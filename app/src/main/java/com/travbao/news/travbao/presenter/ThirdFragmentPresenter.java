package com.travbao.news.travbao.presenter;


import com.travbao.news.travbao.base.BasePresenter;
import com.travbao.news.travbao.modle.ThirdFragmentModel;
import com.travbao.news.travbao.view.ThirdFragmentView;

/**
 * Created by lijinbo on 18/1/1.
 */

public class ThirdFragmentPresenter extends BasePresenter<ThirdFragmentView> {

    private ThirdFragmentModel firstFragmentModel ;

    public ThirdFragmentPresenter(){
        firstFragmentModel = new ThirdFragmentModel() {
        };
    }


}
