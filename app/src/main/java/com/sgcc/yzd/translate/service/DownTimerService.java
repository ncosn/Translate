package com.sgcc.yzd.translate.service;

import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import com.blankj.utilcode.util.LogUtils;
import com.sgcc.yzd.translate.MyApplication;
import com.sgcc.yzd.translate.utils.WindowUtils;

import java.lang.ref.WeakReference;

public class DownTimerService extends Service {

    private CallbackHandler mHandler;
    private static final int ACTION_YES = 110;
    private static final int ACTION_NO = 111;
    private DownTimerThread mDownTimerThread;
    public static volatile int downTimeResetCount = 0;
    public static volatile boolean isRestartTouched = false;//重启后是否触摸过
    private static int maxResetTime = 60;//最大重置时间为60s=(maxResetTime+10s弹窗时间)

    /**
     * 回调Handler
     */
    private static class CallbackHandler extends Handler {
        private WeakReference<DownTimerService> mReference;

        private CallbackHandler(DownTimerService mContext) {
            mReference = new WeakReference<>(mContext);
        }

        @Override
        public void handleMessage(Message msg) {
            DownTimerService mContext = mReference.get();
            if (mContext != null) {
                switch (msg.what) {
                    case ACTION_YES:
                        System.out.println("hidePopUpWindow");
                        WindowUtils.hidePopupWindow();
                        break;

                    case ACTION_NO:
                        System.out.println("showPopUpWindow");
//                        WindowUtils.countDown(mContext);
                        WindowUtils.showPopupWindow(mContext);
                        break;
                }

            }
        }
    }

    public DownTimerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
//        LogUtils.e(DownTimerService.class, "DownTimerService - onBind - Thread = " + Thread.currentThread().getName());
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (mHandler == null) {
            mHandler = new CallbackHandler(this);
        }
//        if (DeviceUtil.getBuildBrandModel().contains("CB013-E") || (Build.MODEL.contains("rk3399") || Build.MANUFACTURER.contains("rockchip"))) {
            /**
             * 60秒倒计时
             */
            initThread();
//        }
        LogUtils.e(DownTimerService.class, "DownTimerService - onCreate - Thread = " + Thread.currentThread().getName());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("DownTimerService---------onStartCommand---------Thread"+Thread.currentThread().getName());
        LogUtils.e(DownTimerService.class, "DownTimerService - onStartCommand - startId = " + startId + ", Thread = " + Thread.currentThread().getName());
        if (intent!=null){
            int num = intent.getIntExtra("num", 0);
            if (num!=0){
                maxResetTime = num;
                downTimeResetCount = 0;
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void initThread() {
        System.out.println("DownTimerService---------initThread");
        if (mDownTimerThread == null) {
            mDownTimerThread = new DownTimerThread();
        }
        if (!MyApplication.getApplication().mDownTimerIsRun) {
            System.out.println("DownTimeThread:start()");
            mDownTimerThread.start();
            MyApplication.getApplication().mDownTimerIsRun = true;
        }
    }

    private class DownTimerThread extends Thread {
        private int onceTime = 5;//每次阻塞的间隔单位s

        @Override
        public void run() {
            System.out.println("DownTimerThread:run()");
            while (true) {
                if (MyApplication.getApplication().isDeal) {
                    if (isRestartTouched) {
                        downTimeResetCount++;
                    }
                    try {
                        Thread.sleep(1000 * onceTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (downTimeResetCount * onceTime >= maxResetTime && isRestartTouched) {//展示10s倒计时弹窗
                        downTimeResetCount = 0;
                        System.out.println("倒计时");
//                        LogUtils.e(DownTimerService.class, "--------------message.what =" + ACTION_NO);
                        Message message = new Message();
                        message.what = ACTION_NO;
                        mHandler.sendMessage(message);
                        try {
                            Thread.sleep(1000 * 10);//等待倒计时弹窗运行结束
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
//                        LogUtils.e(DownTimerService.class, "--------------message.what =" + ACTION_YES);
                        Message message = new Message();
                        message.what = ACTION_YES;
                        mHandler.sendMessage(message);
                    }
                }
            }
        }
    }

    public void cancelThread() {
        MyApplication.getApplication().isDeal = false;
        MyApplication.getApplication().mDownTimerIsRun = false;
        if (mDownTimerThread != null && mDownTimerThread.isAlive()) {
            mDownTimerThread.interrupt();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MyApplication.getApplication().isDeal = false;
        stopSelf(-1);
        LogUtils.e(DownTimerService.class, "onDestroy执行了");
//        WindowUtils.hidePopupWindow();

        if (mDownTimerThread != null && mDownTimerThread.isAlive()) {
            mDownTimerThread.interrupt();
        }
    }
}
