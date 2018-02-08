package com.travbao.news.travbao.presenter;


import com.travbao.news.travbao.base.BasePresenter;
import com.travbao.news.travbao.modle.FirstFragmentModel;
import com.travbao.news.travbao.modle.FirstFragmentModelImpl;
import com.travbao.news.travbao.view.NewsFragmentView;

/**
 * Created by lijinbo on 18/1/1.
 */

public class NewsFragmentPresenter extends BasePresenter<NewsFragmentView> {

    private FirstFragmentModel firstFragmentModel ;

    public NewsFragmentPresenter(){
        firstFragmentModel = new FirstFragmentModelImpl();
    }
}
