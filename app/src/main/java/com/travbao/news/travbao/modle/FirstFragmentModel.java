package com.travbao.news.travbao.modle;

/**
 * Created by lijinbo on 18/1/1.
 */

public interface FirstFragmentModel {


    public void getData(long currenttimer, DataListener dataListener);
    public interface DataListener{
       // public void onSuccess(IndexBean indexBean, long currenttimer);
       // public void onFailed(int code, long currenttimer);
    }

}
