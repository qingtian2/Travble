package com.travbao.news.travbao.presenter;


import com.travbao.news.travbao.base.BasePresenter;
import com.travbao.news.travbao.modle.FourthFragmentModel;
import com.travbao.news.travbao.view.FourthFragmentView;

/**
 * Created by lijinbo on 18/1/1.
 */

public class FourthFragmentPresenter extends BasePresenter<FourthFragmentView> {

    private FourthFragmentModel fourthFragmentModel ;

    public FourthFragmentPresenter(){
        fourthFragmentModel = new FourthFragmentModel() {
        };
    }



}
