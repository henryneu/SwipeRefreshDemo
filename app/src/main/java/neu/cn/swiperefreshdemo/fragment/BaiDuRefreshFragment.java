package neu.cn.swiperefreshdemo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;

import neu.cn.swiperefreshdemo.R;
import neu.cn.swiperefreshdemo.customview.BaiDuRefreshHeaderView;

/**
 * Created by neuHenry on 2017/7/5.
 */

public class BaiDuRefreshFragment extends Fragment implements OnRefreshListener {

    private SwipeToLoadLayout mSwipeToLoadLayout;
    private ImageView iv_refresh_bg;
    private BaiDuRefreshHeaderView swipe_refresh_header;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mFragmentView = inflater.inflate(R.layout.fragment_baidu, container, false);
        mSwipeToLoadLayout = (SwipeToLoadLayout) mFragmentView.findViewById(R.id.swipe_to_loadlayout);
        iv_refresh_bg = (ImageView) findViewById(R.id.iv_refresh_bg);
        swipe_refresh_header = (BaiDuRefreshHeaderView) findViewById(R.id.swipe_refresh_header);
        mSwipeToLoadLayout.setOnRefreshListener(this);
        mSwipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeToLoadLayout.setRefreshing(true);
            }
        }, 100);
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
