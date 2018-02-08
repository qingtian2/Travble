package com.travbao.news.travbao.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by lijinbo on 18/1/1.
 */

public class OkHttpManager {

    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .addNetworkInterceptor(new LoggingInterceptor())
            .build();

    private static volatile OkHttpManager instance = null ;

    private OkHttpManager(){

    }

    public static OkHttpManager getInstance(){
        if(instance == null){
            synchronized (OkHttpManager.class){
                if(instance == null){
                    instance = new OkHttpManager();
                }
            }
        }
        return instance;
    }

}
