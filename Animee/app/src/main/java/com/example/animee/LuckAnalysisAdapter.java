package com.example.animee;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.animee.bean.LuckBean;
import com.example.animee.bean.LuckItemBean;
import com.example.animee.bean.StarInfoBean;

import java.util.List;

public class LuckAnalysisAdapter extends BaseAdapter {

    private Context context;
    private List<LuckItemBean> mDatas;

    public LuckAnalysisAdapter(Context context, List<LuckItemBean> mDatas) {
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
        if (convertView==null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_luck_frag_lv,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = ((ViewHolder) convertView.getTag());
        }
        holder.titleTv.setText(mDatas.get(position).getTitle());
        holder.contentTv.setText(mDatas.get(position).getContent());
        GradientDrawable drawable = (GradientDrawable) holder.titleTv.getBackground();
        drawable.setColor(mDatas.get(position).getColor());
        GradientDrawable drawable1 = (GradientDrawable) holder.contentTv.getBackground();
        drawable1.setColor(Color.LTGRAY);
        return convertView;
    }
    static class ViewHolder{
        TextView titleTv;
        TextView contentTv;
        public ViewHolder(View view){
            titleTv = view.findViewById(R.id.item_luck_tv_title);
            contentTv = view.findViewById(R.id.item_luck_tv_content);
        }
    }
}
