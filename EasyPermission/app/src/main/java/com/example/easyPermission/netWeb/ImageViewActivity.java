package com.example.easyPermission.netWeb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.easyPermission.R;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ImageViewActivity extends AppCompatActivity {

    private ViewPager vp;
    private List<ImageView> ivList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);
        vp = findViewById(R.id.image_vp);
        //获取传入数据
        Intent intent = getIntent();
        String[] srcs = intent.getStringArrayExtra("srcs");
        int position = intent.getIntExtra("position",0);
        initIvList(srcs);
        vp.setAdapter(new ImageVpAdapter(this,ivList));
        vp.setCurrentItem(position);
    }

    private void initIvList(String[] srcs) {
        ivList = new ArrayList<>();
        for (String url:srcs){
            ImageView imageView = new ImageView(ImageViewActivity.this);
            Picasso.with(this).load(url).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.CENTER);
            ivList.add(imageView);
        }
    }
    //加载图片
    public Bitmap getURLImage(String url) {
        Bitmap bmp = null;
        try {
            URL myurl = new URL(url);
            // 获得连接
            HttpURLConnection conn = (HttpURLConnection) myurl.openConnection();
            conn.setConnectTimeout(6000);//设置超时
            conn.setDoInput(true);
            conn.setUseCaches(false);//不缓存
            conn.connect();
            InputStream is = conn.getInputStream();//获得图片的数据流
            bmp = BitmapFactory.decodeStream(is);//读取图像数据
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bmp;
    }
}