package com.travbao.news.travbao.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.travbao.news.travbao.R;
import com.travbao.news.travbao.base.BaseMvpFragment;
import com.travbao.news.travbao.presenter.SecondFragmentPresenter;
import com.travbao.news.travbao.utils.ToastUtil;
import com.travbao.news.travbao.view.SecondFragmentView;

import cn.jzvd.JZVideoPlayer;


/**
 * Created by Administrator on 2017/12/29.
 */

public class SecondFragment extends BaseMvpFragment<SecondFragmentView, SecondFragmentPresenter> implements SecondFragmentView {


    @Override
    public SecondFragmentPresenter initPresenter() {
        return new SecondFragmentPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }

}
