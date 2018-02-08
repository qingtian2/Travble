package com.travbao.news.travbao.utils.event;

/**
 * description 频道管理传递频道已经修改,重新加载viewpager的适配器
 * Created by qingtian on 2017/5/18.
 */

public class ManEvent {
    private boolean man;

    public ManEvent(boolean tab) {
        this.man = tab;
    }

    public boolean isMan() {
        return man;
    }

    public void setMan(boolean man) {
        this.man = man;
    }
}
