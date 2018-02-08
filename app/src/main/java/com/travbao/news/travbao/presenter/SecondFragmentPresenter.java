package com.travbao.news.travbao.presenter;


import com.travbao.news.travbao.base.BasePresenter;
import com.travbao.news.travbao.modle.SecondFragmentModel;
import com.travbao.news.travbao.view.SecondFragmentView;

/**
 * Created by lijinbo on 18/1/1.
 */

public class SecondFragmentPresenter extends BasePresenter<SecondFragmentView> {

    private SecondFragmentModel secondFragmentModel ;

    public SecondFragmentPresenter(){
        secondFragmentModel = new SecondFragmentModel() {
        };
    }


}
