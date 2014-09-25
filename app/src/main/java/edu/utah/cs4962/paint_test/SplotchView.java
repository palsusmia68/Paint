package edu.utah.cs4962.paint_test;

/**
 * Created by minwen on 9/21/2014.
 */
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class SplotchView extends View {

    private ColorsOnPallette _color;
    //checks for pallette activity, if active, sets the background color
    //sets it to inactive when color is not selected
    private boolean _isPalletteActive;

    public SplotchView(Context context, ColorsOnPallette color){
        super(context);
        this._color = color;
    }

    public void set_color(ColorsOnPallette _color){
        this._color = _color;
    }

    public ColorsOnPallette get_color(){
        return _color;
    }

    public void SetIsPalletteActive(boolean active) {
        this._isPalletteActive = active;
        this.invalidate();
    }

    public boolean IsPalletteActive() {
        return _isPalletteActive;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //sets the color of the background of the selected splotch
        if (_isPalletteActive){
            canvas.drawColor(Color.MAGENTA);
        }
        Paint paint = new Paint();
        int rgb = _color.rgb();
        paint.setColor(rgb);
        //draws the shape of the splotch for each color on pallette
        canvas.drawCircle((getWidth() * 0.5f), (getHeight()/1.5f), (getWidth()/1.5f), paint);
    }
}

