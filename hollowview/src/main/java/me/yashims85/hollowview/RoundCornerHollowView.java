package me.yashims85.hollowview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;

/**
 * Created by yashims85 on 2016/01/13.
 */
public class RoundCornerHollowView extends HollowView {

    private float mCornerRadius = 0.0f;

    public void setmCornerRadius(float radius) {
        this.mCornerRadius = radius;
    }

    public float getmCornerRadius() {
        return this.mCornerRadius;
    }

    public RoundCornerHollowView(Context context) {
        super(context);
    }

    public RoundCornerHollowView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public RoundCornerHollowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public RoundCornerHollowView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void init(Context context, AttributeSet attrs) {
        super.init(context, attrs);
        TypedArray typed = context.getTheme().obtainStyledAttributes(attrs, R.styleable.HollowView, 0, 0);
        try {
            this.mCornerRadius = typed.getFloat(R.styleable.HollowView_cornerRadius, 0.0f);
        } finally {
            typed.recycle();
        }
    }

    @Override
    protected Path makePath() {
        Path path = super.makePath();
        RectF rect = new RectF(0.0f, 0.0f, this.getWidth(), this.getHeight());
        path.addRoundRect(rect, this.mCornerRadius, this.mCornerRadius, Path.Direction.CCW);
        return path;
    }
}
