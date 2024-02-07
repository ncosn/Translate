package com.sgcc.yzd.translate.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.sgcc.yzd.translate.R;
import com.sgcc.yzd.translate.databinding.ActivityFifthBinding;

public class FifthActivity extends AppCompatActivity {

    ActivityFifthBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_fifth);

        binding = ActivityFifthBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button1.setOnClickListener(view -> {
//            binding.framelayout.scrollBy(-50,-50);
//            System.out.println("button1");

            binding.customview.smoothScrollTo(100,100);

        });

        ObjectAnimator mObjectAnimator = ObjectAnimator.ofFloat(binding.customview, "translationX", 300);
        mObjectAnimator.setDuration(300);
        binding.button2.setOnClickListener(view -> {
//            // scrollTo
//            binding.framelayout.scrollTo(-100,-100);
//            // Scroller平滑移动
//            binding.customview.smoothScrollTo(-100,-100);
//            // 属性动画ObjectAnimator
//            ObjectAnimator mObjectAnimator = ObjectAnimator.ofFloat(binding.customview, "translationX", 300);
//            mObjectAnimator.setDuration(300);
//            mObjectAnimator.start();
//            mObjectAnimator.cancel();
//            Log.d("TAG", "onCreate: binding.customview.getTranslationX():"+binding.customview.getTranslationX());


//            // ValueAnimator
//            ValueAnimator mValueAnimator = ValueAnimator.ofFloat(0,100);
//            mValueAnimator.setTarget(binding.customview);
//            mValueAnimator.setDuration(1000).start();
//            mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                @Override
//                public void onAnimationUpdate(ValueAnimator animation) {
//                    Float mFloat = (Float) animation.getAnimatedValue();
//                    Log.d("TAG", "onAnimationUpdate: "+mFloat);
//                }
//            });

            System.out.println("button2");
        });


    }
}