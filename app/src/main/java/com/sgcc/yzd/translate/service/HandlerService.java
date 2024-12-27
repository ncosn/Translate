package com.sgcc.yzd.translate.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import com.blankj.utilcode.util.BusUtils;

public class HandlerService extends Service {
    private Handler handler;
    public HandlerService() {
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            BusUtils.post("platform");
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        handler = new Handler(getMainLooper()) {
//            @Override
//            public void handleMessage(Message msg) {
//                switch (msg.what) {
//                    case 100:
////                        Bundle data = msg.getData();
////                        String asrResult = data.getString("asrResult");
//                        handler.sendEmptyMessage(101);
//                        break;
//                }
//
//            }
//        };
        handler = new Handler();
        handler.postDelayed(runnable, 5000);
        return super.onStartCommand(intent, flags, startId);
    }
}