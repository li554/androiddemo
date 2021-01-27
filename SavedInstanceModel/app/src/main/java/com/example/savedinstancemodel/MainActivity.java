package com.example.savedinstancemodel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.savedinstancemodel.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    public static final String key = "my_number";
    private MyViewModel myViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        myViewModel = new ViewModelProvider(this, new SavedStateViewModelFactory(getApplication(), this)).get(MyViewModel.class);
        myViewModel.init(getApplicationContext());
        binding.setData(myViewModel);
        binding.setLifecycleOwner(this);
    }
}