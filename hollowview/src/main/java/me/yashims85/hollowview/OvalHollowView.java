package me.yashims85.hollowview;

import android.content.Context;
import android.graphics.Path;
import android.graphics.RectF;

/**
 * Created by yashims85 on 2016/01/13.
 */
public class OvalHollowView extends HollowView {
    public OvalHollowView(Context context) {
        super(context);
    }

    @Override
    protected Path makePath() {
        Path path = super.makePath();
        RectF rect = new RectF(0.0f, 0.0f, this.getWidth(), this.getHeight());
        path.addOval(rect, Path.Direction.CCW);
        return path;
    }
}
