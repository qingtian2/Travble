package com.travbao.news.travbao.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.helin.loadinglayout.LoadingLayout;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.travbao.news.travbao.R;
import com.travbao.news.travbao.activitys.CityActivity;
import com.travbao.news.travbao.activitys.WebActivity;
import com.travbao.news.travbao.adapter.NewsAdapter;
import com.travbao.news.travbao.adapter.VideoAdapter;
import com.travbao.news.travbao.bean.NewsBean;
import com.travbao.news.travbao.network.BaseObserver;
import com.travbao.news.travbao.network.RetrofitManager;
import com.travbao.news.travbao.utils.Constants;
import com.travbao.news.travbao.utils.PreferencesUtils;
import com.travbao.news.travbao.utils.dao.RequestDao;
import com.travbao.news.travbao.utils.event.TabEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.jzvd.JZMediaManager;
import cn.jzvd.JZUtils;
import cn.jzvd.JZVideoPlayer;


public class NewsFragment extends Fragment {

    private SmartRefreshLayout refreshLayout;
    private RecyclerView newsRecycler;
    private NewsAdapter newsAdapter;
    private List<NewsBean.DataBean> newsList;
    private View view;
    private int mPagePosition;
    private static final int mPageSize = 10;
    private boolean hasMoreData;
    private boolean isViewCreated;
    private boolean isUIVisible;
    private LoadingLayout mLoading;
    private String titleType;
    private VideoAdapter videoAdapter;
    private LinearLayout newsCity;
    private String hotCity = null;
    private RequestDao requestDao;
    private String switchCity;
    private boolean update;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
        initData();
        initVideo();
    }

    private void initData() {
        mLoading.showLoading();
        lazyLoad();
    }

    public static NewsFragment newInstance(String type) {
        Bundle bundle = new Bundle();
        bundle.putString("newstype", type);
        NewsFragment blankFragment = new NewsFragment();
        blankFragment.setArguments(bundle);
        return blankFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.news_fragment, container, false);
        newsList = new ArrayList<>();
        requestDao = new RequestDao(getActivity());
        isViewCreated = true;
        newsRecycler = view.findViewById(R.id.first_recycler);
        mLoading = view.findViewById(R.id.news_loding);
        newsCity = view.findViewById(R.id.news_city);
        refreshLayout = view.findViewById(R.id.smartview);
        refreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));
        refreshLayout.setRefreshFooter(new BallPulseFooter(getActivity()).setSpinnerStyle(SpinnerStyle.Translate));
        titleType = getArguments().getString("newstype");
        switchCity = PreferencesUtils.getValueByKey(getActivity(), "switchCity", "");
        if (update) {
            titleType = switchCity;
        }
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isUIVisible = true;
            lazyLoad();
        } else {
            isUIVisible = false;
        }
    }

    private void lazyLoad() {
        if (isViewCreated && isUIVisible) {
            mPagePosition = 0;
            getData();
            isViewCreated = false;
            isUIVisible = false;
        }
    }

    private void initView() {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        newsRecycler.setLayoutManager(layoutManager);
        newsAdapter = new NewsAdapter(R.layout.first_news_item, newsList);
        videoAdapter = new VideoAdapter(R.layout.first_video_item, newsList);

        newsRecycler.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        switch (titleType) {
            case Constants.Title_VIDEO:
                newsRecycler.setAdapter(videoAdapter);
                break;
            case Constants.Title_City:
                newsRecycler.setAdapter(newsAdapter);
                newsAdapter.notifyDataSetChanged();
                newsCity.setVisibility(View.VISIBLE);
                newsCity.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), CityActivity.class);
                        startActivityForResult(intent, 0);
                        getActivity().overridePendingTransition(0, 0);
                    }
                });
            default:
                newsRecycler.setAdapter(newsAdapter);
                break;
        }

        newsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (newsList.size() > 0 && newsList.get(position).getNewsHeadlineUrl() != null) {
                    Intent intent = new Intent(getActivity(), WebActivity.class);
                    intent.putExtra("url", newsList.get(position).getNewsHeadlineUrl());
                    startActivity(intent);
                    getActivity().overridePendingTransition(0, 0);
                }
            }
        });

        newsAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                adapter.remove(position);
                adapter.notifyDataSetChanged();
            }
        });

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                newsList.clear();
                //mLoading.showLoading();
                JZVideoPlayer.releaseAllVideos();
                mPagePosition = 0;
                getData();
                newsAdapter.notifyDataSetChanged();
                refreshlayout.finishRefresh(2000);
                hasMoreData = true;
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                newsAdapter.notifyDataSetChanged();
                mPagePosition++;
                getData();
                refreshlayout.finishLoadmore(2000);
                hasMoreData = false;
            }
        });
    }

    private void getData() {
        Map map = new HashMap<String, String>();
        map.put("pageIndex", mPagePosition);
        map.put("pageSize", mPageSize);
        map.put("newstype", titleType);
        RetrofitManager.post(Constants.Title_URL, map, new BaseObserver<NewsBean>() {
            @Override
            public void onSuccess(String result) {
                Log.i("---", result);
                mLoading.showContent();
                Gson gson = new Gson();
                NewsBean newsBean = gson.fromJson(result, NewsBean.class);
                if (newsBean.getData() != null && newsBean.getData().size() > 0) {
                    List<NewsBean.DataBean> newsTotal = newsBean.getData();
                    newsList.addAll(newsTotal);
                    newsAdapter.notifyDataSetChanged();
                    videoAdapter.notifyDataSetChanged();
                } else {
                    mLoading.showEmpty();
                    if (newsBean.getData().size() == 0) {
                        videoAdapter.notifyDataSetChanged();
                        if (mPagePosition != 0) {
                            Toast.makeText(getContext(), "No More", Toast.LENGTH_SHORT).show();
                            mLoading.showContent();
                        }
                    }
                }
            }

            @Override
            public void onFailed(int code) {
                mLoading.showState();
                mLoading.setStateClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mLoading.showLoading();
                        getData();
                    }
                });
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 101 && requestCode == 0) {
            Bundle bundle = data.getExtras();
            if (bundle != null)
                hotCity = bundle.getString("hotcity");
            update = requestDao.update(hotCity);
            if (update) {
                EventBus.getDefault().post(new TabEvent(true, hotCity));
            }
        }
    }

    private void initVideo() {
        newsRecycler.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(View view) {
            }

            @Override
            public void onChildViewDetachedFromWindow(View view) {
                JZVideoPlayer jzvd = view.findViewById(R.id.videoplayer);
                if (jzvd != null && JZUtils.dataSourceObjectsContainsUri(jzvd.dataSourceObjects, JZMediaManager.getCurrentDataSource())) {
                    JZVideoPlayer.releaseAllVideos();
                }
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    //    public void onResume(){
//        super.onResume();
//        new Handler().postDelayed(new Runnable() {
//            public void run() {
//                titleType = getArguments().getString("newstype");
//                lazyLoad();
//                newsAdapter = new NewsAdapter(R.layout.first_news_item, list);
//                firstrecycler.setAdapter(newsAdapter);
//            }
//        }, 500);
//    }

}

