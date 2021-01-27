package com.example.littletools.filemanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.littletools.PermissionUtils;
import com.example.littletools.R;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileExplorerActivity extends AppCompatActivity implements PermissionUtils.PermissionGrant{

    private TextView pathTv;
    private ImageButton backBtn;
    private ListView fileLv;
    private File parentFile;
    private List<Map<String,Object>> mDatas;
    private File[] files;
    private SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_explorer);
        //初始化组件
        pathTv = findViewById(R.id.id_filePath);
        backBtn = findViewById(R.id.id_btn_back);
        fileLv = findViewById(R.id.id_lv);
        //请求权限
        PermissionUtils.requestPermission(this, PermissionUtils.CODE_READ_EXTERNAL_STORAGE,this);
    }

    private void initMDatas() {
        mDatas = new ArrayList<>();
        Map<String,Object> map;
        for (File file:files){
            map = new HashMap<>();
            map.put("filename",file.getName());
            if (file.isDirectory()){
                map.put("icon",R.mipmap.folder);
            }else{
                map.put("icon",R.mipmap.file);
            }
            mDatas.add(map);
        }
    }

    @Override
    public void onPermissionGranted(int requestCode) {
        //判断是否装置sd卡
        boolean isLoadedSDK = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        if (isLoadedSDK) {
            //申请权限
            File root = Environment.getExternalStorageDirectory();
            parentFile = root;
            files = root.listFiles();
            initMDatas();
            //设置适配器
            adapter = new SimpleAdapter(this, mDatas, R.layout.item_file_explorer, new String[]{"filename", "icon"}, new int[]{R.id.item_tv, R.id.item_icon});
            fileLv.setAdapter(adapter);
            //更新视图
            pathTv.setText("当前路径:"+parentFile.getAbsolutePath());
        }else{
            Toast.makeText(this, "SD卡没有被装载", Toast.LENGTH_SHORT).show();
        }
        //后退
        backBtn.setOnClickListener(v -> {
            parentFile = new File(parentFile.getParent());
            pathTv.setText("当前路径:"+parentFile.getAbsolutePath());
            files = parentFile.listFiles();
            initMDatas();
            adapter = new SimpleAdapter(this, mDatas, R.layout.item_file_explorer, new String[]{"filename", "icon"}, new int[]{R.id.item_tv, R.id.item_icon});
            fileLv.setAdapter(adapter);
        });
        //前进
        fileLv.setOnItemClickListener((parent, view, position, id) -> {
            if (files[position].isDirectory()){
                //修改路径
                parentFile = files[position];
                pathTv.setText("当前路径:"+parentFile.getAbsolutePath());

                //更新ListView数据
                files = parentFile.listFiles();
                initMDatas();
                adapter = new SimpleAdapter(this, mDatas, R.layout.item_file_explorer, new String[]{"filename", "icon"}, new int[]{R.id.item_tv, R.id.item_icon});
                fileLv.setAdapter(adapter);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionUtils.requestPermissionResult(this,requestCode, grantResults,this);
    }
}