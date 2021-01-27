package com.example.littletools.call;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.littletools.R;

public class CallActivity extends AppCompatActivity {

    private static final int M = 22;
    private static final String PHONE_NUMBER = "15849337553";
    private static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
    }

    public void phoneCallClick(View view) {
        //检查权限
        checkPermission();
    }
    public void call(String phoneNumber){
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri uri = Uri.parse("tel:" + phoneNumber);
        intent.setData(uri);
        startActivity(intent);
    }

    private void checkPermission() {
        /*
        * 1.判断当前手机系统是否大于或等于6.0
        * 2.如果大于6.0检查是否有打电话的权限
        * 3.如果没有打电话的授权，则申请授权
        * 4.如果有授权则直接拨打电话
        * 4.如果小于6.0则直接拨打电话*/
        if (Build.VERSION.SDK_INT>=M){
            int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
            if (permission==PackageManager.PERMISSION_GRANTED) {
                call(PHONE_NUMBER);
            }else{
                ActivityCompat.requestPermissions(this,new String[]{
                        Manifest.permission.CALL_PHONE
                },REQUEST_CODE);
            }
        }else{
            call(PHONE_NUMBER);
        }
    }
    //申请权限的回调方法，成功失败都会调用
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode==REQUEST_CODE) {
            if (grantResults!=null&&grantResults.length>0) {
                //判断用户是否授予权限
                if (grantResults[0]== PackageManager.PERMISSION_GRANTED) {
                    call(PHONE_NUMBER);
                }else{
                    Toast.makeText(this,"您拒绝了申请拨打电话的权限",Toast.LENGTH_SHORT);
                }
            }
        }
    }
}