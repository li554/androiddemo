package com.example.easyPermission.localWeb;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class JS {
    private Context context;

    public JS(Context context) {
        this.context = context;
    }
    @JavascriptInterface
    public String callFromJS(String js){
        Toast.makeText(context, "调用了android方法", Toast.LENGTH_SHORT).show();
        return "abc";
    }
}
