package com.sgcc.yzd.translate.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.util.Date;

//public class GuardService extends Service {
//    private final static int SERVICE_ID = 10;
//    private RobotBinder mBinder;
//    private ServiceConnection serviceConnection;
//
//    @Override
//    public IBinder onBind(Intent intent) {
//        Log.e("TAG", "GuardService绑定");
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
//        Log.e("TAG","GuardService 创建:" + new Date().toLocaleString());
////        mBinder = new RobotBinder();
//        serviceConnection = new ServiceConnection();
////        startForeground(SERVICE_ID, new Notification());
////        //如果 18 以上的设备 启动一个Service startForeground给相同的id,然后结束这个Service
////        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
////            startService(new Intent(this, InnerService.class));
////        }
//        startRobotService();
//    }
//
//    public void startRobotService() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Intent service = new Intent("com.example.myapplication.service.RobotService");
//                String packageName = "com.example.myapplication";
//                service.setComponent(new ComponentName(packageName, "com.example.myapplication.service.RobotService"));
//                startService(service);
//                bindService(service, serviceConnection, Context.BIND_AUTO_CREATE);
//            }
//        }).start();
//    }
//
//
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        // 绑定本地守护service
//        Log.e("TAG","GuardService启动");
////        bindService(new Intent(this, RobotService.class), serviceConnection, BIND_AUTO_CREATE);
//        return super.onStartCommand(intent, flags, startId);
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        Log.e("TAG","GuardService销毁："+ new Date().toLocaleString());
//        unbindService(serviceConnection);
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
//            Log.e("TAG","主进程可能被干掉了，拉活");
//            //连接中断后回调，再启动主进程所在的Service，再进行绑定，通过启动主进程的服务强行拉活，另外先start再bind是为了确保，在其他地方调用unbind时候不被停止掉
////            startService(new Intent(GuardService.this, RobotService.class));
////            bindService(new Intent(GuardService.this, RobotService.class), serviceConnection, BIND_AUTO_CREATE);
//            startRobotService();
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