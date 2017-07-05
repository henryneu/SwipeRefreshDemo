package neu.cn.swiperefreshdemo.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.SwipeRefreshHeaderLayout;

import neu.cn.swiperefreshdemo.R;

/**
 * Created by neuHenry on 2017/7/4.
 */

public class WeatherRefreshHeaderView extends SwipeRefreshHeaderLayout {

    private int mHeaderHeight;

    private Animation rotateAnimation;

    private RelativeLayout mRelativeLayout;
    private TextView mTextViewRefresh;

    private Boolean rotated = false;

    public WeatherRefreshHeaderView(Context context) {
        this(context, null);
    }

    public WeatherRefreshHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WeatherRefreshHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mHeaderHeight = getResources().getDimensionPixelOffset(R.dimen.refresh_header_height_80);
        rotateAnimation = AnimationUtils.loadAnimation(context, R.anim.rotate_sun);
        LinearInterpolator lir = new LinearInterpolator();
        rotateAnimation.setInterpolator(lir);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mRelativeLayout = (RelativeLayout) findViewById(R.id.iv_sun);
        mTextViewRefresh = (TextView) findViewById(R.id.textview_refresh);
    }

    @Override
    public void onRefresh() {
        mRelativeLayout.clearAnimation();
        mRelativeLayout.startAnimation(rotateAnimation);
        mTextViewRefresh.setText("正在刷新...");
    }

    @Override
    public void onPrepare() {
        Log.d("WeatherRefreshHeader", "onPrepare()");
    }

    @Override
    public void onMove(int y, boolean isComplete, boolean automatic) {
        if (!isComplete) {
            if (y > mHeaderHeight) {
                mTextViewRefresh.setText("释放刷新");
                if (!rotated) {
                    mRelativeLayout.clearAnimation();
                    mRelativeLayout.startAnimation(rotateAnimation);
                    rotated = true;
                }
            } else if (y < mHeaderHeight) {
                if (rotated) {
                    rotated = false;
                }
                mTextViewRefresh.setText("下拉刷新");
            }
        }
    }

    @Override
    public void onRelease() {
        Log.d("WeatherRefreshHeader", "onRelease()");
    }

    @Override
    public void onComplete() {
        rotated = false;
        mTextViewRefresh.setText("刷新完成");
        mRelativeLayout.clearAnimation();
    }

    @Override
    public void onReset() {
        rotated = false;
        mTextViewRefresh.setText("下拉刷新");
    }
}
