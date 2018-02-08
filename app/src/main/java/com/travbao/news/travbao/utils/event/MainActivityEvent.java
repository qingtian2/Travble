package com.travbao.news.travbao.utils.event;

/**日夜模式
 * Created by qingtian on 17/5/11.
 */

public class MainActivityEvent {

    public boolean white ;

    public MainActivityEvent(boolean white){
        this.white = white;
    }


    public boolean isWhite() {
        return white;
    }

    public void setWhite(boolean white) {
        this.white = white;
    }
}
