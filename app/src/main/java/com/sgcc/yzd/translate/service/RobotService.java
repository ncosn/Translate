package com.sgcc.yzd.translate.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.blankj.utilcode.util.SPUtils;

import java.util.Date;

//public class RobotService extends Service {
//    private final static String TAG = "RobotService";
//    private final static int SERVICE_ID = 10;
//    //    private RobotBinder mBinder;
//    private ServiceConnection serviceConnection;
//
//    @Override
//    public IBinder onBind(Intent intent) {
//        Log.e("TAG", "RobotService绑定");
////        return mBinder;
//        return new ProcessConnection.Stub() {
//            @Override
//            public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
//
//            }
//        };
//    }
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        Log.e("TAG", "RobotService 创建：" + new Date().toLocaleString());
////        mBinder = new RobotBinder();
//        serviceConnection = new ServiceConnection();
////        startForeground(SERVICE_ID, new Notification());
////        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
////            startService(new Intent(this, InnerService.class));
////        }
//        startGuardService();
//    }
//
//    public void startGuardService() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Intent service = new Intent("com.example.myapplication.service.GuardService");
//                String packageName = "com.example.myapplication";
//                service.setComponent(new ComponentName(packageName, "com.example.myapplication.service.GuardService"));
//                startService(service);
//                bindService(service, serviceConnection, Context.BIND_AUTO_CREATE);
//            }
//        }).start();
//    }
//
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        //绑定本地守护Service，必须实现AIDL否则bindService在这没有作用
//        Log.e("TAG","RobotService启动");
//
//        String asset_path = SPUtils.getInstance().getString("assetPath");
//        String debug_path = SPUtils.getInstance().getString("debugPath");
//        SpeechUtil.getInstance().initNui(asset_path, debug_path, new Callback<String>() {
//            @Override
//            public void success(String s) {
//                Log.e(TAG, "success:" + s);
//                LogTools.info(s);
//            }
//
//            @Override
//            public void fail(String s) {
//                Log.e(TAG, "fail:" + s);
//            }
//        });
//
////        bindService(new Intent(this, GuardService.class), serviceConnection, BIND_AUTO_CREATE);
//
////        Timer timer = new Timer();
////        timer.schedule(new TimerTask() {
////            @Override
////            public void run() {
////                Intent intent1 = new Intent("ACTION");
//////                intent1.setPackage("com.example.application");
////                sendBroadcast(intent1);
////
////                Log.e("TAG","broadcast");
////            }
////        },10_000);
//
//
//        return super.onStartCommand(intent, flags, startId);
//    }
//
//    @Override
//    public void onDestroy() {
//        Log.e("TAG", "RobotService销毁:"+ new Date().toString());
//        unbindService(serviceConnection);
//        super.onDestroy();
//    }
//
//    class ServiceConnection implements android.content.ServiceConnection {
//
//        @Override
//        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
//            //服务连接后回调，即返回到GuardService的onBind方法中
//            Log.e("TAG","Service:建立连接");
//        }
//
//        @Override
//        public void onServiceDisconnected(ComponentName componentName) {
//            Log.e("TAG","守护进程可能被干掉了，拉活");
//            //连接中断后回调，再启动子进程所在的Service，并进行绑定，通过启动主进程的服务强行拉活
////            startService(new Intent(RobotService.this, GuardService.class));
////            bindService(new Intent(RobotService.this, GuardService.class), serviceConnection,
////                    BIND_AUTO_CREATE);
//            startGuardService();
//        }
//    }
//
////    public static class InnerService extends Service {
////        @Override
////        public void onCreate() {
////            super.onCreate();
////            startForeground(SERVICE_ID, new Notification());
////            stopSelf();
////        }
////
////        @Nullable
////        @Override
////        public IBinder onBind(Intent intent) {
////            return null;
////        }
////    }
//
//    class RobotBinder extends ProcessConnection.Stub{
//        @Override
//        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
//
//        }
//    }
//}