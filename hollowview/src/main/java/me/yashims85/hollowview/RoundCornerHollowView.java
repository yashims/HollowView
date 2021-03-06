package me.yashims85.hollowview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;

/**
 * Created by yashims85 on 2016/01/13.
 */
public class RoundCornerHollowView extends HollowView {

    private float cornerRadius = 0.0f;

    public void setCornerRadius(float radius) {
        this.cornerRadius = radius;
    }

    public float getCornerRadius() {
        return this.cornerRadius;
    }

    public RoundCornerHollowView(Context context) {
        super(context);
    }

    public RoundCornerHollowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setup(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public RoundCornerHollowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setup(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public RoundCornerHollowView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.setup(context, attrs);
    }

    protected void setup(Context context, AttributeSet attrs) {
        TypedArray typed = context.getTheme().obtainStyledAttributes(attrs, R.styleable.RoundCornerHollowView, 0, 0);
        try {
            this.setCornerRadius(typed.getFloat(R.styleable.RoundCornerHollowView_cornerRadius, 0.0f));
        } finally {
            typed.recycle();
        }
    }

    @Override
    protected Path makePath() {
        Path path = new Path();
        path.addRoundRect(
                this.getPaddingAdjustedRectF(),
                this.getCornerRadius(),
                this.getCornerRadius(),
                Path.Direction.CCW
        );
        return path;
    }
}
