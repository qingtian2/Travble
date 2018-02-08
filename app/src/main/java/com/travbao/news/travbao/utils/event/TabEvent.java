package com.travbao.news.travbao.utils.event;

/**
 * description 城市列表的适配器,改变tab上"北京"位置的tab标签为在城市列表中所选择的城市
 * Created by qingtian on 2017/5/18.
 */

public class TabEvent {
    private boolean tab;//是否改变
    private String result;//选择结果

    public TabEvent(boolean tab, String result) {
        this.tab = tab;
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public boolean isTab() {
        return tab;
    }

    public void setTab(boolean tab) {
        this.tab = tab;
    }
}
