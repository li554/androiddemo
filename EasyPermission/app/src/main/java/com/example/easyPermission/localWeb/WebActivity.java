package com.example.easyPermission.localWeb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.easyPermission.R;

public class WebActivity extends AppCompatActivity {

    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        webView = findViewById(R.id.local_web);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setAllowFileAccess(true);
        settings.setAllowFileAccessFromFileURLs(true);

        webView.loadUrl("file:///android_asset/webView/test.html");
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl("file:///android_asset/webView/test.html");
                return true;
            }
        });
        //设置处理js代码的客户端
        webView.setWebChromeClient(new WebChromeClient());
        //设置js中调用android函数
        webView.addJavascriptInterface(new JS(this),"animee");
    }

    public void LoadJSClick(View view) {
        webView.loadUrl("javascript:testAlert()");
    }
}