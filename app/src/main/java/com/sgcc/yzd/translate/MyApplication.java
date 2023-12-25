package com.sgcc.yzd.translate;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.sgcc.yzd.translate.service.DownTimerService;

import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;

public class MyApplication extends Application {

    private static MyApplication mInstance;
    public boolean mDownTimerIsRun = false;
    public boolean isDeal = false;

    @Override
    public void onCreate() {
        super.onCreate();
//        try {
//            mInstance = this;
//            isDeal = true;
//            Intent intent = new Intent(this, DownTimerService.class);
//            intent.putExtra("num", 10);
//            this.startService(intent);
//            System.out.println("MyApplication:onCreate()-------startService");
//        } catch (IllegalStateException e) {
//            e.printStackTrace();
//        }


        // 取消订阅后，抛出的异常无法捕获，导致程序崩溃
        RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.i("TAG","setErrorHandler accept:throwable="+throwable.toString());
            }
        });
    }

    public static MyApplication getApplication() {
        return mInstance;
    }
}
