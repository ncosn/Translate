package com.sgcc.yzd.translate.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.sgcc.yzd.translate.R;
import com.sgcc.yzd.translate.callback.DataCallback;
import com.sgcc.yzd.translate.config.InfoString;
import com.sgcc.yzd.translate.model.TranslationResponse;
import com.sgcc.yzd.translate.utils.HttpBusinessUtils;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    /**翻译目标语言，0表示英语，1表示日语，2表示韩语，3表示德语，4表示中文（简体），5中文（繁体），6表示中文（粤语）*/
    private int lang = 0;

    EditText etTrans;
    Button btTrans;
    TextView tvResult;
    Spinner spLang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(new Intent(MainActivity.this,SecondActivity.class));
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        btTrans.setOnClickListener(v -> {
            Map<String,String> map = new HashMap<>();
            map.put("grantType", InfoString.GRANT_TYPE);
            map.put("apiKey",InfoString.API_KEY);
            map.put("secretKey",InfoString.SECRET_KEY);
            map.put("from","zh");
            switch (lang) {
                case 0:
                    map.put("to","en");
                    break;
                case 1:
                    map.put("to","jp");
                    break;
                case 2:
                    map.put("to","kor");
                    break;
                case 3:
                    map.put("to","de");
                    break;
                case 4:
                    map.put("to","zh");
                    break;
                case 5:
                    map.put("to","cht");
                    break;
                case 6:
                    map.put("to","yue");
                    break;
                default:
                    break;
            }
            map.put("q", etTrans.getText().toString().trim());
            HttpBusinessUtils.postTranslateAccessToken(map, new DataCallback<TranslationResponse>() {
                @Override
                public void onSuccess(TranslationResponse result) {
                    // 获取译文内容
                    if (result.getResult()!=null) {
                        // 正常返回
                        String translation = result.getResult().getTransResult().get(0).getDst();
                        tvResult.setText(translation);
                    } else {
                        // 异常返回
                        String error = result.getErrorMsg();
                        tvResult.setText(error);
                    }
                }

                @Override
                public void onError(String error) {
                    // 显示异常信息
                    tvResult.setText(error);
                }
            });
        });

        spLang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // 选择翻译目标语言
                spLang.setSelection(position);
                lang=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spLang.setSelection(0);
            }
        });
    }

    private void initData() {
    }

    private void initView() {
        etTrans = findViewById(R.id.et_trans);
        btTrans = findViewById(R.id.bt_trans);
        tvResult = findViewById(R.id.tv_result);
        spLang = findViewById(R.id.spinner_lang);

    }
}