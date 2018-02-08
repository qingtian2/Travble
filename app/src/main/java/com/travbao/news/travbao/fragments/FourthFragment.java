package com.travbao.news.travbao.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.travbao.news.travbao.R;
import com.travbao.news.travbao.activitys.TabActivity;
import com.travbao.news.travbao.base.BaseMvpFragment;
import com.travbao.news.travbao.presenter.FourthFragmentPresenter;
import com.travbao.news.travbao.view.FourthFragmentView;

import cn.jzvd.JZVideoPlayer;


/**
 *Created by lijinbo on 18/1/1.
 */

public class FourthFragment extends BaseMvpFragment<FourthFragmentView,FourthFragmentPresenter> implements FourthFragmentView{

   TabActivity activity;

    @Override
    public FourthFragmentPresenter initPresenter() {
        return new FourthFragmentPresenter();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        activity = (TabActivity) getActivity() ;
        View view = inflater.inflate(R.layout.fragment_fourth, container, false);
        return view;
    }
    @Override
    public void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }
}
