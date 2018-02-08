package com.travbao.news.travbao.base;

/**
 * Created by lijinbo on 18/1/1.
 */

public  abstract  class BasePresenter<T> {


    public T view ;


    public void attach(T view){

        this.view = view ;

    }

    public void detach(){

        this.view = null ;

    }



}
