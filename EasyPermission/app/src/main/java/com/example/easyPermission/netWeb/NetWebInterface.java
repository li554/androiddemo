package com.example.easyPermission.netWeb;

import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;

public class NetWebInterface {
    private Context context;

    public NetWebInterface(Context context) {
        this.context = context;
    }
    @JavascriptInterface
    public void openImage(String[] imgs,int position){
        Intent intent = new Intent(context,ImageViewActivity.class);
        intent.putExtra("srcs",imgs);
        intent.putExtra("position",position);
        context.startActivity(intent);
    }
}
