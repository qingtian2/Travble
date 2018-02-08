package com.travbao.news.travbao.base;

import android.os.Bundle;

import com.travbao.news.travbao.R;


public abstract  class BaseMvpActivity<V,T extends BasePresenter<V>> extends BaseActivity {


    public T presenter ;

    public abstract T initPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_mvp);
        presenter = initPresenter();
    }
    @Override
    protected void onResume() {
        super.onResume();
        presenter.attach((V) this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detach();
    }
}
