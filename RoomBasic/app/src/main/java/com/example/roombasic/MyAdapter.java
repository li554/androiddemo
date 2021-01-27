package com.example.roombasic;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Word> allWords = new ArrayList<>();
    private boolean useCardView;
    private  MyViewModel myViewModel;
    public MyAdapter(boolean useCardView,MyViewModel myViewModel){
        this.useCardView = useCardView;
        this.myViewModel = myViewModel;
    }
    public void setAllWords(List<Word> allWords){
        this.allWords = allWords;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView;
        if (useCardView){
            itemView = inflater.inflate(R.layout.cell_card,parent,false);
        }else{
            itemView = inflater.inflate(R.layout.cell_normal,parent,false);
        }
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Word word = allWords.get(position);
        holder.id.setText(String.valueOf(word.getId()));
        holder.english.setText(word.getWord());
        holder.chinese.setText(word.getChineseMeaning());
        //初始化
        holder.aSwitch.setOnCheckedChangeListener(null);
        if (!word.isShowChinese()){
            holder.aSwitch.setChecked(true);
            holder.chinese.setVisibility(View.GONE);
        }else {
            holder.aSwitch.setChecked(false);
            holder.chinese.setVisibility(View.VISIBLE);
        }
        holder.aSwitch.setOnCheckedChangeListener(((buttonView, isChecked) -> {
            if (isChecked){
                holder.chinese.setVisibility(View.GONE);
                word.setShowChinese(false);
            }else{
                holder.chinese.setVisibility(View.VISIBLE);
                word.setShowChinese(true);
            }
            myViewModel.updateWords(word);
        }));
        holder.itemView.setOnClickListener(v -> {
            Uri uri = Uri.parse("https://m.youdao.com/dict?le=eng&q="+holder.english.getText());
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(uri);
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return allWords.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView id,english,chinese;
        Switch aSwitch;
        public ViewHolder(View view) {
            super(view);
            this.id = view.findViewById(R.id.id);
            this.english = view.findViewById(R.id.english);
            this.chinese = view.findViewById(R.id.chinese);
            this.aSwitch = view.findViewById(R.id.switch2);
        }
    }
}
