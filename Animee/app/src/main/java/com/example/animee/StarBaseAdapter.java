package com.example.animee;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.animee.bean.StarInfoBean;
import com.example.animee.utils.AssetsUtils;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class StarBaseAdapter extends BaseAdapter {
    private Context context;
    List<StarInfoBean.StarinfoBean>mDatas;

    public StarBaseAdapter(Context context, List<StarInfoBean.StarinfoBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }


    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_star_gv,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        //获取指定位置的数据
        StarInfoBean.StarinfoBean bean = mDatas.get(position);
        //将数据绑定到视图
        holder.tv.setText(bean.getName());
        Bitmap img = AssetsUtils.logoImgMap.get(bean.getLogoName());
        holder.iv.setImageBitmap(img);
        return convertView;
    }
    //对item中的控件进行声明和初始化
    static class ViewHolder{
        CircleImageView iv;
        TextView tv;

        public ViewHolder(View view) {
            iv = view.findViewById(R.id.item_star_iv);
            tv = view.findViewById(R.id.item_star_tv);
        }
    }
}
