package com.example.animee;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.RadioGroup;
import com.example.animee.bean.StarInfoBean;
import com.example.animee.utils.AssetsUtils;
import com.google.gson.Gson;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{
    private RadioGroup mainRg;
    //声明四个fragment
    private Fragment star,partner,luck,mine;
    private FragmentManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainRg = findViewById(R.id.main_rg);
        mainRg.setOnCheckedChangeListener(this);
        //加载数据
        StarInfoBean infoBean = loadData();
        Bundle bundle = new Bundle();
        bundle.putSerializable("info", infoBean);
        //初始化碎片
        star = new StarFragment();
        star.setArguments(bundle);
        partner = new PartnerFragment();
        partner.setArguments(bundle);
        luck = new LuckFragment();
        luck.setArguments(bundle);
        mine = new MineFragment();
        mine.setArguments(bundle);
        addFragmentPage();
    }

    private StarInfoBean loadData() {
        try {
            String json = AssetsUtils.getJsonFromAssets(this,"xzcontent/xzcontent.json");
            Gson gson = new Gson();
            StarInfoBean infoBean = gson.fromJson(json, StarInfoBean.class);
            AssetsUtils.saveBitmapFromAssets(this,infoBean);
            return infoBean;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    private void addFragmentPage() {
        //创建碎片管理者对象
        manager = getSupportFragmentManager();
        //创建碎片处理事务对象（原子操作）
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        //将四个fragment添加到布局
        fragmentTransaction.add(R.id.main_layout_center,star);
        fragmentTransaction.add(R.id.main_layout_center,partner);
        fragmentTransaction.add(R.id.main_layout_center,luck);
        fragmentTransaction.add(R.id.main_layout_center,mine);
        //隐藏后面三个
        fragmentTransaction.hide(partner);
        fragmentTransaction.hide(luck);
        fragmentTransaction.hide(mine);
        //提交事务
        fragmentTransaction.commit();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        switch (checkedId) {
            case R.id.main_rb_star:
                fragmentTransaction.hide(partner);
                fragmentTransaction.hide(luck);
                fragmentTransaction.hide(mine);
                fragmentTransaction.show(star);
                break;
            case R.id.main_rb_luck:
                fragmentTransaction.hide(partner);
                fragmentTransaction.hide(star);
                fragmentTransaction.hide(mine);
                fragmentTransaction.show(luck);
                break;
            case R.id.main_rb_mine:
                fragmentTransaction.hide(partner);
                fragmentTransaction.hide(luck);
                fragmentTransaction.hide(star);
                fragmentTransaction.show(mine);
                break;
            case R.id.main_rb_partner:
                fragmentTransaction.hide(star);
                fragmentTransaction.hide(luck);
                fragmentTransaction.hide(mine);
                fragmentTransaction.show(partner);
                break;
        }
        fragmentTransaction.commit();
    }
}