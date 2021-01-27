package com.example.animee.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.animee.bean.StarInfoBean;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AssetsUtils {
    public static Map<String,Bitmap>logoImgMap;
    public static Map<String,Bitmap>contentLogoImgMap;
    //  读取json字符串的方法
    public static String getJsonFromAssets(Context context, String filename) throws IOException {
        AssetManager am = context.getAssets();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        //获取输入流
        InputStream is = am.open(filename);
        byte[] buf = new byte[1024];
        int hasRead = 0;
        while((hasRead = is.read(buf))!=-1){
            baos.write(buf,0,hasRead);
        }
        String msg = baos.toString();
        is.close();
        return msg;
    }
    public static Bitmap getBitmapFromAssets(Context context,String filename) throws IOException {
        Bitmap bitmap = null;
        AssetManager am = context.getAssets();
        InputStream is = am.open(filename);
        bitmap = BitmapFactory.decodeStream(is);
        is.close();
        return bitmap;
    }
    public static void saveBitmapFromAssets(Context context, StarInfoBean infoBean) throws IOException {
        logoImgMap = new HashMap<>();
        contentLogoImgMap = new HashMap<>();
        List<StarInfoBean.StarinfoBean> beanList = infoBean.getStarinfo();
        for (StarInfoBean.StarinfoBean item:beanList){
            String logoName = item.getLogoName();
            Bitmap logoBitmap = getBitmapFromAssets(context,"xzlogo/"+logoName+".png");
            logoImgMap.put(logoName,logoBitmap);
            Bitmap contentLogoBitmap = getBitmapFromAssets(context,"xzcontentlogo/"+logoName+".png");
            contentLogoImgMap.put(logoName,contentLogoBitmap);
        }
    }
}
