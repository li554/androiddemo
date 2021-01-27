package com.example.animee;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.animee.bean.StarInfoBean;
import com.example.animee.utils.AssetsUtils;

import java.util.List;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class MineFragment extends Fragment {
    private CircleImageView meCiv;
    private TextView meTv;
    private List<StarInfoBean.StarinfoBean>mDatas;
    private SharedPreferences star_pref;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        //获取传入数据
        super.onCreate(savedInstanceState);
        assert getArguments() != null;
        mDatas = ((StarInfoBean) getArguments().getSerializable("info")).getStarinfo();
        //获取本地存储数据
        star_pref = Objects.requireNonNull(getContext()).getSharedPreferences("star_pref", Context.MODE_PRIVATE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        //初始化控件
        meCiv = view.findViewById(R.id.me_frag_civ);
        meTv = view.findViewById(R.id.me_frag_tv_name);
        String name = star_pref.getString("name",mDatas.get(0).getName());
        String logoName = star_pref.getString("logoName",mDatas.get(0).getLogoName());
        //默认显示白羊座
        meCiv.setImageBitmap(AssetsUtils.contentLogoImgMap.get(logoName));
        meTv.setText(name);
        //绑定事件
        meCiv.setOnClickListener(v -> {
            Dialog dialog = new Dialog(getContext());
            dialog.setTitle("请选择您的星座:");
            dialog.setCancelable(true);
            dialog.setCanceledOnTouchOutside(true);
            View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.mine_dialog,null);
            dialog.setContentView(dialogView);
            GridView dialogGv = dialogView.findViewById(R.id.mine_dialog_gv);
            dialogGv.setAdapter(new LuckBaseAdapter(getContext(),mDatas));
            //设置gridView的点击事件
            dialogGv.setOnItemClickListener((parent, view1, position, id) -> {
                //关闭对话框，切换图片和文字
                dialog.cancel();
                String tName = mDatas.get(position).getName();
                String tLogoName = mDatas.get(position).getLogoName();
                meCiv.setImageBitmap(AssetsUtils.contentLogoImgMap.get(tLogoName));
                meTv.setText(tName);
                //保存选择的星座至本地，下次打开时加载
                SharedPreferences.Editor edit = star_pref.edit();
                edit.putString("name",tName);
                edit.putString("logoName",tLogoName);
                edit.apply();
            });
            dialog.show();
        });
        return view;
    }
}