package me.yashims85.hollowview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by yashims85 on 2016/01/13.
 */
public class HollowView extends View {

    private Drawable edgeDrawable = null;

    public void setEdgeDrawable(Drawable drawable) {
        this.edgeDrawable = drawable;
    }

    public Drawable getEdgeDrawable() {
        return this.edgeDrawable;
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
        if (this.getBackground() != null) {
            this.setEdgeDrawable(
                    this.getBackground().getConstantState().newDrawable()
            );
            this.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Drawable drw = this.getEdgeDrawable();
        if (drw != null) {
            Rect r = new Rect();
            this.getLocalVisibleRect(r);
            drw.setBounds(r);
            canvas.clipPath(this.makePath(), Region.Op.DIFFERENCE);
            drw.draw(canvas);
        }
    }

    protected Path makePath() {
        return new Path();
    }

    protected RectF getPaddingAdjustedRectF() {
        return new RectF(
                this.getPaddingLeft(),
                this.getPaddingTop(),
                this.getWidth() - this.getPaddingRight(),
                this.getHeight() - this.getPaddingBottom()
        );
    }
}
