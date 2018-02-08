package com.travbao.news.travbao.activitys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.travbao.news.travbao.R;
import com.travbao.news.travbao.adapter.CityAdapter;
import com.travbao.news.travbao.adapter.SortAdapter;
import com.travbao.news.travbao.bean.CitySortModel;

import com.travbao.news.travbao.utils.PinyinUtils;
import com.travbao.news.travbao.view.EditTextWithDel;
import com.travbao.news.travbao.view.PinyinComparator;
import com.travbao.news.travbao.view.SideBar;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * description 城市列表的类
 * Created by lijinbo on 2017/5/17.
 */

public class CityActivity extends Activity {
    private ListView sortListView;
    private SideBar sideBar;
    private TextView dialog, mTvTitle;
    private SortAdapter adapter;
    private EditTextWithDel mEtCityName;
    private List<CitySortModel> SourceDateList;
    private ImageView iv;
    private CityAdapter adapter1;
    private GridView mGvCity;
    private View headView;
    private ArrayList<String> cityList;
    public LocationClient mLocationClient = null;
    private TextView cityName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyLocationListenner myListener = new MyLocationListenner();

        setContentView(R.layout.city_item);
        mLocationClient = new LocationClient(this);
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
        //注册监听函数
        initLocation();
        initViews();
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mGvCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String name = cityList.get(position);
                hidenKeyBoard();
                Intent intent=getIntent();
          ;     Bundle bundle =new Bundle();
                bundle.putString("hotcity",name);
                intent.putExtras(bundle);
                setResult(101,intent);
                finish();;
                overridePendingTransition(0, 0);
            }
        });
    }


    //设置百度定位参数
    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //国外 WGS84
        option.setCoorType("WGS84");
        // option.setScanSpan(1000);
        option.setOpenGps(true);
        option.setLocationNotify(true);
        option.setIgnoreKillProcess(false);
        option.SetIgnoreCacheException(false);
        option.setWifiCacheTimeOut(5 * 60 * 1000);
        option.setEnableSimulateGps(false);
        option.setIsNeedAddress(true);
        mLocationClient.setLocOption(option);
        mLocationClient.start();
    }

    //定位城市
    private void initViews() {

        headView = getLayoutInflater().inflate(R.layout.city_head_item, null);
        cityName = headView.findViewById(R.id.btn_city_name);
        // builder = DialogUtils.setDialog(this);
        mGvCity = headView.findViewById(R.id.gv_hot_city);
        mEtCityName = findViewById(R.id.et_search);
        sideBar = findViewById(R.id.sidrbar);
        dialog = findViewById(R.id.dialog);
        mTvTitle = findViewById(R.id.tv_title);
        sortListView = findViewById(R.id.country_lvcountry);
        iv = findViewById(R.id.city_iv_back);
        initDatas();
        initEvents();
        setAdapter();
    }

    private void setAdapter() {
        String[] datas = getResources().getStringArray(R.array.city);
        cityList = new ArrayList<>();
        for (int i = 0; i < datas.length; i++) {
            cityList.add(datas[i]);
        }
        adapter1 = new CityAdapter(getApplicationContext(), R.layout.gridview_item, cityList);
        mGvCity.setAdapter(adapter1);
        SourceDateList = new ArrayList<>();
        sortListView.addHeaderView(headView);
        adapter = new SortAdapter(CityActivity.this, SourceDateList);
        sortListView.setAdapter(adapter);

        //设置数据
        SourceDateList.add(new CitySortModel("老挝", "L"));
        SourceDateList.add(new CitySortModel("缅甸", "M"));
        SourceDateList.add(new CitySortModel("马来西亚", "M"));
        SourceDateList.add(new CitySortModel("泰国", "T"));
        SourceDateList.add(new CitySortModel("新加坡", "X"));
        SourceDateList.add(new CitySortModel("越南", "Y"));
        SourceDateList.add(new CitySortModel("文莱", "W"));
        SourceDateList.add(new CitySortModel("菲律宾", "F"));
        SourceDateList.add(new CitySortModel("印度尼西亚", "Y"));
        SourceDateList.add(new CitySortModel("柬埔寨", "J"));
        SourceDateList.add(new CitySortModel("中国", "Z"));

        Collections.sort(SourceDateList, new PinyinComparator());
        adapter.notifyDataSetChanged();

    }

    private void initEvents() {
        //设置右侧触摸监听
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                //该字母首次出现的位置
                int position = adapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    sortListView.setSelection(position + 1);
                }
            }
        });

        //ListView的点击事件
        sortListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                String name = ((CitySortModel) adapter.getItem(position - 1)).getName();
                Intent intent=getIntent();
                Bundle bundle =new Bundle();
                bundle.putString("hotcity",name);
                intent.putExtras(bundle);
                setResult(101,intent);
                finish();
                hidenKeyBoard();
                overridePendingTransition(0, 0);
            }
        });

        //根据输入框输入值的改变来过滤搜索
        mEtCityName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
                filterData(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void initDatas() {
        sideBar.setTextView(dialog);
    }

    /**
     * 根据输入框中的值来过滤数据并更新ListView
     *
     * @param filterStr
     */
    private void filterData(String filterStr) {
        List<CitySortModel> mSortList = new ArrayList<>();
        if (TextUtils.isEmpty(filterStr)) {
            mSortList = SourceDateList;
        } else {
            mSortList.clear();
            for (CitySortModel sortModel : SourceDateList) {
                String name = sortModel.getName();
                if (name.toUpperCase().indexOf(filterStr.toString().toUpperCase()) != -1 || PinyinUtils.getPingYin(name).toUpperCase().startsWith(filterStr.toString().toUpperCase())) {
                    mSortList.add(sortModel);
                }
            }
        }
        // 根据a-z进行排序
        Collections.sort(mSortList, new PinyinComparator());
        adapter.updateListView(mSortList);
    }


    public void hidenKeyBoard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mEtCityName.getWindowToken(), 0);
    }

    public void showKeyBoard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(mEtCityName, InputMethodManager.SHOW_FORCED);
    }

    /**
     * 定位相关:定位SDK监听函数
     */
    public class MyLocationListenner implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {//得到未知信息
            //得到城市
            final String curCity = location.getCountry();
            if (TextUtils.isEmpty(curCity)) {
                cityName.setText("正在定位");
            } else {
                cityName.setText(curCity);
                cityName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=getIntent();
                        Bundle bundle =new Bundle();
                        bundle.putString("hotcity",curCity);
                        intent.putExtras(bundle);
                        setResult(101,intent);
                        finish();
                        overridePendingTransition(0, 0);
                    }
                });
            }


        }

        public void onReceivePoi(BDLocation poiLocation) {

        }
    }


}
