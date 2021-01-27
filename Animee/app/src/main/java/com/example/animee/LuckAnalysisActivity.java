package com.example.animee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.animee.bean.LuckBean;
import com.example.animee.bean.LuckItemBean;
import com.example.animee.bean.LuckItemBean;
import com.example.animee.utils.LoadAsyncTask;
import com.example.animee.utils.URLContent;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class LuckAnalysisActivity extends AppCompatActivity implements LoadAsyncTask.OnGetNetDataListener {

    private ListView luckLv;
    private TextView nameTv;
    private ImageView backTv;
    private List<LuckItemBean>mDatas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luck_analysis);
        //初始化控件
        luckLv = findViewById(R.id.luck_frag_lv);
        nameTv = findViewById(R.id.title_layout_tv_title);
        backTv = findViewById(R.id.title_layout_iv_back);
        //获取传入数据
        Intent intent = getIntent();
        String consName = intent.getStringExtra("consName");
        //获取网络数据
        LoadAsyncTask task = new LoadAsyncTask(this, this);
        try {
            String url = URLContent.getLuckURL(consName,"year");
            task.execute(url);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //将数据显示到视图
        nameTv.setText(consName);
        //绑定事件
        backTv.setOnClickListener(v->finish());
    }

    @Override
    public void onSuccess(String json) {
        Log.d("LuckAnalysis", "onSuccess: "+json);
        LuckBean bean = new Gson().fromJson(json, LuckBean.class);
        mDatas = new ArrayList<>();
        mDatas.add(new LuckItemBean("综合运势:",bean.getMima().getText().get(0), Color.BLUE));
        mDatas.add(new LuckItemBean("爱情运势:",bean.getLove().get(0),Color.GREEN));
        mDatas.add(new LuckItemBean("事业学业:",bean.getCareer().get(0),Color.RED));
        mDatas.add(new LuckItemBean("健康运势:",bean.getHealth().get(0),Color.GRAY));
        mDatas.add(new LuckItemBean("财富运势:",bean.getFinance().get(0),Color.BLACK));
        luckLv.setAdapter(new LuckAnalysisAdapter(this,mDatas));
    }
}