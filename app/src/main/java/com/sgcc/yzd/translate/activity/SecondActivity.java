package com.sgcc.yzd.translate.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.sgcc.yzd.translate.R;
import com.sgcc.yzd.translate.databinding.ActivitySecondBinding;
import com.sgcc.yzd.translate.viewmodel.MyViewModel;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySecondBinding activitySecondBinding = DataBindingUtil.setContentView(this,R.layout.activity_second);
        MyViewModel viewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(MyViewModel.class);
        activitySecondBinding.setViewModel(viewModel);
        activitySecondBinding.setLifecycleOwner(this);
    }
}