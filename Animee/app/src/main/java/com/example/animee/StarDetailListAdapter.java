package com.example.animee;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.animee.bean.StarDetailBean;
import com.example.animee.bean.StarInfoBean;
import com.example.animee.utils.AssetsUtils;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class StarDetailListAdapter extends BaseAdapter {
    private Context context;
    private List<StarDetailBean> mDatas;
    public StarDetailListAdapter(Context context, List<StarDetailBean> mDatas){
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
       StarDetailListAdapter.ViewHolder holder = null;
        if (convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_star_detail_lv,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (StarDetailListAdapter.ViewHolder) convertView.getTag();
        }
        //获取指定位置的数据
        StarDetailBean bean = mDatas.get(position);
        //将数据绑定到视图
        holder.title.setText(bean.getTitle());
        holder.content.setText(bean.getContent());
        holder.content.setBackgroundResource(bean.getColor());
        return convertView;
    }
    //对item中的控件进行声明和初始化
    static class ViewHolder{
        TextView title;
        TextView content;
        public ViewHolder(View view) {
            title = view.findViewById(R.id.item_star_detail_title);
            content = view.findViewById(R.id.item_star_detail_content);
        }
    }
}
