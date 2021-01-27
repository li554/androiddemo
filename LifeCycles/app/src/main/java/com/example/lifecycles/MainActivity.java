package com.example.lifecycles;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleObserver;

import android.os.Bundle;
import android.widget.Chronometer;

public class MainActivity extends AppCompatActivity {

    MyChronometer meter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        meter = findViewById(R.id.meter);
        getLifecycle().addObserver(meter);
    }
}