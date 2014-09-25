package edu.utah.cs4962.paint_test;

import android.os.Bundle;
import android.app.Activity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class PaintActivity extends Activity {

    private PaintView paintView;
    private PalletteView palletteView;
    private OnClickChangeColorListener colorChanged = new OnClickChangeColorListener() {
        @Override
        public void onColorChange(ColorsOnPallette color) {
            paintView.SetPaintColor(color);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout rootLayoutView = new LinearLayout(this);
        paintView = new PaintView(this); //get paint and pallette views
        palletteView = new PalletteView(this);

        // red, blue, green, and black as initial colors
        // make pallette for each initial color
        palletteView.InitialColors(true, 1.0f, 0.0f, 0.0f, 0.0f);
        palletteView.InitialColors(true, 0.0f, 1.0f, 0.0f, 0.0f);
        palletteView.InitialColors(true, 0.0f, 0.0f, 1.0f, 0.0f);
        palletteView.set_onClickChangeColorListener(colorChanged); //once gets clicked, notify color has been changed
        rootLayoutView.setOrientation(LinearLayout.VERTICAL);
        rootLayoutView.addView(paintView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1));
        rootLayoutView.addView(palletteView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1));
        setContentView(rootLayoutView);
    }
}