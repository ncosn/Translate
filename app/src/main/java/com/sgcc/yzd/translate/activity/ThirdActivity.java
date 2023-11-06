package com.sgcc.yzd.translate.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.sgcc.yzd.translate.R;
import com.sgcc.yzd.translate.databinding.ActivityThirdBinding;

import org.jetbrains.annotations.NotNull;

public class ThirdActivity extends AppCompatActivity {

    private ActivityThirdBinding binding;
    private Boolean isEditing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityThirdBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        // 设置标题栏
        setSupportActionBar(binding.myToolbar);
        // 启用向上返回按钮，自定义图标，并设置自定义点击事件
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);
        binding.myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 打开左侧菜单栏
                binding.drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        // 侧滑菜单栏点击事件
        binding.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navCall:
                        System.out.println("navigationView:Call");
                        binding.drawerLayout.closeDrawers();
                        break;
                    case R.id.navLocation:
                        System.out.println("navigationView:Location");
                        binding.drawerLayout.closeDrawers();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });


        binding.button.setOnClickListener(v -> {
            Glide.with(this)
//                .load(R.drawable.fragment)
                    .load("http://b247.photo.store.qq.com/psb?/V11ZojBI312o2K/63aY8a4M5quhi.78*krOo7k3Gu3cknuclBJHS3g1fpc!/b/dDXWPZMlBgAA")
                    .fitCenter()
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.fragment)
                    .into(binding.ivGlide);
        });

        binding.etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isEditing = true;
                invalidateOptionsMenu();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                isEditing = false;
            }
        });

//        // ToolBar菜单栏点击事件
//        binding.myToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.item_scan:
//                        System.out.println("扫一扫");
//                        break;
//                    case R.id.item_add:
//                        System.out.println("添加新朋友");
//                        break;
//                    default:
//                        break;
//                }
//                return true;
//            }
//        });

    }

    /**
     * 创建操作栏(ActionBar)的菜单栏
     * @param menu 菜单
     * @return true操作完成
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        System.out.println("onCreateOptionsMenu");
        getMenuInflater().inflate(R.menu.toolbar_menu_third,menu);
        MenuItem searchItem = menu.findItem(R.id.item_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                System.out.println(query);
                //添加下面一句,防止数据两次加载
//                searchView.setIconified(true);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        System.out.println("onOptionsItemSelected:");
        switch (item.getItemId()) {
            case R.id.item_scan:
                System.out.println("onOptionsItemSelect:扫一扫");
                break;
            case R.id.item_add:
                System.out.println("onOptionsItemSelected:添加新朋友");
                break;
            case R.id.item_search:
                System.out.println("onOptionsItemSelected:search");
                break;
            case R.id.item_save:
                isEditing = false;
                invalidateOptionsMenu();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem item = menu.findItem(R.id.item_save);
        item.setVisible(isEditing);
        return true;
    }

}