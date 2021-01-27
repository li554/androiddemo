package com.example.littletools;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

public class PermissionUtils {
    public final static int CODE_RECORD_AUDIO = 0;
    public final static int CODE_GET_ACCOUNTS = 1;
    public final static int CODE_READ_PHONE_STATE = 2;
    public final static int CODE_CALL_PHONE = 3;
    public final static int CODE_CAMERA = 4;
    public final static int CODE_ACCESS_FINE_LOCATION = 5;
    public final static int CODE_ACCESS_COARSE_LOCATION = 6;
    public final static int CODE_READ_EXTERNAL_STORAGE = 7;
    public final static int CODE_WRITE_EXTERNAL_STORAGE = 8;

    public static final String PERMISSION_RECORD_AUDIO = Manifest.permission.RECORD_AUDIO;
    public static final String PERMISSION_GET_ACCOUNTS= Manifest.permission.GET_ACCOUNTS;
    public static final String PERMISSION_READ_PHONE_STATE = Manifest.permission.READ_PHONE_STATE;
    public static final String PERMISSION_CALL_PHONE = Manifest.permission.CALL_PHONE;
    public static final String PERMISSION_CAMERA = Manifest.permission.CAMERA;
    public static final String PERMISSION_ACCESS_FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    public static final String PERMISSION_READ_EXTERNAL_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE;
    public static final String PERMISSION_WRITE_EXTERNAL_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;

    public interface PermissionGrant{
        void onPermissionGranted(int requestCode);
    }
    public static final String[] requestPermissions = {
            PERMISSION_RECORD_AUDIO,PERMISSION_GET_ACCOUNTS,PERMISSION_READ_PHONE_STATE,PERMISSION_CALL_PHONE,
            PERMISSION_CAMERA,PERMISSION_ACCESS_FINE_LOCATION,PERMISSION_READ_EXTERNAL_STORAGE,PERMISSION_WRITE_EXTERNAL_STORAGE
    };
    public static void requestPermission(Activity activity,int requestCode,PermissionGrant permissionGrant){
        if (activity==null) {
            return;
        }
        if ((requestCode<0||requestCode>=requestPermissions.length)) {
            return;
        }
        String requestPermission = requestPermissions[requestCode];
        if (Build.VERSION.SDK_INT<23){
            return;
        }
        int checkSelfPermission;
        try {
            checkSelfPermission = ActivityCompat.checkSelfPermission(activity,requestPermission);
            if (checkSelfPermission!= PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity,new String[]{requestPermission},requestCode);
            }else{
                permissionGrant.onPermissionGranted(requestCode);
            }
        }catch (Exception e){
            Toast.makeText(activity, "请打开这个权限", Toast.LENGTH_SHORT).show();
        }
    }

    public static void requestPermissionResult(Activity activity, int requestCode, @NonNull int[] grantResults, PermissionGrant permissionGrant){
        if (activity==null) return;
        if ((requestCode<0||requestCode>=requestPermissions.length)) {
            Toast.makeText(activity, "illegal requestCode:"+requestCode, Toast.LENGTH_SHORT).show();
            return;
        }
        if (grantResults.length>0&&grantResults[0]== PackageManager.PERMISSION_GRANTED) {
            permissionGrant.onPermissionGranted(requestCode);
        }else{
            String permission = requestPermissions[requestCode];
            openSettingActivity(activity,"Result:"+permission);
        }
    }

    private static void openSettingActivity(Activity activity, String s) {
        showMessageOkCancel(activity, s, (dialog, which) -> {
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
            intent.setData(uri);
            activity.startActivity(intent);
        });
    }

    private static void showMessageOkCancel(Activity activity, String msg, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(activity).setMessage(msg)
                .setPositiveButton("OK",okListener)
                .setNegativeButton("CANCEL",null)
                .create().show();
    }
}
