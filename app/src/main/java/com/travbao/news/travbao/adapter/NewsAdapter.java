package com.travbao.news.travbao.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.travbao.news.travbao.R;
import com.travbao.news.travbao.bean.NewsBean;

import java.util.List;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Created by Administrator on 2018/1/4.
 */

public class NewsAdapter extends BaseQuickAdapter<NewsBean.DataBean, BaseViewHolder> {


    public NewsAdapter(int layoutResId, @Nullable List<NewsBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsBean.DataBean item) {

        String replaceData = item.getNewsNcontent().replaceAll("\\s", "");

        helper.setText(R.id.first_news_title, item.getNewsHeadline()). setText(R.id.down_country, item.getNewscountry()).setText(R.id.down_newsFrom, item.getNewsFrom())
                .setText(R.id.down_time, item.getNewsNtime()).addOnClickListener(R.id.down_remove);

        if (item.getNewsImgurl1().equals("")) {
            ImageView newImage = helper.getView(R.id.first_news_iv);
            newImage.setVisibility(View.GONE);
        }else {
            Glide.with(mContext).load("http://192.168.100.75:8080/goabraod/" + item.getNewsImgurl1()).transition(withCrossFade()).into((ImageView) helper.getView(R.id.first_news_iv));
        }

        if (!item.getNewsImgurl1().equals("") && !item.getNewsImgurl2().equals("") && !item.getNewsImgurl3().equals("")) {
            Glide.with(mContext).load("http://192.168.1.149:8080/goabraod/" + item.getNewsImgurl1()).transition(withCrossFade()).into((ImageView) helper.getView(R.id.first_news_imag1));
            Glide.with(mContext).load("http://192.168.1.149:8080/goabraod/" + item.getNewsImgurl2()).transition(withCrossFade()).into((ImageView) helper.getView(R.id.first_news_imag2));
            Glide.with(mContext).load("http://192.168.1.149:8080/goabraod/" + item.getNewsImgurl3()).transition(withCrossFade()).into((ImageView) helper.getView(R.id.first_news_imag3));
            LinearLayout layout = helper.getView(R.id.image_layout);
            ImageView newImage = helper.getView(R.id.first_news_iv);
            layout.setVisibility(View.VISIBLE);
            newImage.setVisibility(View.GONE);
        } else {
            LinearLayout layout = helper.getView(R.id.image_layout);
            layout.setVisibility(View.GONE);
        }
    }
}
