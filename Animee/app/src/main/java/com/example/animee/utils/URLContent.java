package com.example.animee.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class URLContent {
    public static String getPartnerURL(String man,String woman) throws UnsupportedEncodingException {
        man = man.replace("座","");
        woman = woman.replace("座","");
        man = URLEncoder.encode(man, "UTF-8");
        woman = URLEncoder.encode(woman,"UTF-8");
        String url = "http://apis.juhe.cn/xzpd/query?men="+man+"&women="+woman+"&key=ccb38d1bca41b85d834835b334b78e2b";
        System.out.println(url);
        return url;
    }
    public static String getLuckURL(String consName,String type) throws UnsupportedEncodingException {
        consName = URLEncoder.encode(consName,"UTF-8");
        type = URLEncoder.encode(type,"UTF-8");
        return "http://web.juhe.cn:8080/constellation/getAll?consName="+consName+"&type="+type+"&key=f1f1f42c73b0915ba682c1ab5343e8b2";
    }
    public static String getJSONFromNet(String path){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            URL url = new URL(path);
            //获取网络连接对象
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            int hasRead = 0;
            byte[] buf = new byte[1024];
            while ((hasRead=is.read(buf))!=-1){
                outputStream.write(buf,0,hasRead);
            }
            is.close();
            return outputStream.toString();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
