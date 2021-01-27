package com.example.animee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.animee.bean.StarDetailBean;
import com.example.animee.bean.StarInfoBean;
import com.example.animee.utils.AssetsUtils;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class StarDetailActivity extends AppCompatActivity {
    private CircleImageView iconIv;
    private TextView titleTv;
    private ImageView backIv;
    private TextView nameTv,dateTv;
    private ListView detailLv;
    private TextView footerInfoTv;
    private List<StarDetailBean> mDatas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_star_detail);
        iconIv = findViewById(R.id.star_detail_civ);
        titleTv = findViewById(R.id.title_layout_tv_title);
        backIv = findViewById(R.id.title_layout_iv_back);
        nameTv = findViewById(R.id.star_detail_tv_name);
        dateTv = findViewById(R.id.star_detail_tv_date);
        detailLv = findViewById(R.id.star_detail_lv);
        //获取数据
        Intent intent = getIntent();
        StarInfoBean.StarinfoBean bean = (StarInfoBean.StarinfoBean) intent.getSerializableExtra("star");
        initMData(bean);
        //显示数据
        titleTv.setText("星座详情");
        iconIv.setImageBitmap(AssetsUtils.contentLogoImgMap.get(bean.getLogoName()));
        nameTv.setText(bean.getName());
        dateTv.setText(bean.getDate());
        View footerView = LayoutInflater.from(this).inflate(R.layout.footer_star_detail, null);
        footerInfoTv = footerView.findViewById(R.id.footer_star_detail_tv_info);
        detailLv.addFooterView(footerView);
        footerInfoTv.setText(bean.getInfo());
        //设置ListView的适配器
        detailLv.setAdapter(new StarDetailListAdapter(this,mDatas));
        //绑定返回事件
        backIv.setOnClickListener(v -> finish());
    }

    private void initMData(StarInfoBean.StarinfoBean bean) {
        mDatas = new ArrayList<>();
        mDatas.add(new StarDetailBean("性格特点:",bean.getTd(),R.color.lightBlue));
        mDatas.add(new StarDetailBean("掌管宫位:",bean.getGw(),R.color.lightPink));
        mDatas.add(new StarDetailBean("显阴阳性:",bean.getYy(),R.color.lightGreen));
        mDatas.add(new StarDetailBean("最大特征:",bean.getTz(),R.color.purple));
        mDatas.add(new StarDetailBean("主管星球:",bean.getZg(),R.color.orange));
        mDatas.add(new StarDetailBean("幸运颜色:",bean.getYs(),R.color.colorAccent));
        mDatas.add(new StarDetailBean("搭配珠宝:",bean.getZb(),R.color.colorPrimary));
        mDatas.add(new StarDetailBean("幸运号码:",bean.getHm(),R.color.grey));
        mDatas.add(new StarDetailBean("相配金属:",bean.getJs(),R.color.darkBlue));
    }
}