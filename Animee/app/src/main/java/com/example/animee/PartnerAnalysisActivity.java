package com.example.animee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.example.animee.utils.AssetsUtils;
import com.example.animee.utils.LoadAsyncTask;
import com.example.animee.utils.URLContent;

import java.io.UnsupportedEncodingException;

import de.hdodenhof.circleimageview.CircleImageView;

public class PartnerAnalysisActivity extends AppCompatActivity implements LoadAsyncTask.OnGetNetDataListener {

    private CircleImageView manIv,womanIv;
    private TextView manTv,womanTv;
    private TextView pd,vs,pf,bz,jx,zy;
    private ImageView backIv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_analysis);
        //初始化控件
        manIv = findViewById(R.id.partner_analysis_civ_man);
        womanIv = findViewById(R.id.partner_analysis_civ_woman);
        manTv = findViewById(R.id.partner_analysis_tv_man);
        womanTv = findViewById(R.id.partner_analysis_tv_woman);
        backIv = findViewById(R.id.title_layout_iv_back);

        pd = findViewById(R.id.partner_analysis_tv_pd);
        vs = findViewById(R.id.partner_analysis_tv_vs);
        pf = findViewById(R.id.partner_analysis_tv_pf);
        bz = findViewById(R.id.partner_analysis_tv_bz);
        jx = findViewById(R.id.partner_analysis_tv_jx);
        zy = findViewById(R.id.partner_analysis_tv_zy);

        //获取传送数据
        Intent intent = getIntent();
        String manName = intent.getStringExtra("manName");
        String womanName = intent.getStringExtra("womanName");
        String manLogoName = intent.getStringExtra("manLogoName");
        String womanLogoName = intent.getStringExtra("womanLogoName");
        Bitmap manMap = AssetsUtils.contentLogoImgMap.get(manLogoName);
        Bitmap womanMap = AssetsUtils.contentLogoImgMap.get(womanLogoName);

        //请求获取数据
        //创建异步任务对象
        LoadAsyncTask task = new LoadAsyncTask(this, this);
        try {
            String url = URLContent.getPartnerURL(manName, womanName);
            task.execute(url);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //将数据显示到视图
        manTv.setText(manName);
        womanTv.setText(womanName);
        manIv.setImageBitmap(manMap);
        womanIv.setImageBitmap(womanMap);
        pd.setText("星座配对-"+manName+"和"+womanName+"配对");
        vs.setText(manName+" vs "+womanName);

        //绑定事件
        backIv.setOnClickListener(v->finish());
    }

    @Override
    public void onSuccess(String json) {
        System.out.println(json);
        JSONObject object = JSONObject.parseObject(json);
        //将数据显示到视图
        JSONObject result = (JSONObject) object.get("result");
        pf.setText("配对评分: "+result.get("zhishu")+"   "+result.get("jieguo"));
        bz.setText("星座比重: "+result.get("bizhong"));
        jx.setText("解析: "+result.get("lianai"));
        zy.setText("注意: "+result.get("zhuyi"));
    }
}