package com.sgcc.yzd.translate.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.NavigatorProvider;
import androidx.navigation.fragment.FragmentNavigator;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.view.MotionEvent;

import com.sgcc.yzd.translate.R;
import com.sgcc.yzd.translate.databinding.ActivityNavigationBinding;
import com.sgcc.yzd.translate.navigation.FixedFragmentNavigator;
import com.sgcc.yzd.translate.navigation.InitNavGraph;
import com.sgcc.yzd.translate.service.DownTimerService;

public class NavigationActivity extends AppCompatActivity {

    private NavGraph navDestinations;
    public NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityNavigationBinding binding = ActivityNavigationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initNavigation();
    }

    private void initNavigation() {
        Fragment fragmentById = getSupportFragmentManager().findFragmentById(R.id.fragment_bill_print);
        //fragment的重复加载问题和NavController有关
        navController = NavHostFragment.findNavController(fragmentById);
        NavigatorProvider provider = navController.getNavigatorProvider();
        //设置自定义的navigator
        FixedFragmentNavigator fixFragmentNavictor = new FixedFragmentNavigator(this, fragmentById.getChildFragmentManager(), fragmentById.getId());
        provider.addNavigator(fixFragmentNavictor);
        FragmentNavigator fragmentNavigator = new FragmentNavigator(this, fragmentById.getChildFragmentManager(), fragmentById.getId());
        provider.addNavigator(fragmentNavigator);
        navDestinations = InitNavGraph.initBillPrintNavGraph(provider, fixFragmentNavictor, fragmentNavigator);
        navController.setGraph(navDestinations);
    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        resetAutoExitTime();//页面触摸重置60s倒计时
////        WindowUtils.countDown();
//        return super.dispatchTouchEvent(ev);
//    }

    //触发重置60s倒计时
    public void resetAutoExitTime() {
        System.out.println("resetAutoExitTime");
        DownTimerService.downTimeResetCount = 0;
        DownTimerService.isRestartTouched = true;//重启后触摸过
    }
}