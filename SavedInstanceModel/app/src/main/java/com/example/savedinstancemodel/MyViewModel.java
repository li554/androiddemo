package com.example.savedinstancemodel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {
    private LocalData localData;
    private SavedStateHandle handle;
    public void init(Context context){
        localData = new LocalData(context);
        if (!handle.contains(MainActivity.key)){
            handle.set(MainActivity.key,localData.load());
        }
    }
    public MyViewModel(SavedStateHandle handle){
        this.handle = handle;
    }
    public MutableLiveData<Integer> getNumber(){
        return handle.getLiveData(MainActivity.key);
    }
    public void add(){
        getNumber().setValue(getNumber().getValue()+1);
        localData.save(getNumber().getValue());
    }
}
