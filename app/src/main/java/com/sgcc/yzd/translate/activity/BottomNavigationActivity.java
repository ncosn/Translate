package com.sgcc.yzd.translate.activity;

import android.os.Bundle;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.sgcc.yzd.translate.R;
import com.sgcc.yzd.translate.databinding.ActivityBottomNavigationBinding;

public class BottomNavigationActivity extends AppCompatActivity {

    private ActivityBottomNavigationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityBottomNavigationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.myToolbar);


        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_bottom_navigation);
        // 有标题栏的话设置标题
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        // 设置 bottomNavigationView , 绑定 controller
        NavigationUI.setupWithNavController(binding.navView, navController);

        // 消息提示图标
        binding.navView.getOrCreateBadge(R.id.navigation_home);
        binding.navView.getOrCreateBadge(R.id.navigation_dashboard).setNumber(3);

        binding.navView.setOnNavigationItemSelectedListener(item -> {
            navController.navigate(item.getItemId());
            return true;
        });
    }



}