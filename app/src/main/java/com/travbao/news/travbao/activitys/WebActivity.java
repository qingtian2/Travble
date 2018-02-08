package com.travbao.news.travbao.activitys;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.travbao.news.travbao.R;
import com.travbao.news.travbao.base.BaseActivity;

public class WebActivity extends BaseActivity {

    WebView webView;
    Button pub_back;
    ProgressBar mProgressBar;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        initView();
    }

    private void initView() {
        webView = findViewById(R.id.webview);
        pub_back = findViewById(R.id.pub_title_back);
        tv = findViewById(R.id.pub_title);
        mProgressBar = findViewById(R.id.pb_progress);
        pub_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backIActivity(WebActivity.this);
            }
        });

        WebSettings webSettings = webView.getSettings();
        String url = getIntent().getStringExtra("url");
        System.out.println(url);
        //WebView加载web资源
        webView.loadUrl(url);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setSupportZoom(true);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);

                return true;
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {//监听网页加载
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    // 网页加载完成
                    mProgressBar.setVisibility(View.GONE);
                } else {
                    // 加载中
                    mProgressBar.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
    }

    /**
     * 按返回键时， 不退出程序而是返回WebView的上一页面
     */
    @Override
    public void onBackPressed() {
        if (webView.canGoBack())
            webView.goBack();
        else {
            super.onBackPressed();
        }
    }
}
