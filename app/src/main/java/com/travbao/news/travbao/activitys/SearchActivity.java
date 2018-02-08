package com.travbao.news.travbao.activitys;


import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.travbao.news.travbao.R;
import com.travbao.news.travbao.base.BaseActivity;


public class SearchActivity extends BaseActivity {

    private SearchView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();

    }

    private void initView() {
        mSearchView = findViewById(R.id.search_view);
        View btnBack = findViewById(R.id.btn_back);
        View btnSearch = findViewById(R.id.search_go);
        mSearchView.setSubmitButtonEnabled(false);

        //设置字体颜色
//        EditText searchEditText = (EditText) mSearchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
//        searchEditText.setTextColor(ContextCompat.getColor(this, R.color.bule));
//        searchEditText.setHintTextColor(ContextCompat.getColor(this, R.color.color_black));
//        searchEditText.setTextSize(15);
        //修改光标颜色
//        Field mCursorDrawableRes = null;
//        try {
//            mCursorDrawableRes = TextView.class.getDeclaredField("mCursorDrawableRes");
//            mCursorDrawableRes.setAccessible(true);
//            mCursorDrawableRes.set(searchEditText, R.drawable.search_guan);
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
                finish();
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSearch();
            }
        });
    }

    private void doSearch() {
        String keyword = mSearchView.getQuery().toString().trim();
        if (!TextUtils.isEmpty(keyword) && keyword.length() > 1) {
            Toast.makeText(getApplication(),keyword,Toast.LENGTH_SHORT).show();
           // Log.i("--输入内容--", keyword);
        }
        mSearchView.clearFocus();
    }

    private void back() {
        if (mSearchView != null) {
            mSearchView.clearFocus();
        }
    }
}
