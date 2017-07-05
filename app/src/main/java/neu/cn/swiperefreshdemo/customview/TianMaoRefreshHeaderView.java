package neu.cn.swiperefreshdemo.customview;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.SwipeRefreshTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;

import neu.cn.swiperefreshdemo.R;

import static android.R.attr.y;

/**
 * Created by neuHenry on 2017/7/5.
 */

public class TianMaoRefreshHeaderView extends RelativeLayout implements SwipeTrigger, SwipeRefreshTrigger {

    private ImageView imageViewRefresh;
    private LinearLayout linearLayoutRefresh;
    private TextView textViewRefresh;

    private AnimationDrawable mAnimationDrawable;

    private int mHeaderHeight;

    public TianMaoRefreshHeaderView(Context context) {
        this(context, null);
    }

    public TianMaoRefreshHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TianMaoRefreshHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mHeaderHeight = getResources().getDimensionPixelOffset(R.dimen.refresh_header_height_100);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        linearLayoutRefresh = (LinearLayout) findViewById(R.id.linear_layout_refresh);
        imageViewRefresh = (ImageView) findViewById(R.id.image_view_refresh);
        textViewRefresh = (TextView) findViewById(R.id.text_view_refresh);
        mAnimationDrawable = (AnimationDrawable) imageViewRefresh.getBackground();
        if (!mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }
    }

    @Override
    public void onRefresh() {
        textViewRefresh.setText("正在刷新...");
        imageViewRefresh.setBackgroundResource(R.drawable.animation_list_refresh_tm_release);
        mAnimationDrawable = (AnimationDrawable) imageViewRefresh.getBackground();
        if (!mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }
    }

    @Override
    public void onPrepare() {
        mAnimationDrawable = (AnimationDrawable) imageViewRefresh.getBackground();
        if (!mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }
    }

    @Override
    public void onMove(int i, boolean isComplete, boolean b1) {
        if (!isComplete) {
            if (y > mHeaderHeight) {
                textViewRefresh.setText("释放刷新");
            } else if (y < mHeaderHeight) {
                textViewRefresh.setText("下拉刷新");
            }
        }
    }

    @Override
    public void onRelease() {

    }

    @Override
    public void onComplete() {
        mAnimationDrawable.stop();
        imageViewRefresh.setBackgroundResource(R.drawable.animation_list_refresh_tm_pull);
        textViewRefresh.setText("刷新完成");
    }

    @Override
    public void onReset() {
        mAnimationDrawable.stop();
        imageViewRefresh.setBackgroundResource(R.drawable.animation_list_refresh_tm_pull);
        mAnimationDrawable = (AnimationDrawable) imageViewRefresh.getBackground();
        textViewRefresh.setText("下拉刷新");
    }
}
