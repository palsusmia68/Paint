package edu.utah.cs4962.paint_test;

/**
 * Created by minwen on 9/21/2014.
 */
import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class PalletteActivity extends Activity {
    private PalletteView _palletteView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout palletteLayout = new LinearLayout(this);
        _palletteView = new PalletteView(this);
        palletteLayout.addView(_palletteView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1));
        setContentView(palletteLayout);
    }
}

