package com.example.animee.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.example.animee.MainActivity;
import com.example.animee.R;

public class WelcomeActivity extends AppCompatActivity {

    private TextView timeTv;
    Handler handler = new Handler();
    private SharedPreferences sharedPreferences;
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int number = Integer.parseInt((String) timeTv.getText())-1;
            if (number!=-1){
                timeTv.setText(number+"");
                handler.postDelayed(this,1000);
            }else{
                //跳转到starActivity
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        timeTv = findViewById(R.id.welcome_tv_time);
        sharedPreferences = getSharedPreferences("welcome_pref",MODE_PRIVATE);
        //读取sharedPreference
        boolean status = sharedPreferences.getBoolean("isFirst",true);
        if (status){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isFirst",false);
            editor.apply();
            Intent intent = new Intent(this,GuideActivity.class);
            startActivity(intent);
            finish();
        }else{
            handler.postDelayed(runnable,1000);
        }
    }
}