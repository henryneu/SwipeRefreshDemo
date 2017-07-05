package neu.cn.swiperefreshdemo.customview;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.aspsine.swipetoloadlayout.SwipeRefreshTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;

import neu.cn.swiperefreshdemo.R;

/**
 * Created by neuHenry on 2017/7/5.
 */

public class MeiTuanRefreshHeaderView extends RelativeLayout implements SwipeTrigger, SwipeRefreshTrigger {

    private ImageView imageViewRefresh;
    private LinearLayout linearLayoutRefresh;

    private AnimationDrawable mAnimationDrawable;

    private int mHeaderHeight;

    public MeiTuanRefreshHeaderView(Context context) {
        this(context, null);
    }

    public MeiTuanRefreshHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MeiTuanRefreshHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mHeaderHeight = getResources().getDimensionPixelOffset(R.dimen.refresh_header_height_100);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        imageViewRefresh = (ImageView) findViewById(R.id.image_view_refresh);
        linearLayoutRefresh = (LinearLayout) findViewById(R.id.linear_layout_refresh);
        mAnimationDrawable = (AnimationDrawable) imageViewRefresh.getBackground();
        if (!mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }
    }

    @Override
    public void onRefresh() {
        if (!mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }
    }

    @Override
    public void onPrepare() {
        linearLayoutRefresh.setAlpha(0.3f);
        imageViewRefresh.setScaleX(0.4f);
        imageViewRefresh.setScaleY(0.4f);
        if (!mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }
    }

    @Override
    public void onMove(int y, boolean isComplete, boolean automatic) {
        if (!isComplete) {
            float scale = (float) y / (float) mHeaderHeight;
            if (y >= mHeaderHeight) {
                imageViewRefresh.setScaleX(1);
                imageViewRefresh.setScaleY(1);
                linearLayoutRefresh.setAlpha(1.0f);
            } else if (y > 0 && y < mHeaderHeight) {
                imageViewRefresh.setScaleX(scale);
                imageViewRefresh.setScaleY(scale);
                linearLayoutRefresh.setAlpha(scale);
            } else {
                imageViewRefresh.setScaleX(0.4f);
                imageViewRefresh.setScaleY(0.4f);
                linearLayoutRefresh.setAlpha(0.3f);
            }
        }
    }

    @Override
    public void onRelease() {
        mAnimationDrawable.stop();
    }

    @Override
    public void onComplete() {
    }

    @Override
    public void onReset() {
        mAnimationDrawable.stop();
        linearLayoutRefresh.setAlpha(1.0f);
    }
}
