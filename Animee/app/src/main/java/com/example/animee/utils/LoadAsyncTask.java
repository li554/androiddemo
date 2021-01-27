package com.example.animee.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class LoadAsyncTask extends AsyncTask<String,Void,String> {
    private Context context;
    private OnGetNetDataListener listener;
    private ProgressDialog dialog;
    public LoadAsyncTask(Context context, OnGetNetDataListener listener) {
        this.context = context;
        this.listener = listener;
        initDialog();
    }
    private void initDialog(){
        dialog = new ProgressDialog(context);
        dialog.setTitle("提示信息");
        dialog.setMessage("正在加载中");
    }

    public interface OnGetNetDataListener{
        void onSuccess(String json);
    }
    //运行在子线程中，进行耗时操作，如网络请求等
    @Override
    protected String doInBackground(String... strings) {
        return URLContent.getJSONFromNet(strings[0]);
    }
    //运行在主线程中，通常在此方法中进行控件的初始化
    @Override
    protected void onPreExecute() {
        dialog.show();
    }
    //运行在主线程，可以获得doInBackground返回的数据，主要用于控件的更新
    @Override
    protected void onPostExecute(String s) {
        dialog.dismiss();
        listener.onSuccess(s);
    }
}
