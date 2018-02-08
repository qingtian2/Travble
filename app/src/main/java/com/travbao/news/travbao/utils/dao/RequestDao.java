package com.travbao.news.travbao.utils.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.travbao.news.travbao.bean.NewsBean;
import com.travbao.news.travbao.bean.TitleBean;

import java.util.ArrayList;
import java.util.List;

/**
 * description tab标签的数据应用类
 * Created by lijinbo on 18/1/1.
 */

public class RequestDao {

    private final SQLiteDatabase db;
    private Cursor cursor;
    private String categary;

    public RequestDao(Context context) {
        OpenDb openDb = new OpenDb(context);
        db = openDb.getWritableDatabase();
    }
    public void add(String newstype, int orderId, int selected ){
        db.execSQL("insert into request (newstype,orderId,selected) values (?,?,?)",new Object[]{newstype,orderId,selected});
    }

    public List<NewsBean.DataBean> query (){
        List<NewsBean.DataBean> list =new ArrayList<>();
        cursor = db.query("request", null, null, null, null, null, null);
        while(cursor.moveToNext()){
            String type=cursor.getString(cursor.getColumnIndex("newstype"));
            int orderId = cursor.getInt(cursor.getColumnIndex("orderId"));
            int selected = cursor.getInt(cursor.getColumnIndex("selected"));
            NewsBean.DataBean data = new NewsBean.DataBean();
            data.setNewstype(type);
            list.add(data);
        }
        return list;
    }
    public List<NewsBean.DataBean> queryType (String type){
        List<NewsBean.DataBean> list =new ArrayList<>();
        cursor = db.query("request", null, "type=?", new String[]{type}, null, null, null);
        while(cursor.moveToNext()){
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String categary = cursor.getString(cursor.getColumnIndex("categary"));
          //  String type=cursor.getString(cursor.getColumnIndex("newstype"));
            int orderId = cursor.getInt(cursor.getColumnIndex("orderId"));
            int selected = cursor.getInt(cursor.getColumnIndex("selected"));
            NewsBean.DataBean data = new NewsBean.DataBean();
            data.setNewstype(type);
            list.add(data);
        }
        return list;
    }
    public boolean update(String title){
        ContentValues values=new ContentValues();
        values.put("newstype",title);
        int request = db.update("request", values, "id=?", new String[]{String.valueOf(3)});
        if (request>0){
            return true;
        }else{
            return false;
        }

    }
    public String queryCate (String title){
        List<NewsBean.DataBean> list =new ArrayList<>();

        cursor = db.query("request", null, "title=?", new String[]{title}, null, null, null);
        while(cursor.moveToNext()){

            categary = cursor.getString(cursor.getColumnIndex("categary"));

        }
        return categary;
    }
    public List<TitleBean> queryInSe (int selected){
        List<TitleBean> list =new ArrayList<>();
        cursor = db.query("request", null, "selected=?", new String[]{selected+""}, null, null, null);
        while(cursor.moveToNext()){
            String type=cursor.getString(cursor.getColumnIndex("newstype"));
            int orderId = cursor.getInt(cursor.getColumnIndex("orderId"));
            TitleBean data = new TitleBean("");
            data.setNewstype(type);
            data.setOrderId(orderId);
            list.add(data);
        }
        return list;
    }
    public void destory(){
        if (db!=null){
            db.close();
        }
        if (cursor!=null){
            cursor.close();
        }
    }

    public void clearFeedTable() {
        String sql = "DELETE FROM " + "request" + ";";
        db.execSQL(sql);
        revertSeq();
    }

    private void revertSeq() {
        String sql = "update sqlite_sequence set seq=0 where name='"
                + "request" + "'";
        db.execSQL(sql);
    }

}
