package com.example.easyPermission;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.easyPermission.localWeb.WebActivity;
import com.example.easyPermission.netWeb.NetWebActivity;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks{

    private static final int RC_READ_EXTERNAL_STORAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] perms = {Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.CALL_PHONE};
        if (EasyPermissions.hasPermissions(this, perms)) {
            test();
        } else {
            EasyPermissions.requestPermissions(this, "申请内存权限",
                    RC_READ_EXTERNAL_STORAGE, perms);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // 将返回结果转给EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        test();
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }
    private void test(){
        Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();
    }

    public void toLocalWebClick(View view) {
        Intent intent = new Intent(this, WebActivity.class);
        startActivity(intent);
    }

    public void toNetWebClick(View view) {
        Intent intent = new Intent(this, NetWebActivity.class);
        startActivity(intent);
    }

}