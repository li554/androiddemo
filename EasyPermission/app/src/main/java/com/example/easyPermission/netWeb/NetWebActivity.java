package com.example.easyPermission.netWeb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.easyPermission.R;

public class NetWebActivity extends AppCompatActivity {

    private WebView netWeb;
    private static final String url = "http://www.moviebase.cn/uread/app/viewArt/viewArt-0985242225a84c7eabe3eb62c9fa91bf.html?appVersion=1.7.0&osType=null&platform=2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_web);
        netWeb = findViewById(R.id.net_web);

        WebSettings settings = netWeb.getSettings();

        settings.setAllowFileAccess(true);
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
//      加载网页
        netWeb.loadUrl(url);
        netWeb.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                //添加图片的监听
                addImageViewListener(view);
            }
        });
        netWeb.setWebChromeClient(new WebChromeClient());
        //添加通道
        netWeb.addJavascriptInterface(new NetWebInterface(this),"listener");
    }

    private void addImageViewListener(WebView view) {
        netWeb.loadUrl("javascript:(function(){\n" +
                "            var imgs = document.getElementsByTagName(\"img\");\n" +
                "            var srcs = [];\n" +
                "            for(let i=0;i<imgs.length;i++){\n" +
                "               srcs[i] = imgs[i].src;\n" +
                "            }\n" +
                "            for(let i=0;i<imgs.length;i++){\n" +
                "               imgs[i].onclick = function(){\n" +
                "                    window.listener.openImage(srcs,i);\n" +
                "               }\n" +
                "            }\n" +
                "        })()");
    }
}