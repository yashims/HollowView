package me.yashims85.hollowview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import dalvik.bytecode.OpcodeInfo;

/**
 * Created by yashims85 on 2016/01/13.
 */
public class HollowView extends View {

    private boolean mTouchThrough = false;

    public void setTouchThrough(boolean through) {
        this.mTouchThrough = through;
    }

    public boolean getTouchThrough() {
        return this.mTouchThrough;
    }

    private Drawable mCornerDrawable = null;

    public void setCornerDrawable(Drawable drawable) {
        this.mCornerDrawable = drawable;
    }

    public Drawable getCornerDrawable() {
        return this.mCornerDrawable;
    }


    public HollowView(Context context) {
        super(context);
    }

    public HollowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public HollowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public HollowView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.init(context, attrs);
    }

    protected void init(Context context, AttributeSet attrs) {
        TypedArray typed = context.getTheme().obtainStyledAttributes(attrs, R.styleable.HollowView, 0, 0);
        try {
            this.mTouchThrough = typed.getBoolean(R.styleable.HollowView_touchThrough, false);
        } finally {
            typed.recycle();
        }

        if (this.getBackground() != null) {
            this.setCornerDrawable(
                    this.getBackground().getConstantState().newDrawable()
            );
            this.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        if (this.getCornerDrawable() != null) {
            if (this.getCornerDrawable() instanceof ColorDrawable) {
               // Log.d("yashims85", "SET Color");
                paint.setColor(
                        ((ColorDrawable) this.getCornerDrawable()).getColor()
                );
            }
        }

        if (this.getCornerDrawable() != null) {
            this.getCornerDrawable().setBounds(this.getBackground().getBounds());
            Log.d("yashims85", "bounds:" + this.getCornerDrawable().getBounds() + "bgBounds:" + this.getBackground().getBounds() + " color:" + ((ColorDrawable) this.getCornerDrawable()).getColor() + " colorFilter:" + this.getCornerDrawable().getColorFilter());
            canvas.clipPath(this.makePath(), Region.Op.DIFFERENCE);
            this.getCornerDrawable().draw(canvas);
            //canvas.clipPath(this.makePath());
        }

        //canvas.drawPath(this.makePath(), paint);
    }

    protected Path makePath() {
        Path path = new Path();
        RectF rect = new RectF(0.0f, 0.0f, this.getWidth(), this.getHeight());
        //path.addRect(rect, Path.Direction.CW);
        return path;
    }
}
