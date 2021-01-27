package com.example.littletools;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.littletools.call.CallActivity;
import com.example.littletools.filemanager.FileExplorerActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void phoneClick(View view) {
            Intent intent = new Intent(this, CallActivity.class);
            startActivity(intent);
    }

    public void fileExplorerClick(View view) {
        Intent intent = new Intent(this,FileExplorerActivity.class);
        startActivity(intent);
    }
}