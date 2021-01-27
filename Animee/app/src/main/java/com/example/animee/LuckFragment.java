package com.example.animee;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.animee.bean.StarInfoBean;
import com.example.animee.utils.LoadAsyncTask;
import com.example.animee.utils.URLContent;

import java.util.List;

public class LuckFragment extends Fragment{
    private GridView luckGv;
    private List<StarInfoBean.StarinfoBean> mDatas;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_luck, container, false);
        //初始化控件
        luckGv = view.findViewById(R.id.luck_frag_gv);
        //获取数据
        mDatas = ((StarInfoBean) getArguments().getSerializable("info")).getStarinfo();
        //设置适配器
        luckGv.setAdapter(new LuckBaseAdapter(getContext(),mDatas));
        //绑定事件
        luckGv.setOnItemClickListener((parent, view1, position, id) -> {
            Intent intent = new Intent(getContext(),LuckAnalysisActivity.class);
            intent.putExtra("consName",mDatas.get(position).getName());
            startActivity(intent);
        });
        return view;
    }
}