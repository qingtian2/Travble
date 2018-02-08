package com.travbao.news.travbao.activitys;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.travbao.news.travbao.R;
import com.travbao.news.travbao.base.BaseActivity;

import java.util.Timer;
import java.util.TimerTask;


public class SpalshActivity extends BaseActivity {

    TextView mSkipReal;
    ImageView mSplashView;
    private int recLen = 3;
    Timer timer = new Timer();
    LinearLayout splash_Layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //全屏显示图片
       // findViewById(R.id.splash_layout).setSystemUiVisibility(View.INVISIBLE);;
        mSplashView = findViewById(R.id.splash_view);
        mSkipReal = findViewById(R.id.skip_real);
        splash_Layout=findViewById(R.id.splash_back);
        timer.schedule(task, 1000, 1000);

        mSkipReal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.cancel();
                toIActivity(TabActivity.class, null, 0);
                finish();
                overridePendingTransition(0, 0);
            }
        });
    }

    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    mSkipReal.setText(recLen+" 跳过");
                    if (recLen < 1) {
                        timer.cancel();
                        mSkipReal.setVisibility(View.GONE);
                        toIActivity(TabActivity.class, null, 0);
                        finish();
                    }
            }
        }
    };

    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            recLen --;
            Message message = new Message();
            message.what = 1;
            handler.sendMessage(message);
        }
    };

    /**
     * 屏蔽物理返回键
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    protected void onDestroy() {
        if (handler != null) {
            //If token is null, all callbacks and messages will be removed.
            handler.removeCallbacksAndMessages(null);
        }
        super.onDestroy();
    }

}