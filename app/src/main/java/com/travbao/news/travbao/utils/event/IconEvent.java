package com.travbao.news.travbao.utils.event;

/**
 * description eventbus 第三方登录传递换头像的消息
 * Created by qingtian on 2017/5/16.
 */

public class IconEvent {
  private  boolean  isIcon;

    public boolean isIcon() {
        return isIcon;
    }

    public void setIcon(boolean icon) {
        isIcon = icon;
    }

    public IconEvent(boolean isIcon) {
        this.isIcon = isIcon;
    }
}
