package com.example.kousuan;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.DialogInterface;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private MyViewModel myViewModel;
    private NavController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myViewModel = new ViewModelProvider(this,new SavedStateViewModelFactory(getApplication(),this)).get(MyViewModel.class);
        controller = Navigation.findNavController(this,R.id.fragment);
        NavigationUI.setupActionBarWithNavController(this,controller);
    }

    @Override
    public boolean onSupportNavigateUp() {
        if (controller.getCurrentDestination().getId() == R.id.questionFragment){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.quit_dialog_title);
            builder.setPositiveButton(R.string.dialog_positive_msg, (dialog, which) -> controller.navigateUp());
            builder.setNegativeButton(R.string.dialog_negative_msg, (dialog, which) -> {});
            AlertDialog dialog = builder.create();
            dialog.show();
        }else if (controller.getCurrentDestination().getId()==R.id.titleFragment){
            finish();
        } else{
            controller.navigate(R.id.titleFragment);
        }
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        onSupportNavigateUp();
    }
}