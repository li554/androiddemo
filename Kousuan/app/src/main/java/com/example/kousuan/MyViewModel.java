package com.example.kousuan;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

import java.util.Random;

public class MyViewModel extends AndroidViewModel {
    private SavedStateHandle handle;
    private static final String KEY_CURRENT_SCORE = "key_current_score";
    private static final String KEY_HIGH_SCORE = "key_high_score";
    private static final String KEY_LEFT_NUMBER = "key_left_number";
    private static final String KEY_RIGHT_NUMBER = "key_right_number";
    private static final String KEY_OPERATOR = "key_operator";
    private static final String KEY_ANSWER = "key_answer";
    private static final String SHP_NAME = "shp_name";
    public boolean win_flag = false;
    public MyViewModel(@NonNull Application application, SavedStateHandle handle) {
        super(application);
        if (!handle.contains(KEY_HIGH_SCORE)){
            SharedPreferences preferences = application.getSharedPreferences(SHP_NAME, Context.MODE_PRIVATE);
            handle.set(KEY_HIGH_SCORE,preferences.getInt(KEY_HIGH_SCORE,0));
            handle.set(KEY_LEFT_NUMBER,0);
            handle.set(KEY_RIGHT_NUMBER,0);
            handle.set(KEY_OPERATOR,"+");
            handle.set(KEY_ANSWER,0);
        }
        this.handle = handle;
    }
    public MutableLiveData<Integer> getAnswer(){
        return handle.getLiveData(KEY_ANSWER);
    }
    public MutableLiveData<Integer> getCurrentScore(){
        return handle.getLiveData(KEY_CURRENT_SCORE);
    }
    public MutableLiveData<Integer> getLeftNumber(){
        return handle.getLiveData(KEY_LEFT_NUMBER);
    }
    public MutableLiveData<Integer> getRightNumber(){
        return handle.getLiveData(KEY_RIGHT_NUMBER);
    }
    public MutableLiveData<String> getOperator(){
        return handle.getLiveData(KEY_OPERATOR);
    }
    public MutableLiveData<Integer> getHighScore(){
        return handle.getLiveData(KEY_HIGH_SCORE);
    }
    void genRandomQuestion(){
        //生成题目（两个数字，一个运算符）
        Random random = new Random();
        int a = random.nextInt(20)+1;
        int b = random.nextInt(20)+1;
        String[] ops = {"+","-"};
        String op = ops[random.nextInt(2)];
        switch (op) {
            case "+":
                if (a>b){
                    getLeftNumber().setValue(b);
                    getRightNumber().setValue(a-b);
                    getAnswer().setValue(a);
                }else{
                    getLeftNumber().setValue(a);
                    getRightNumber().setValue(b-a);
                    getAnswer().setValue(b);
                }
                break;
            case "-":
                if (a<b){
                    getLeftNumber().setValue(b);
                    getRightNumber().setValue(a);
                    getAnswer().setValue(b-a);
                }else{
                    getLeftNumber().setValue(a);
                    getRightNumber().setValue(b);
                    getAnswer().setValue(a-b);
                }
                break;
        }
        getOperator().setValue(op);
    }
    public void save(){
        SharedPreferences preferences = getApplication().getSharedPreferences(SHP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(KEY_HIGH_SCORE,getHighScore().getValue());
        editor.apply();
    }
    public void answerCorrect(){
        getCurrentScore().setValue(getCurrentScore().getValue()+1);
        if (getCurrentScore().getValue()>getHighScore().getValue()){
            getHighScore().setValue(getCurrentScore().getValue());
            save();
            win_flag = true;
        }
        genRandomQuestion();
    }
}
