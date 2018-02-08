package com.travbao.news.travbao.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.travbao.news.travbao.R;
import com.travbao.news.travbao.activitys.TabActivity;
import com.travbao.news.travbao.base.BaseMvpFragment;
import com.travbao.news.travbao.presenter.ThirdFragmentPresenter;
import com.travbao.news.travbao.view.ThirdFragmentView;

import cn.jzvd.JZVideoPlayer;


/**
 * Created by lijinbo on 18/1/1.
 */

public class ThirdFragment extends BaseMvpFragment<ThirdFragmentView, ThirdFragmentPresenter> implements ThirdFragmentView {

    private TabActivity activity;

    @Override
    public ThirdFragmentPresenter initPresenter() {
        return new ThirdFragmentPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        activity = (TabActivity) getActivity();
        View view = inflater.inflate(R.layout.fragment_third, container, false);
        //initView(view);
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }
}
