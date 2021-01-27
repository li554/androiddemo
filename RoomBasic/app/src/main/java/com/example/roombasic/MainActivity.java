package com.example.roombasic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.example.roombasic.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private MyViewModel myViewModel;
    private MyAdapter myAdapter1,myAdapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        myAdapter1 = new MyAdapter(false,myViewModel);
        myAdapter2 = new MyAdapter(true,myViewModel);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(myAdapter1);
        myViewModel.getAllWordsLive().observe(this, words -> {
            int temp = myAdapter1.getItemCount();
            myAdapter1.setAllWords(words);
            myAdapter2.setAllWords(words);
            if (words.size() != temp){
                myAdapter1.notifyDataSetChanged();
                myAdapter2.notifyDataSetChanged();
            }
        });
        binding.setData(myViewModel);
        binding.setLifecycleOwner(this);
        binding.insert.setOnClickListener(v->{
            String[] english_words = {
                    "Hello",
                    "World",
                    "Android",
                    "Google",
                    "Studio",
                    "Project",
                    "Database",
                    "Recycler",
                    "View",
                    "String",
                    "Value",
                    "Integer"
            };
            String[] chinese_meaning = {
                    "你好",
                    "世界",
                    "安卓",
                    "谷歌",
                    "工作室",
                    "项目",
                    "数据库",
                    "回收站",
                    "视图",
                    "字符串",
                    "价值",
                    "整数类型"
            };
            for (int i=0;i<english_words.length;i++){
                myViewModel.insertWords(new Word(english_words[i],chinese_meaning[i]));
            }
        });
        binding.clear.setOnClickListener(v -> {
            myViewModel.deleteAllWords();
        });
        binding.switch1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                binding.recyclerView.setAdapter(myAdapter2);
            }else{
                binding.recyclerView.setAdapter(myAdapter1);
            }
        });
    }
}