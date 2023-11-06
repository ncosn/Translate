package com.sgcc.yzd.translate.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.sgcc.yzd.translate.R;
import com.sgcc.yzd.translate.databinding.ActivitySecondBinding;
import com.sgcc.yzd.translate.viewmodel.MyViewModel;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("onCreate");
        ActivitySecondBinding activitySecondBinding = DataBindingUtil.setContentView(this,R.layout.activity_second);
        MyViewModel viewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(MyViewModel.class);
        // 给 variable 传对象
        activitySecondBinding.setMyViewModel(viewModel);
        activitySecondBinding.setLifecycleOwner(this);

        // 设置标题栏
        setSupportActionBar(activitySecondBinding.myToolbar);
        // 启用向上返回按钮，并设置自定义点击事件
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activitySecondBinding.myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        viewModel.getLang().observe(this, integer -> {
            Toast.makeText(SecondActivity.this,String.valueOf(integer),Toast.LENGTH_SHORT).show();
        });
    }
}