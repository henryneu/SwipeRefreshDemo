package neu.cn.swiperefreshdemo.customview;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.aspsine.swipetoloadlayout.SwipeRefreshHeaderLayout;

import neu.cn.swiperefreshdemo.R;

/**
 * Created by neuHenry on 2017/7/5.
 */

public class BaiDuRefreshHeaderView extends SwipeRefreshHeaderLayout {

    private Animation rotateAnimation;
    private Animation pull_back_anim_right_cloud_linear;
    private Animation pull_back_anim_left_cloud_linear;
    private Animation pull_back_anim_left_linear;
    private Animation pull_back_anim_right_linear;

    private ImageView imageViewSun;
    private ImageView imageViewRefresh;
    private ImageView imageViewCloud;
    private ImageView imageViewCloud2;
    private ImageView imageViewCastle;
    private ImageView imageViewCastle2;

    private AnimationDrawable mAnimationDrawable;

    public BaiDuRefreshHeaderView(Context context) {
        this(context, null);
    }

    public BaiDuRefreshHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaiDuRefreshHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        rotateAnimation = AnimationUtils.loadAnimation(context, R.anim.rotate_sun);
        LinearInterpolator lir = new LinearInterpolator();
        rotateAnimation.setInterpolator(lir);

        pull_back_anim_left_cloud_linear = AnimationUtils.loadAnimation(context, R.anim.pull_back_anim_left_cloud_linear);
        pull_back_anim_right_cloud_linear = AnimationUtils.loadAnimation(context, R.anim.pull_back_anim_right_cloud_linear);
        pull_back_anim_left_linear = AnimationUtils.loadAnimation(context, R.anim.pull_back_anim_left_linear);
        pull_back_anim_right_linear = AnimationUtils.loadAnimation(context, R.anim.pull_back_anim_right_linear);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        imageViewRefresh = (ImageView) findViewById(R.id.image_view_refresh);
        imageViewSun = (ImageView) findViewById(R.id.image_view_sun);
        imageViewCloud = (ImageView) findViewById(R.id.image_view_back_cloud);
        imageViewCloud2 = (ImageView) findViewById(R.id.image_view_back_cloud2);
        imageViewCastle = (ImageView) findViewById(R.id.image_view_back_castle);
        imageViewCastle2 = (ImageView) findViewById(R.id.image_view_back_castle2);
        mAnimationDrawable = (AnimationDrawable) imageViewRefresh.getBackground();

        //开启动画
        openAnimation();
    }

    private void openAnimation() {
        //开启动画
        if (!mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }

        imageViewSun.clearAnimation();
        imageViewCloud.clearAnimation();
        imageViewCloud2.clearAnimation();
        imageViewCastle.clearAnimation();
        imageViewCastle2.clearAnimation();

        imageViewSun.startAnimation(rotateAnimation);
        imageViewCloud.startAnimation(pull_back_anim_left_cloud_linear);
        imageViewCloud2.startAnimation(pull_back_anim_right_cloud_linear);
        imageViewCastle.startAnimation(pull_back_anim_left_linear);
        imageViewCastle2.startAnimation(pull_back_anim_right_linear);
    }

    @Override
    public void onRefresh() {
    }

    @Override
    public void onPrepare() {
        //开启动画
        openAnimation();

    }

    @Override
    public void onMove(int y, boolean isComplete, boolean automatic) {
        if (!isComplete) {
            if (onMoveListener != null) {
                onMoveListener.onMove(y);
            }
        }
    }

    @Override
    public void onRelease() {
    }

    @Override
    public void onComplete() {
    }

    @Override
    public void onReset() {
        mAnimationDrawable.stop();
        imageViewSun.clearAnimation();
        imageViewCloud.clearAnimation();
        imageViewCloud2.clearAnimation();
        imageViewCastle.clearAnimation();
        imageViewCastle2.clearAnimation();
    }

    private OnMoveListener onMoveListener;

    public interface OnMoveListener {
        void onMove(int y);
    }

    public void setOnMoveListener(OnMoveListener onMoveListener) {
        this.onMoveListener = onMoveListener;
    }
}
