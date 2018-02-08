package com.travbao.news.travbao.modle;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lijinbo on 18/1/1.
 */

public class FirstFragmentModelImpl implements FirstFragmentModel {


    @Override
    public void getData(final long currenttimer, final DataListener listener) {

        Map<String,String> map = new HashMap<String, String>();
//        map.put("pageIndex",page+"");
//        map.put("pageSize","20");
        map.put("user.currenttimer",currenttimer+"");

//        RetrofitManager.post(Constants.ALL_USER, map, new BaseObserver1<IndexBean>("age") {
//            @Override
//            public void onSuccess(IndexBean result, String tag) {
//
//            }
//
//            @Override
//            public void onFailed(int code) {
//
//            }
//        });



//        public void getData1(Map map,String tag) {
//
////            Map<String,String> map = new HashMap<String, String>();
////        map.put("pageIndex",page+"");
////        map.put("pageSize","20");
////            map.put("user.currenttimer",currenttimer+"");
//
//            RetrofitManager.post(Constants.ALL_USER, map, new BaseObserver1<IndexBean>(tag) {
//                @Override
//                public void onSuccess(IndexBean result, String tag) {
//
//                }
//
//                @Override
//                public void onFailed(int code) {
//
//                }
//            });

    }
}
