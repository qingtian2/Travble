package com.travbao.news.travbao.utils.event;

/**
 * Created by Administrator on 2018/2/5.
 */

public class DataEvent {

    private boolean man;

    public DataEvent(boolean tab) {
        this.man = tab;
    }

    public boolean isData() {
        return man;
    }

    public void setData(boolean man) {
        this.man = man;
    }
}
