package com.example.animee;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.animee.bean.StarInfoBean;
import com.example.animee.utils.AssetsUtils;

import java.util.ArrayList;
import java.util.List;

public class PartnerFragment extends Fragment implements View.OnClickListener,AdapterView.OnItemSelectedListener {
    private ImageView manIv,womanIv;
    private Spinner manSp,womanSp;
    private Button prizeBtn,anaBtn;
    private List<StarInfoBean.StarinfoBean> infoList;
    private List<String>nameList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_partner,container,false);
        //初始化控件
        manIv = view.findViewById(R.id.partner_frag_iv_man);
        womanIv = view.findViewById(R.id.partner_frag_iv_woman);
        manSp = view.findViewById(R.id.partner_frag_sp_man);
        womanSp = view.findViewById(R.id.partner_frag_sp_woman);
        prizeBtn = view.findViewById(R.id.partner_frag_btn_prize);
        anaBtn = view.findViewById(R.id.partner_frag_btn_analysis);
        //获取数据
        StarInfoBean infoBean = (StarInfoBean) getArguments().getSerializable("info");
        infoList = infoBean.getStarinfo();
        nameList = new ArrayList<>();
        for (StarInfoBean.StarinfoBean item:infoList){
            nameList.add(item.getName());
        }
        //设置适配器
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),R.layout.item_partner_sp,R.id.item_partner_tv,nameList);
        manSp.setAdapter(adapter);
        womanSp.setAdapter(adapter);
        //获取第一个图片资源
        Bitmap img = AssetsUtils.contentLogoImgMap.get(infoList.get(0).getLogoName());
        manIv.setImageBitmap(img);
        womanIv.setImageBitmap(img);
        //绑定事件
        manSp.setOnItemSelectedListener(this);
        womanSp.setOnItemSelectedListener(this);
        prizeBtn.setOnClickListener(this);
        anaBtn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.partner_frag_btn_prize:
                break;
            case R.id.partner_frag_btn_analysis:
                int manPosition = manSp.getSelectedItemPosition();
                int womanPosition = womanSp.getSelectedItemPosition();
                String manName = infoList.get(manPosition).getName();
                String womanName = infoList.get(womanPosition).getName();
                Intent intent = new Intent(getContext(),PartnerAnalysisActivity.class);
                intent.putExtra("manName",manName);
                intent.putExtra("manLogoName",infoList.get(manPosition).getLogoName());
                intent.putExtra("womanName",womanName);
                intent.putExtra("womanLogoName",infoList.get(womanPosition).getLogoName());
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.partner_frag_sp_man:
                manIv.setImageBitmap(AssetsUtils.contentLogoImgMap.get(infoList.get(position).getLogoName()));
                break;
            case R.id.partner_frag_sp_woman:
                womanIv.setImageBitmap(AssetsUtils.contentLogoImgMap.get(infoList.get(position).getLogoName()));
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}