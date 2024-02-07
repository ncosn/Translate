package com.sgcc.yzd.translate.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;

import com.sgcc.yzd.translate.R;
import com.sgcc.yzd.translate.model.User;
import com.sgcc.yzd.translate.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    // 功能组
    private String[] functions = new String[]{"翻译","glide","长图","BottomNavigation", "ViewPager", "Navigation","View","Robot","WebView"};
    private int[] ids = new int[]{R.drawable.ic_baseline_translate_24,R.drawable.ic_baseline_image_search_24, R.drawable.ic_baseline_image_24, R.drawable.ic_baseline_power_input_24,
            R.drawable.ic_baseline_power_input_24, R.drawable.ic_baseline_navigation_24, R.drawable.baseline_view_compact_24, R.drawable.baseline_view_compact_24,
            R.drawable.baseline_view_compact_24};

//    EditText etTrans;
//    Button btTrans;
//    TextView tvResult;
//    Spinner spLang;
    private com.sgcc.yzd.translate.databinding.ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 使用ViewBinding绑定布局
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 设置标题栏
        setSupportActionBar(binding.myToolbar);
        // 隐藏标题栏
        //getSupportActionBar().hide();
        // 显示标题栏
        //getSupportActionBar().show();



        final List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < functions.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("functions",functions[i]);
            map.put("img",ids[i]);
            list.add(map);
        }
        String[] from = new String[]{"functions","img"};
        int[] to = new int[]{R.id.tv_item, R.id.iv_item};
        // 待优化，使用自定义适配器 getView() 动态调整列宽
        SimpleAdapter adapter = new SimpleAdapter(this,list,R.layout.gv_item,from,to);
        binding.gvFunction.setAdapter(adapter);

        binding.gvFunction.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("id:"+id);
                switch (position) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, SecondActivity.class));
                        break;
                    case 1:
                        //通过Bundle 传递
                        User user = new User("cns","ddd@");
                        Bundle params = new Bundle();
                        params.putParcelable("user", user);
                        Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
                        intent.putExtra("bundle",params);
                        startActivity(intent);
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, FourthActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(MainActivity.this, BottomNavigationActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(MainActivity.this, MyViewPagerActivity.class));
                        break;
                    case 5:
                        startActivity(new Intent(MainActivity.this, NavigationActivity.class));
                        break;
                    case 6:
//                        startActivity(new Intent(MainActivity.this,FifthActivity.class));
                        startActivity(new Intent(MainActivity.this,ViewActivity.class));
                    case 7:
                        startActivity(new Intent(MainActivity.this, RobotActivity.class));

                    case 8:
                        startActivity(new Intent(MainActivity.this, WebViewActivity.class));
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu_third,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        System.out.println("onOptionsItemSelected");
        return super.onOptionsItemSelected(item);
    }

}