package neu.cn.swiperefreshdemo.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.SwipeRefreshHeaderLayout;

import neu.cn.swiperefreshdemo.R;

/**
 * Created by neuHenry on 2017/7/5.
 */

public class WeiBoRefreshHeaderView extends SwipeRefreshHeaderLayout {

    private ImageView imageViewArrow;

    private TextView textViewRefresh;

    private ProgressBar mProgressBar;

    private int mHeaderHeight;

    private Animation animRotateUp;

    private Animation animRotateDown;

    private boolean rotated = false;

    public WeiBoRefreshHeaderView(Context context) {
        this(context, null);
    }

    public WeiBoRefreshHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WeiBoRefreshHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mHeaderHeight = getResources().getDimensionPixelOffset(R.dimen.refresh_header_height_60);
        animRotateUp = AnimationUtils.loadAnimation(context, R.anim.anim_rotate_up);
        animRotateDown = AnimationUtils.loadAnimation(context, R.anim.anim_rotate_down);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        textViewRefresh = (TextView) findViewById(R.id.text_view_refresh);
        imageViewArrow = (ImageView) findViewById(R.id.image_view_arrow);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
    }

    @Override
    public void onRefresh() {
        imageViewArrow.clearAnimation();
        imageViewArrow.setVisibility(GONE);
        mProgressBar.setVisibility(VISIBLE);
        textViewRefresh.setText("正在刷新...");
    }

    @Override
    public void onPrepare() {
    }

    @Override
    public void onMove(int y, boolean isComplete, boolean automatic) {
        if (!isComplete) {
            imageViewArrow.setVisibility(VISIBLE);
            mProgressBar.setVisibility(GONE);
            if (y > mHeaderHeight) {
                textViewRefresh.setText("释放刷新");
                if (!rotated) {
                    imageViewArrow.clearAnimation();
                    imageViewArrow.startAnimation(animRotateUp);
                    rotated = true;
                }
            } else if (y < mHeaderHeight) {
                if (rotated) {
                    imageViewArrow.clearAnimation();
                    imageViewArrow.startAnimation(animRotateDown);
                    rotated = false;
                }
                textViewRefresh.setText("下拉刷新");
            }
        }
    }

    @Override
    public void onRelease() {
    }

    @Override
    public void onComplete() {
        rotated = false;
        imageViewArrow.clearAnimation();
        imageViewArrow.setVisibility(GONE);
        mProgressBar.setVisibility(GONE);
        textViewRefresh.setText("刷新完成");
    }

    @Override
    public void onReset() {
        rotated = false;
        imageViewArrow.clearAnimation();
        imageViewArrow.setVisibility(GONE);
        mProgressBar.setVisibility(GONE);
        textViewRefresh.setText("下拉刷新");
    }

}
