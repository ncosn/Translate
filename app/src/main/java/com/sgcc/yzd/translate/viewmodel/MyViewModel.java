package com.sgcc.yzd.translate.viewmodel;

import android.view.View;
import android.widget.AdapterView;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sgcc.yzd.translate.callback.DataCallback;
import com.sgcc.yzd.translate.config.InfoString;
import com.sgcc.yzd.translate.model.TranslationData;
import com.sgcc.yzd.translate.model.TranslationResponse;
import com.sgcc.yzd.translate.utils.HttpBusinessUtils;

import java.util.HashMap;
import java.util.Map;

public class MyViewModel extends ViewModel {
    private MutableLiveData<String> text;
    private MutableLiveData<String> translation;
    private MutableLiveData<Integer> lang;

    public MutableLiveData<String> getText() {
        if (text ==null) {
            text = new MutableLiveData<>();
            text.setValue("");
        }
        System.out.println("getText..."+text.getValue());
        return text;
    }

    public MutableLiveData<String> getTranslation() {
        if (translation ==null) {
            translation = new MutableLiveData<>();
            translation.setValue("");
        }
        System.out.println("getTranslation..."+translation.getValue());
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation.setValue(translation);
    }

    public MutableLiveData<Integer> getLang() {
        if (lang == null) {
            lang = new MutableLiveData<>();
            lang.setValue(0);
        }
        return lang;
    }

    public void setLang(int lang) {
        this.lang.setValue(lang);
    }

    public void transPost() {
        System.out.println(lang);
        Map<String,String> map = new HashMap<>();
        map.put("grantType", InfoString.GRANT_TYPE);
        map.put("apiKey",InfoString.API_KEY);
        map.put("secretKey",InfoString.SECRET_KEY);
        map.put("from","zh");
        switch ((lang.getValue()!=null)?lang.getValue():0) {
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
        map.put("q", text.getValue());
        HttpBusinessUtils.postTranslateAccessToken(map, new DataCallback<TranslationResponse>() {
            @Override
            public void onSuccess(TranslationResponse result) {
                // 获取译文内容
                if (result.getResult()!=null) {
                    // 正常返回
                    String translation = result.getResult().getTransResult().get(0).getDst();
                    setTranslation(translation);
                } else {
                    // 异常返回
                    String error = result.getErrorMsg();
                    setTranslation(error);
                }
            }

            @Override
            public void onError(String error) {
                // 显示异常信息
                setTranslation(error);
            }
        });
    }

    public void onSelectItem(AdapterView<?> parent, View view, int pos, long id) {
        getLang();
        System.out.println(pos);
        setLang(pos);
    }

}
