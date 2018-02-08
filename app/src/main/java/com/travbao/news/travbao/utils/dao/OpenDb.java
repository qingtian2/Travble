package com.travbao.news.travbao.utils.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * description 数据库帮助类
 * Created by lijinbo on 18/1/1.
 */

public class OpenDb extends SQLiteOpenHelper {
    Context context;

    public OpenDb(Context context) {
        super(context, "News.db", null, 1);
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建表
        //tab标签的表,id 主键,title tab的标签,categary 请求数据用的关键词,type 判断用哪个bean类,orderid,频道管理排序用,selected,其他频道和我的频道的判断用
        db.execSQL("create table request (id integer primary key autoincrement,newstype varchar(20),orderId integer,selected integer)");
        //存type=4的数据,用于没有网络连接的时候显示数据,ID主键,title 新闻名称,comment评论数,author作者 hot是否是热点话题 date 时间戳
        db.execSQL("create table type (id integer primary key autoincrement,title varchar(100),comment integer,author varchar(20),hot integer,date integer,url text,imageurl text,tag_id text)");
        //存离线缓存的数据,列作用同上,url为跳转的webview页面
        db.execSQL("create table offline (id integer primary key autoincrement,title varchar(100),comment integer,author varchar(20),hot integer,date integer,url text,categary varchar(20))");
        db.execSQL("create table shoucang (id integer primary key autoincrement,title varchar(100),comment integer,author varchar(20),hot integer,date integer,url text,categary varchar(20),image_one text)");
        db.execSQL("create table shanchu (id integer primary key autoincrement,tag_id text)");
        db.execSQL("create table duanzi(id integer primary key autoincrement,content text,comment integer,praise integer,unpraise integer)");
        db.execSQL("create table type_three(id integer primary key autoincrement,content text,comment integer,praise integer,unpraise integer,url text)");
        db.execSQL("create table type_one(id integer primary key autoincrement,content text,comment integer,praise integer,unpraise integer,url text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //升级表

    }
}
