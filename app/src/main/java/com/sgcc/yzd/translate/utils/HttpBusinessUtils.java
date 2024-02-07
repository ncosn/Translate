package com.sgcc.yzd.translate.utils;

import android.content.Context;

import androidx.lifecycle.LifecycleOwner;

import com.sgcc.yzd.translate.callback.DataCallback;
import com.sgcc.yzd.translate.model.HttpResponse;
import com.sgcc.yzd.translate.model.ResponseData;
import com.sgcc.yzd.translate.model.TranslationResponse;
import com.sgcc.yzd.translate.retrofit.Api;
import com.sgcc.yzd.translate.retrofit.ApiMethods;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class HttpBusinessUtils {
    /**
     * 获取鉴权参数 access_token
     *
     * @param map
     * @param dataCallback
     */
    public static void postAccessToken(Map<String,String> map, DataCallback<ResponseData> dataCallback) {
        Observer<ResponseData> observer = new Observer<ResponseData>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("onSubscribe...");
            }

            @Override
            public void onNext(ResponseData value) {
                System.out.println("onNext...");
                dataCallback.onSuccess(value);
            }

            @Override
            public void onError(Throwable e) {
                dataCallback.onError(e.toString());
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete...");
            }
        };
        ApiMethods.newInstance().postAccessToken(observer,map);
    }

    /**
     * 翻译文本
     *
     * @param map
     * @param callback
     */
    public static void postTranslate(Map<String, String> map, DataCallback<TranslationResponse> callback) {
        Observer<TranslationResponse> observer = new Observer<TranslationResponse>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("onSubscribe...");
            }

            @Override
            public void onNext(TranslationResponse value) {
                System.out.println("onNext...");
                callback.onSuccess(value);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError...");
                callback.onError(e.toString());
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete...");
            }
        };
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"from\":\""+map.get("from")+"\",\"to\":\""+map.get("to")+"\",\"q\":\""+map.get("q")+"\"}");

        ApiMethods.newInstance().postTranslate(observer, body);
    }


    /**
     * 获取鉴权参数并翻译文本
     *
     * @param map
     * @param callback
     */
    public static void postTranslateAccessToken(Map<String, String> map, DataCallback<TranslationResponse> callback) {
        Observer<TranslationResponse> observer = new Observer<TranslationResponse>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("onSubscribe...");
            }

            @Override
            public void onNext(TranslationResponse value) {
                System.out.println("onNext...");
                callback.onSuccess(value);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError...");
                callback.onError(e.toString());
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete...");
            }
        };
        ApiMethods.newInstance().postTranslateAccessToken(observer, map);
    }

    public static void postCurtainControl(Number deviceType, Number deviceNo, Number angle, Number deviceId, Number distance,DataCallback<HttpResponse> callback) {
        Observer<HttpResponse> observer = new Observer<HttpResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(HttpResponse value) {
                callback.onSuccess(value);
            }

            @Override
            public void onError(Throwable e) {
                callback.onError(e.toString());
            }

            @Override
            public void onComplete() {

            }
        };
        ApiMethods.newInstance().postCurtain(deviceType,deviceNo,angle,deviceId,distance,observer);
    }
}
