package neu.cn.swiperefreshdemo.customview;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.aspsine.swipetoloadlayout.SwipeRefreshTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;

import neu.cn.swiperefreshdemo.R;

/**
 * Created by neuHenry on 2017/7/5.
 */

public class JingDongRefreshHeaderView extends RelativeLayout implements SwipeTrigger, SwipeRefreshTrigger {

    private LinearLayout linearLayoutRefresh;
    // 京东小人后面表示小人速度的图片
    private ImageView imageViewSpeed;
    // 京东小人跑动的动画集
    private ImageView imageViewRefresh;
    // 京东小人跑动时前面的小盒子
    private ImageView imageViewBox;
    private AnimationDrawable mAnimationDrawable;
    private Animation mTwinkAnim;

    public JingDongRefreshHeaderView(Context context) {
        this(context, null);
    }

    public JingDongRefreshHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public JingDongRefreshHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        linearLayoutRefresh = (LinearLayout) findViewById(R.id.linear_layout_refresh);
        imageViewRefresh = (ImageView) findViewById(R.id.image_view_refresh);
        imageViewSpeed = (ImageView) findViewById(R.id.image_view_speed);
        imageViewBox = (ImageView) findViewById(R.id.image_view_box);
        mAnimationDrawable = (AnimationDrawable) imageViewRefresh.getBackground();
        mTwinkAnim = AnimationUtils.loadAnimation(getContext(), R.anim.twinkle);
        imageViewSpeed.setVisibility(View.GONE);
    }

    @Override
    public void onRefresh() {
        imageViewSpeed.setVisibility(VISIBLE);
        imageViewBox.setVisibility(GONE);
        imageViewSpeed.startAnimation(mTwinkAnim);
        linearLayoutRefresh.setAlpha(1.0f);
        imageViewBox.setAlpha(1.0f);
        if (!mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }
    }

    @Override
    public void onPrepare() {
        imageViewSpeed.clearAnimation();
        imageViewSpeed.setVisibility(GONE);
        imageViewBox.setVisibility(VISIBLE);
        linearLayoutRefresh.setAlpha(0.3f);
        imageViewBox.setAlpha(0.3f);
    }

    @Override
    public void onMove(int i, boolean b, boolean b1) {

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
        imageViewSpeed.clearAnimation();
        imageViewSpeed.setVisibility(GONE);
        imageViewBox.setVisibility(VISIBLE);
        linearLayoutRefresh.setAlpha(1.0f);
        imageViewBox.setAlpha(1.0f);
    }
}
