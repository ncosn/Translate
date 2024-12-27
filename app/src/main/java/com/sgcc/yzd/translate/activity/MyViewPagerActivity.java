package com.sgcc.yzd.translate.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.sgcc.yzd.translate.R;
import com.sgcc.yzd.translate.databinding.ActivityMyViewPagerBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MyViewPagerActivity extends AppCompatActivity {
    private static final String TAG = "ViewPagerActivity";
    List<ImageView> imageViewsList = new ArrayList<>();
    private ScheduledExecutorService scheduledExecutorService;
    private GuideShowRunnable guideShowTask;
    ActivityMyViewPagerBinding binding;
    private int currentItem = 0;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            binding.vpMain.setCurrentItem(currentItem);
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMyViewPagerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initData();

        binding.vpMain.setFocusable(true);
        binding.vpMain.setAdapter(new ViewPagerAdapter(imageViewsList));
        binding.vpMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.e(TAG, "当前页面： " + (position + 1));
                currentItem = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        startPlay();

    }

    public void initData() {
        ImageView iv1 = new ImageView(this);
        iv1.setImageResource(R.drawable.fragment);
        ImageView iv2 = new ImageView(this);
        iv2.setImageResource(R.drawable.smile);
        ImageView iv3 = new ImageView(this);
        iv3.setImageResource(R.drawable.placeholder);
        imageViewsList.add(iv1);
        imageViewsList.add(iv2);
        imageViewsList.add(iv3);
    }

    private class ViewPagerAdapter extends PagerAdapter {
        List<ImageView> mImageViewList;

        public ViewPagerAdapter(List<ImageView> mImageViewList) {
            this.mImageViewList = mImageViewList;
        }

        @Override
        public int getCount() {
            return mImageViewList.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            container.addView(mImageViewList.get(position));
            return mImageViewList.get(position);
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }

    private void startPlay() {
        if (scheduledExecutorService == null) {
            scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        }
        if (guideShowTask == null) {
            guideShowTask = new GuideShowRunnable();
            int time = 3;
            if(time <= 0){
                time = 3;
            }
            scheduledExecutorService.scheduleAtFixedRate(guideShowTask, time, time, TimeUnit.SECONDS);
        }
    }

    public void stopPlay() {
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdown();
            try {
                // 向学生传达“问题解答完毕后请举手示意！”
                scheduledExecutorService.shutdown();

                // (所有的任务都结束的时候，返回TRUE)
                if(!scheduledExecutorService.awaitTermination(3000, TimeUnit.MILLISECONDS)){
                    // 超时的时候向线程池中所有的线程发出中断(interrupted)。
                    scheduledExecutorService.shutdownNow();
                }
            } catch (InterruptedException e) {
                // awaitTermination方法被中断的时候也中止线程池中全部的线程的执行。
                System.out.println("awaitTermination interrupted: " + e);
                scheduledExecutorService.shutdownNow();
            }
            scheduledExecutorService = null;
        }
        if (guideShowTask != null) {
            guideShowTask = null;
        }
    }

    private class GuideShowRunnable implements Runnable {

        @Override
        public void run() {
            synchronized (binding.vpMain) {
                currentItem = (currentItem + 1) % imageViewsList.size();
                handler.obtainMessage().sendToTarget();
            }
        }

    }

    @Override
    protected void onDestroy() {
        stopPlay();
        super.onDestroy();
    }
}