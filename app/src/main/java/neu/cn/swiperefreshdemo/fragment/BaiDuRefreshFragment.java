package neu.cn.swiperefreshdemo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;

import neu.cn.swiperefreshdemo.R;
import neu.cn.swiperefreshdemo.customview.BaiDuRefreshHeaderView;

import static neu.cn.swiperefreshdemo.R.id.iv_refresh_bg;

/**
 * Created by neuHenry on 2017/7/5.
 */

public class BaiDuRefreshFragment extends Fragment implements OnRefreshListener {

    private static final String TAG = "BaiDuRefreshFragment";

    private SwipeToLoadLayout mSwipeToLoadLayout;
    private ImageView imageViewRefreshBackground;
    private BaiDuRefreshHeaderView mBaiDuRefreshHeaderView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mFragmentView = inflater.inflate(R.layout.fragment_baidu, container, false);

        mSwipeToLoadLayout = (SwipeToLoadLayout) mFragmentView.findViewById(R.id.swipe_to_loadlayout);
        imageViewRefreshBackground = (ImageView) mFragmentView.findViewById(iv_refresh_bg);
        mBaiDuRefreshHeaderView = (BaiDuRefreshHeaderView) mFragmentView.findViewById(R.id.swipe_refresh_header);

        mSwipeToLoadLayout.setOnRefreshListener(this);
        mSwipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeToLoadLayout.setRefreshing(true);
                mSwipeToLoadLayout.setRefreshFinalDragOffset(imageViewRefreshBackground.getHeight() - 100);
            }
        }, 100);

        mBaiDuRefreshHeaderView.setOnMoveListener(new BaiDuRefreshHeaderView.OnMoveListener() {
            @Override
            public void onMove(int y) {
                int height = imageViewRefreshBackground.getHeight();
                int move = height - y - 20;
                Log.i(TAG, "y:" + y + ",move:" + move);
                if (move < 20) {
                    move = 20;
                }
                imageViewRefreshBackground.setY(-move);
            }
        });

        return mFragmentView;
    }

    @Override
    public void onRefresh() {
        mSwipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeToLoadLayout.setRefreshing(false);
            }
        }, 3000);
    }
}
