package com.example.navmodel;


import androidx.databinding.BindingAdapter;
import androidx.databinding.BindingMethod;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
public class MyViewModel extends ViewModel {
    private MutableLiveData<Integer> number;

    public MutableLiveData<Integer> getNumber() {
        if (number==null){
            number = new MutableLiveData<>();
            number.setValue(0);
        }
        return number;
    }
    public void setNumber(MutableLiveData<Integer> number){
        this.number.setValue(number.getValue());
    }
    public void add(int x){
        number.setValue(number.getValue()+x);
        if (number.getValue()<0) number.setValue(0);
    }
}
