package neu.cn.swiperefreshdemo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;

import neu.cn.swiperefreshdemo.R;

/**
 * Created by neuHenry on 2017/7/5.
 */

public class TianMaoRefreshFragment extends Fragment implements OnRefreshListener {

    private SwipeToLoadLayout mSwipeToLoadLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mFragmentView = inflater.inflate(R.layout.fragment_tianmao, container, false);
        mSwipeToLoadLayout = (SwipeToLoadLayout) mFragmentView.findViewById(R.id.swipe_to_loadlayout);
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
