package com.travbao.news.travbao.bean;

/**
 * Created by Administrator on 2018/1/23.
 */

public class TitleBean {

    private  int orderId;
    private  int  selected;
    private String newstype;



    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getSelected() {
        return selected;
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }

    public String getNewstype() {
        return newstype;
    }

    public void setNewstype(String newstype) {
        this.newstype = newstype;
    }

    public TitleBean(String newstype) {
        this.newstype = newstype;
    }
}
