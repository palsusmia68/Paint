package edu.utah.cs4962.paint_test;
import android.graphics.Color;

/**
 * Created by minwen on 09/21/2014
 *
 * This class represents the colors on pallette,
 * as well as the mixed colors
 */
public class ColorsOnPallette {
    private float _green;
    private float _blue;
    private float _red;
    private float _black;

    public float get_green() {
        return _green;
    }

    public float get_blue() {
        return _blue;
    }

    public float get_red() {
        return _red;
    }

    public float get_black() {
        return _black;
    }

    public ColorsOnPallette(float g, float b, float r, float bl) {
        this._green = g;
        this._blue = b;
        this._red = r;
        this._black = bl;
    }

    public int rgb(){
        //get rgb color
        //Color.rgb will calculate the rgb value based on (alpha << 24) | (red << 16) | (green << 8) | blue
        //255.0f means 100% contribution for that component.
        int red = Math.round(255.0f *  _green * (2.0f - _black));
        int green = Math.round(255.0f * _blue * (2.0f - _black));
        int blue = Math.round(255.0f * _red * (2.0f -_black));
        return Color.rgb(red, green, blue);
    }
}
