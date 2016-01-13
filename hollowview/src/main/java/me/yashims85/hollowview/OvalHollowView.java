package me.yashims85.hollowview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;

/**
 * Created by yashims85 on 2016/01/13.
 */
public class OvalHollowView extends HollowView {
    public OvalHollowView(Context context) {
        super(context);
    }

    public OvalHollowView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public OvalHollowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public OvalHollowView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected Path makePath() {
        Path path = super.makePath();
        RectF rect = new RectF(0.0f, 0.0f, this.getWidth(), this.getHeight());
        path.addOval(rect, Path.Direction.CCW);
        return path;
    }
}
