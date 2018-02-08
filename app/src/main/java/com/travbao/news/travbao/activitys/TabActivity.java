package com.travbao.news.travbao.activitys;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.Toast;
import com.travbao.news.travbao.R;
import com.travbao.news.travbao.base.BaseActivity;
import com.travbao.news.travbao.fragments.TabFragment;
import com.travbao.news.travbao.fragments.FourthFragment;
import com.travbao.news.travbao.fragments.SecondFragment;
import com.travbao.news.travbao.fragments.ThirdFragment;
import com.travbao.news.travbao.utils.NetUtil;
import com.travbao.news.travbao.utils.ToastUtil;
import com.travbao.news.travbao.weight.ButtomLayout;

import java.util.ArrayList;
import java.util.List;

import cn.jzvd.JZVideoPlayer;


public class TabActivity extends BaseActivity implements ButtomLayout.OnSelectListener {

    private FragmentManager fragmentManager;
    private ButtomLayout buttomLayout;
    private List<Fragment> fragments = new ArrayList<Fragment>();
    private TabFragment firstFragment;
    private static boolean isExit = false;

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        fragmentManager = getSupportFragmentManager();
        buttomLayout = findViewById(R.id.buttom_layout);
        if (!NetUtil.isNetworkAvailable(this)) {
            ToastUtil toastUtil = new ToastUtil(this, R.layout.toast_center_horizontal, "请检查您的网络");
            toastUtil.show();
        }
        buttomLayout.setOnSelectListener(this);
        createFragment(savedInstanceState);
        switchFragment(0);
        switchContent(firstFragment);
        buttomLayout.setOnSelectListener(this);
        accessFineLocation();
    }


    private void createFragment(Bundle savedInstanceState) {

        firstFragment = (TabFragment) fragmentManager.findFragmentByTag("TabFragment");
        SecondFragment secondFragment = (SecondFragment) fragmentManager.findFragmentByTag("SecondFragment");
        ThirdFragment thirdFragment = (ThirdFragment) fragmentManager.findFragmentByTag("ThirdFragment");
        FourthFragment fourthFragment = (FourthFragment) fragmentManager.findFragmentByTag("FourthFragment");

        if (firstFragment == null) {
            firstFragment = new TabFragment();
        }
        if (secondFragment == null) {
            secondFragment = new SecondFragment();
        }
        if (thirdFragment == null) {
            thirdFragment = new ThirdFragment();
        }
        if (fourthFragment == null) {
            fourthFragment = new FourthFragment();
        }
        fragments.add(firstFragment);
        fragments.add(secondFragment);
        fragments.add(thirdFragment);
        fragments.add(fourthFragment);
    }

    public void switchContent(Fragment to) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, to);
        //transaction.addToBackStack(null);
        transaction.commit();
    }

    public void switchFragment(int pos) {
        switchIFragment(pos, fragments, R.id.container);
    }

    @Override
    public void onSelect(int index) {
        switchFragment(index);
    }


    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }

    public void accessFineLocation() {
        requestPermission(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION}, "开启定位权限", new GrantedResult() {
            @Override
            public void onResult(boolean granted) {
                if (granted) {
                    //Toast.makeText(SpalshActivity.this,"权限已开启",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(TabActivity.this, "权限拒绝", Toast.LENGTH_SHORT).show();
                }
            }
        });
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            finish();
            System.exit(0);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHandler!=null){
            mHandler.removeCallbacksAndMessages(null);
        }
    }
}
