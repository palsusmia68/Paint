package edu.utah.cs4962.paint_test;

/**
 * Created by minwen on 9/21/2014.
 */
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class PalletteView extends ViewGroup{

    private boolean _isMix;
    Button _mixButton;
    private OnClickChangeColorListener _onClickChangeColorListener;

    public void set_onClickChangeColorListener(OnClickChangeColorListener _onClickChangeColorListener) {
        this._onClickChangeColorListener = _onClickChangeColorListener;
    }

    public PalletteView(Context context) {
        super(context);
        this._isMix = false;
        setWillNotDraw(false);

        // Setup mix button
        _mixButton = new Button(this.getContext());
        _mixButton.setText("Mix them!");
        _mixButton.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        _mixButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                _isMix = true;
                _mixButton.setText("Mixed!");
            }
        });
        this.addView(_mixButton);
    }

    /**
     * Give initial colors to each splotch on pallette
     * Also set button text
     */
    public SplotchView InitialColors(boolean isValid, float black, float green, float red, float blue){
        ColorsOnPallette color = new ColorsOnPallette(black, green, red, blue);

        SplotchView paint = new SplotchView(this.getContext(), color);
        paint.setOnClickListener(_selectedPaint);
        this.addView(paint);
        _isMix = false;
        invalidate();
        return paint;
    }

    // click listener for paint blotches.
    // when clicked, select the new paint or mixes the new paint.
    private OnClickListener _selectedPaint = new OnClickListener() {
        @Override
        public void onClick(View view) {
            SplotchView paintView = (SplotchView)view;
            ColorsOnPallette color = null;
            //gets all active colors from children and set the rest to inactive
            for (int childIndex = 0; childIndex < getChildCount(); childIndex++){
                View v = getChildAt(childIndex);
                if(v instanceof SplotchView){
                    SplotchView childView = (SplotchView)v;
                    if (childView.IsPalletteActive()){
                        color = childView.get_color();
                        childView.SetIsPalletteActive(false);
                    }
                }
            }
            if (_isMix){
                SplotchView paint = mixPaints(paintView.get_color(), color);
                paint.SetIsPalletteActive(true);
                _onClickChangeColorListener.onColorChange(paint.get_color());
            } else {
                paintView.SetIsPalletteActive(true);
                _onClickChangeColorListener.onColorChange(paintView.get_color());
            }
        }
    };

    //this is the mix method for mixing 2 colors on pallette and create a new color
    //then adds the new mixed color onto the pallette
    //TODO: black always occurs as the first mixed in colors...
    private SplotchView mixPaints(ColorsOnPallette firstColor, ColorsOnPallette secondColor) {
        // TODO: can probably get better result when divide by 3.0f...
        float _green = firstColor.get_green() -  ((firstColor.get_green() - secondColor.get_green()) / 1.5f);
        float _blue = firstColor.get_blue() - ((firstColor.get_blue() - secondColor.get_blue()) / 1.5f);
        float _red = firstColor.get_red() - ((firstColor.get_red() - secondColor.get_red()) / 1.5f);
        float _black = firstColor.get_black() - ((firstColor.get_black() - secondColor.get_black()) / 1.5f);
        return InitialColors(true, _green, _blue, _red, _black);
    }

    /*
    This method determines the layout of additional and existing splotches
     */
    @Override
    protected void onLayout(boolean b, int i, int i2, int i3, int i4) {
        //sets the angle to turn for splotches
        float turningAngle = 1.5f;
        int childCount = getChildCount() - 1;
        float paletteWidth = (float)getWidth();
        float paletteHeight = (float)getHeight();

        for (int childIndex = 0; childIndex < childCount; childIndex++){
            View child = getChildAt(childIndex);
            float childWidth = 50.0f;
            float childHeight = 50.0f;
            float centerX = (float)getWidth() * 0.5f;
            float centerY = (float)getHeight() * 0.5f;
            //splotchview has its own sizes; if not splotche view, must be the botton
            //botton has its own size
            if (child instanceof SplotchView){
                turningAngle -= 0.4f;
                if (turningAngle == -3.0f){
                    turningAngle = 2.8f;
                }
                centerX = (paletteWidth * 0.5f) + ((paletteWidth * 0.5f) * (float)Math.cos(turningAngle));
                centerY = (paletteHeight * 0.5f) + ((paletteHeight * 0.5f) * (float)Math.sin(turningAngle));
            } else {
                childWidth = 250.0f;
                childHeight = 90.0f;
            }
            RectF r = new RectF();
            r.left = -childWidth * 0.5f + centerX;
            r.right = childWidth * 0.5f + centerX;
            r.top = -childHeight * 0.5f + centerY;
            r.bottom = childHeight * 0.5f + centerY;
            child.layout((int)r.left, (int)r.top, (int)r.right, (int)r.bottom);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
