package com.example.savedinstancemodel;

import android.content.Context;
import android.content.SharedPreferences;

public class LocalData {

    private Context context;
    SharedPreferences preferences;
    public LocalData(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(context.getResources().getString(R.string.prefer_name),Context.MODE_PRIVATE);
    }

    public void save(int i){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(context.getResources().getString(R.string.my_key),i);
        editor.apply();
    }
    public int load(){
        return preferences.getInt(context.getResources().getString(R.string.my_key), context.getResources().getInteger(R.integer.def_value));
    }
}
