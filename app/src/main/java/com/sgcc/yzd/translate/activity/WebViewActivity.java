package com.sgcc.yzd.translate.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.blankj.utilcode.util.LogUtils;
import com.sgcc.yzd.translate.R;
import com.sgcc.yzd.translate.activity.ui.view.ProgressView;

import java.io.IOException;

public class WebViewActivity extends AppCompatActivity {

    private WebView mWebView;
    private String webviewUrl;
    private ProgressView mProgressView;
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        initViews();
    }

    public void initViews() {
        mProgressView = findViewById(R.id.progress);
        mWebView = findViewById(R.id.web_view);
        //去除滚动条白边
        mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        WebSettings mWebSettings = mWebView.getSettings();
        // 设置WebView属性，能够执行Javascript脚本
        mWebSettings.setJavaScriptEnabled(true);
        // 设置可以访问文件
        mWebSettings.setAllowFileAccess(true);
        // 设置编码格式
        mWebSettings.setDefaultTextEncodingName("utf-8");
        // 设置Web视图
        mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        //显示缩放按钮
        mWebSettings.setBuiltInZoomControls(false);
        //设置是否启用 DOM 存储 API。DOM 存储允许网页应用在本地存储数据，以便在用户离线时访问。
        mWebSettings.setDomStorageEnabled(true);
        //设置缓存模式为不使用缓存。这意味着每次加载网页时都会从网络下载，而不会使用本地缓存。
        mWebSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        //设置是否启用数据库存储 API。数据库存储允许网页应用在本地存储数据，类似于 DOM 存储。
        mWebSettings.setDatabaseEnabled(true);
        //设置是否启用应用缓存 API。应用缓存允许网页应用在本地存储资源，以便在用户离线时访问。
        mWebSettings.setAppCacheEnabled(true);
        //设置是否支持缩放。如果设置为 true，用户可以通过手势缩放 WebView 中的内容。
        mWebSettings.setSupportZoom(true);
        // 设置此属性,可任意比例缩放,设置webview推荐使用的窗口
        mWebSettings.setUseWideViewPort(false);
        // 设置webview加载的页面的模式,缩放至屏幕的大小
        mWebSettings.setLoadWithOverviewMode(true);
//        String userAgent = mWebSettings.getUserAgentString() + "-STDL-APP-ANDROID";
//        mWebSettings.setUserAgentString(userAgent);
        mWebView.setWebChromeClient(mChromeClient);
        mWebView.setWebViewClient(mWebViewClient);
//        mWebView.addJavascriptInterface(new JsInterface(this), "AndroidWebView");
        mWebView.loadUrl("http://192.168.11.201:8848/pad/index.html#/");
    }

    private WebChromeClient mChromeClient = new WebChromeClient() {

        public void onProgressChanged(WebView view, int progress) {
            super.onProgressChanged(view, progress);
            if (progress == 100) {
                //加载完毕进度条消失
                mProgressView.setVisibility(View.GONE);
            } else {
                //更新进度
                mProgressView.setProgress(progress);
            }
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
        }

        @Override
        public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
            return true;
        }

        @Override
        public boolean onJsConfirm(WebView view, String url, String message, final JsResult result) {
            return true;
        }

        @Override
        public boolean onJsPrompt(WebView view, String url, String message, String defaultValue,
                                  final JsPromptResult result) {
            return true;
        }

    };

    private WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, final String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String
                failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
        }
    };

    @Override
    public void onDestroy() {
        if (mWebView != null) {
            mWebView.stopLoading();
            // 退出时调用此方法，移除绑定的服务，否则某些特定系统会报错
            mWebView.getSettings().setJavaScriptEnabled(false);
            WebStorage.getInstance().deleteAllData(); //清空WebView的localStorage
            mWebView.setWebChromeClient(null);
            mWebView.setWebViewClient(null);
            mWebView.clearHistory();
            mWebView.clearView();
            mWebView.removeAllViews();
            try {
                mWebView.destroy();
            } catch (Throwable ex) {

            }
        }
        super.onDestroy();
    }
}