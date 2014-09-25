package edu.utah.cs4962.paint_test;

/**
 * Created by minwen on 9/21/2014.
 */
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class PaintView extends View implements View.OnTouchListener{
    private Canvas _canvas;
    private float _x, _y;
    //array of painted lines
    private List<Drawing> _drawings;
    //current paint line
    private Drawing _lines;
    private static final float _distanceFromInitial = 6;

    public PaintView(Context context) {
        super(context);
        setFocusable(true);

        this.setOnTouchListener(this);
        _drawings = new ArrayList<Drawing>();
        //set to white so it doesn't interfere with other colors
        _lines = new Drawing(Color.WHITE);
        _drawings.add(_lines);
        _canvas = new Canvas();
    }

    //Draws the path for painted lines
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        for (Drawing ptLine : _drawings){
            for (Path p : ptLine.getPaths()){
                canvas.drawPath(p, ptLine.getPaint());
            }
        }
    }

    //gets initial position when user pressed down on view
    private void StartDrawingDown(float x, float y) {
        //resets every time user starts a new paint, then move to that location
        _lines.getPath().reset();
        _lines.getPath().moveTo(x, y);
        //gets initial position
        _x = x;
        _y = y;
    }

    /* this method moves as user paints
     * if the drawing line has 6 pixels away from original point
     * draws a curve line instead of the straight line
    */
    private void DrawingMove(float x, float y) {
        float dx = Math.abs(x - _x);
        float dy = Math.abs(y - _y);
        //draws curved lines by using quadTo instead of lineTo
        if (dx >= _distanceFromInitial || dy >= _distanceFromInitial) {
            _lines.getPath().quadTo(_x, _y, (x + _x + 1) * 0.5f, (y + _y + 1) * 0.5f);
            _x = x;
            _y = y;
        }
    }

    //finishes drawing a path when user lift up from view
    private void FinishDrawingUp() {
        _lines.getPath().lineTo(_x, _y);
        _canvas.drawPath(_lines.getPath(), _lines.getPaint());

        //gets a new path whenever user finishes drawing.
        _lines.setPath(new Path());
        _lines.getPaths().add(_lines.getPath());
    }

    //sets the selected color to draw
    public void SetPaintColor(ColorsOnPallette color) {
        int rgb = color.rgb();
        _lines = new Drawing(rgb);
        _drawings.add(_lines);
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        //detects motion, draw each time it detects event
        if (event.getAction() == MotionEvent.ACTION_DOWN)
        {
            StartDrawingDown(x, y);
            invalidate();
        }
        if (event.getAction() == MotionEvent.ACTION_MOVE)
        {
            DrawingMove(x, y);
            invalidate();
        }
        if (event.getAction() == MotionEvent.ACTION_UP)
        {
            FinishDrawingUp();
            invalidate();
        }
        return true;
    }
}

