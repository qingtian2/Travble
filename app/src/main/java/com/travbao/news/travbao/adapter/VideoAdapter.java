package com.travbao.news.travbao.adapter;

import android.support.annotation.Nullable;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.travbao.news.travbao.R;
import com.travbao.news.travbao.bean.NewsBean;
import java.util.List;
import cn.jzvd.JZVideoPlayerStandard;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;


/**
 * Created by Administrator on 2018/1/26.
 */

public class VideoAdapter extends BaseQuickAdapter<NewsBean.DataBean, BaseViewHolder> {


    public VideoAdapter(int layoutResId, @Nullable List<NewsBean.DataBean> data) {
        super(layoutResId, data);

    }

    @Override
    protected void convert(BaseViewHolder helper, NewsBean.DataBean item) {
       // "http://219.238.7.66/mp4files/4100000003406F25/clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"
        JZVideoPlayerStandard jzVideoPlayer= helper.getView(R.id.videoplayer);
        helper.setText(R.id.down_country, item.getNewscountry()).setText(R.id.down_newsFrom, item.getNewsFrom())
                .setText(R.id.down_time, item.getNewsNtime());
        jzVideoPlayer.setUp(item.getNewsNcontent()
                , JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, item.getNewsHeadline());
          Glide.with(mContext).load("http://192.168.1.149:8080/goabraod/" +item.getNewsImgurl1()).transition(withCrossFade()).into(((jzVideoPlayer.thumbImageView)));
    }

}
