package com.example.paging;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class MyPageAdapter extends PagedListAdapter<Student,MyPageAdapter.ViewHolder> {

    protected MyPageAdapter() {
        super(new DiffUtil.ItemCallback<Student>() {
            @Override
            public boolean areItemsTheSame(@NonNull Student oldItem, @NonNull Student newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull Student oldItem, @NonNull Student newItem) {
                return oldItem.getStudentNumber().equals(newItem.getStudentNumber());
            }
        });
    }

    @NonNull
    @Override
    public MyPageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_card,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyPageAdapter.ViewHolder holder, int position) {
        Student student = getItem(position);
        if (student==null){
            holder.textView.setText("Loading...");
        }else{
            holder.textView.setText(String.valueOf(student.getStudentNumber()));
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
