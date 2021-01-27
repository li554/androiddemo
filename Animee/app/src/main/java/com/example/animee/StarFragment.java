package com.example.animee;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.animee.bean.StarInfoBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StarFragment extends Fragment {

    private ViewPager starVp;
    private GridView starGv;
    private LinearLayout pointLayout;
    private List<StarInfoBean.StarinfoBean> mDatas;

    //将轮播图片id存入数组
    private int[] imgIds = {R.mipmap.pic_guanggao,R.mipmap.pic_star};
    //声明ViewPager的数据源
    private List<ImageView>ivList;
    //声明管理指示器小圆点的集合
    private List<ImageView>pointList;
    //完成定时装置，实现自动滑动的效果
    private Handler handler = new Handler();
    private final Runnable runnable = () -> {
        //TODO: Replace this with your own logic
        //获取viewpager显示页面
        int currentItem = starVp.getCurrentItem();
        //判断currentItem是否是最后一条,如果是currentItem+1,否则currentItem=0
        if (currentItem==ivList.size()-1){
            currentItem=0;
        }else{
            currentItem=currentItem+1;
        }
        starVp.setCurrentItem(currentItem);
        handler.postDelayed(this.runnable,5000);
    };
    public StarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        StarInfoBean infoBean = (StarInfoBean) getArguments().getSerializable("info");

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_star, container, false);
        //初始化控件
        starGv = view.findViewById(R.id.starfrag_gv);
        starVp = view.findViewById(R.id.starfrag_vp);
        pointLayout = view.findViewById(R.id.starfrag_layout);
        //获得星座信息数据
        mDatas = infoBean.getStarinfo();
        //创建GridView适配器
        starGv.setAdapter(new StarBaseAdapter(getContext(), mDatas));
        //监听ViewPager切换，使圆点指示器随之切换
        starVp.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                //先把所有的page设为normal
                for (ImageView item:pointList){
                    item.setImageResource(R.mipmap.point_normal);
                }
                pointList.get(position).setImageResource(R.mipmap.point_focus);
            }
        });
        starGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StarInfoBean.StarinfoBean bean = mDatas.get(position);
                Intent intent = new Intent(getContext(), StarDetailActivity.class);
                intent.putExtra("star", bean);
                startActivity(intent);
            }
        });
        //设置ViewPager的数据源，顺便
        ivList = new ArrayList<>();
        pointList = new ArrayList<>();
        for (int i:imgIds){
            ImageView iv = new ImageView(getContext());
            iv.setImageResource(i);
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //设置图片的宽高
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            iv.setLayoutParams(lp);
            //将图片View加载到集合中
            ivList.add(iv);

            ImageView point = new ImageView(getContext());
            point.setImageResource(R.mipmap.point_normal);
            //设置point的宽高
            LinearLayout.LayoutParams plp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            plp.setMargins(20,0,0,0);
            point.setLayoutParams(plp);
            //将point添加到布局
            pointLayout.addView(point);
            //
            pointList.add(point);
        }
        //默认第一个小圆点是获取焦点的状态
        pointList.get(0).setImageResource(R.mipmap.point_focus);
        starVp.setAdapter(new StarPagerAdapter(getContext(),ivList));
        //设置定时器,延迟5秒钟发送一条消息，通知可以切换图片
        handler.postDelayed(runnable,5000);
        return view;
    }
}