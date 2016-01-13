package me.yashims85.hollowview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

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

    private ColorDrawable mCornerBackground;

    public void setCornerBackground(ColorDrawable drawable) {
        this.mCornerBackground = drawable;
    }

    public ColorDrawable getCornerBackground() {
        return this.mCornerBackground;
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
            if (this.getBackground() instanceof ColorDrawable) {
                this.mCornerBackground = (ColorDrawable) this.getBackground();
            }
            this.setBackground(null);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        if (this.mCornerBackground != null) {
            paint.setColor(this.mCornerBackground.getColor());
        }

        canvas.drawPath(this.makePath(), paint);
    }

    protected Path makePath() {
        Path path = new Path();
        RectF rect = new RectF(0.0f, 0.0f, this.getWidth(), this.getHeight());
        path.addRect(rect, Path.Direction.CW);
        return path;
    }
}