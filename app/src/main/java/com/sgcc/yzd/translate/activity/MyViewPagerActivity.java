package com.sgcc.yzd.translate.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.sgcc.yzd.translate.R;
import com.sgcc.yzd.translate.databinding.ActivityMyViewPagerBinding;

public class MyViewPagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMyViewPagerBinding binding = ActivityMyViewPagerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



    }
}