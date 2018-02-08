package com.travbao.news.travbao.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.travbao.news.travbao.R;
import com.travbao.news.travbao.activitys.ChannelActivity;
import com.travbao.news.travbao.activitys.SearchActivity;
import com.travbao.news.travbao.adapter.TabAdapter;
import com.travbao.news.travbao.base.BaseMvpFragment;
import com.travbao.news.travbao.bean.TitleBean;
import com.travbao.news.travbao.utils.dao.RequestDao;
import com.travbao.news.travbao.presenter.NewsFragmentPresenter;
import com.travbao.news.travbao.utils.PreferencesUtils;
import com.travbao.news.travbao.utils.event.ManEvent;
import com.travbao.news.travbao.utils.event.TabEvent;
import com.travbao.news.travbao.view.ColorTrackTabLayout;
import com.travbao.news.travbao.view.NewsFragmentView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by qingtian on 2017/12/29.
 */
public class TabFragment extends BaseMvpFragment<NewsFragmentView, NewsFragmentPresenter> implements NewsFragmentView {

    private List<TitleBean> data;
    private ColorTrackTabLayout tabLayout;
    private ViewPager viewPager;
    private TabAdapter tabAdapter;
    private CardView cardtoView;
    private View viewClick;
    private int tabPosition;
    private RequestDao dao;


    @Override
    public NewsFragmentPresenter initPresenter() {
        return new NewsFragmentPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dao = new RequestDao(getActivity());
        EventBus.getDefault().register(this);
        View view = inflater.inflate(R.layout.tab_fragment, container, false);
        viewClick = view.findViewById(R.id.index_add_iv);
        cardtoView = view.findViewById(R.id.searchtoview);
        tabLayout = view.findViewById(R.id.index_tablayout);
        viewPager = view.findViewById(R.id.index_viewpager);
        tabAdapter = new TabAdapter(getChildFragmentManager(), data, getActivity());
        viewPager.setAdapter(tabAdapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        getData();
    }

    private void initView() {
        data = new ArrayList<>();
        //隐藏Indicator
//       tabLayout.setSelectedTabIndicatorHeight(0);
        //设置左右内边距
        tabLayout.setTabPaddingLeftAndRight(30, 30);

        cardtoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(0, 0);
            }
        });

        viewClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChannelActivity.class);
                startActivity(intent);
                tabPosition = tabLayout.getSelectedTabPosition();
                getActivity().overridePendingTransition(0, 0);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onTabEvent(final TabEvent event) {
        if (event.isTab()) {
            data = dao.queryInSe(1);
            String result = event.getResult();
            tabLayout.removeAllTabs();
            for (int i = 0; i < data.size(); i++) {
                NewsFragment newsFragment = NewsFragment.newInstance("");
                TabLayout.Tab tab = tabLayout.newTab();
                tab.setCustomView(newsFragment.getView());
                tab.setText(result);
                tabLayout.addTab(tab, i);
            }
            tabLayout.tabScrolled(2, 1);
            viewPager.setAdapter(new TabAdapter(getChildFragmentManager(), data, getActivity()));
            tabLayout.setCurrentItem(2);
            tabLayout.scrollTo(0, 0);
            tabAdapter.notifyDataSetChanged();
            PreferencesUtils.addConfigInfo(getActivity(), "switchCity", result);

        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onManEvent(ManEvent event) {
        if (event.isMan()) {
            viewPager.setCurrentItem(0);
            tabLayout.getTabAt(0).select();
            data = dao.queryInSe(1);
            if (data.size() != 0) {
                viewPager.setAdapter(new TabAdapter(getChildFragmentManager(), data, getActivity()));
                tabAdapter.notifyDataSetChanged();
            }
        }
    }

    private void getData() {
        if (PreferencesUtils.getValueByKey(getActivity(), "isSave", false)) {
            data = dao.queryInSe(1);
            tabAdapter = new TabAdapter(getChildFragmentManager(), data, getActivity());
            viewPager.setAdapter(tabAdapter);
        } else {
            data.add(new TitleBean("时政"));
            data.add(new TitleBean("视频"));
            data.add(new TitleBean("财经"));
            data.add(new TitleBean("政治"));
            data.add(new TitleBean("社会"));
            data.add(new TitleBean("美食"));
            data.add(new TitleBean("商讯"));
            data.add(new TitleBean("摄影"));
            data.add(new TitleBean("科技"));
            data.add(new TitleBean("体育"));
            data.add(new TitleBean("娱乐"));
            data.add(new TitleBean("旅游"));
            data.add(new TitleBean("图片"));

            if (!PreferencesUtils.getValueByKey(getActivity(), "isSave", false)) {
                for (int i = 0; i < 10; i++) {
                    dao.add(data.get(i).getNewstype(), (i + 1), 1);
                }
                for (int i = 10; i < data.size(); i++) {
                    dao.add(data.get(i).getNewstype(), (i - 9), 0);
                }
                PreferencesUtils.addConfigInfo(getActivity(), "isSave", true);
            }
            tabAdapter = new TabAdapter(getChildFragmentManager(), data, getActivity());
            viewPager.setAdapter(tabAdapter);
        }
        tabLayout.setupWithViewPager(viewPager);
        tabAdapter.notifyDataSetChanged();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dao.destory();
        EventBus.getDefault().unregister(this);
    }

}
