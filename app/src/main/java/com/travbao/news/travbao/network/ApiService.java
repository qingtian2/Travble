package com.travbao.news.travbao.network;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by lijinbo on 18/1/1.
 */

public interface  ApiService {
    @GET
    public Call<String> get1(@Url String url);

    @GET
    public Observable<String> get(@Url String url);

    @GET
    public Observable<String> get(@Url String url, @QueryMap Map<String, String> map);

    @FormUrlEncoded
    @POST
    public Observable<String> post(@Url String url, @FieldMap Map<String, String> map);

    //上传图片
    @Multipart
    @POST("MyInterface/userAction_uploadImage.action")
    Observable<String> uploadPhoto(@Part("user.file") MultipartBody file, @PartMap Map<String, String> map);

}
