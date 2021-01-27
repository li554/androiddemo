package com.example.animee.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.animee.MainActivity;
import com.example.animee.R;
import com.example.animee.StarPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity {

    private ViewPager guideVp;
    private int[] imageIds = {R.mipmap.loading1,R.mipmap.loading2,R.mipmap.loading3};
    private List<ImageView> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        guideVp = findViewById(R.id.guide_vp);
        list = new ArrayList<>();
        for (int i:imageIds){
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(i);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(lp);
            list.add(imageView);
        }
        guideVp.setAdapter(new StarPagerAdapter(this,list));
        ImageView view = list.get(list.size()-1);
        view.setOnClickListener(v -> {
            //点击跳转至MainActivity
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}