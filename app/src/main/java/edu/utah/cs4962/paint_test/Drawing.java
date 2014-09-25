package edu.utah.cs4962.paint_test;

/**
 * Created by minwen on 9/21/2014.
 * TODO: implement Matt's transformation
 */
import android.graphics.Paint;
import android.graphics.Path;

import java.util.ArrayList;
import java.util.List;

public class Drawing {
    private Path _paintPath;
    private Paint Paint;
    private ArrayList<Path> paths = new ArrayList<Path>();

    public Path getPath() {
        return _paintPath;
    }

    public Paint getPaint() {
        return Paint;
    }

    public List<Path> getPaths() {
        return paths;
    }

    public void setPath(Path path) {
        this._paintPath = path;
    }

    public Drawing(int color) {
        Paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        Paint.setColor(color);
        Paint.setStyle(android.graphics.Paint.Style.STROKE);
        Paint.setStrokeWidth(2);
        _paintPath = new Path();
        paths.add(_paintPath);
    }
}

