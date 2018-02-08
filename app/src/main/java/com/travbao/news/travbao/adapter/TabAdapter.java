package com.travbao.news.travbao.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.travbao.news.travbao.bean.TitleBean;
import com.travbao.news.travbao.fragments.NewsFragment;

import java.util.List;

/**
 * description tablayout下方viewpager的适配器
 * Created by lijinbo on 18/1/1.
 */

public class TabAdapter extends FragmentPagerAdapter {

    List<TitleBean>  data;

    public TabAdapter(FragmentManager fm, List<TitleBean> data, Context context) {
        super(fm);
        this.data=data;
    }

    @Override
    public Fragment getItem(int position) {
        NewsFragment blankFragment = NewsFragment.newInstance(data.get(position).getNewstype());
        return blankFragment;
    }

    @Override
    public int getCount() {
        return data != null ? data.size() : 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return data.get(position).getNewstype();
    }

}
