package com.sgcc.yzd.translate.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.sgcc.yzd.translate.R;
import com.sgcc.yzd.translate.activity.MainActivity;
import com.sgcc.yzd.translate.service.DownTimerService;

import static com.blankj.utilcode.util.ActivityUtils.startActivity;

public class WindowUtils {
    private static View mView = null;
    private static WindowManager mWindowManager = null;
    private static Context mContext = null;
    private static Boolean isShown = false;

    private static CountDownTimer mCountDownTimer;
    private static TextView mCountDownTv;

    public static View countDown(Context mContext) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.count_down_popwindow_view,
                null);
        mCountDownTv = view.findViewById(R.id.countdown_tv);
        mCountDownTv.setText(String.format("%ss", 10));
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
        if (mCountDownTimer == null) {
            mCountDownTimer = new CountDownTimer(10 * 1000L, 1000L) {
                @Override
                public void onTick(long millisUntilFinished) {
                    long second = millisUntilFinished / 1000L;
                    System.out.println(String.format("%ss",second));
                    mCountDownTv.setText(String.format("%ss", second));
                }

                @Override
                public void onFinish() {
//                    context.stopService(new Intent(context, DownTimerService.class));
                    WindowUtils.hidePopupWindow();
//                    Intent intent = new Intent();
//                    intent.setClass(context, WelcomeGuideNewActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                    intent.putExtra("haveNoOperate", true);
//                    SPUtil.getInstance().getBoolean(Constants.IS_LOGIN, false);
//                    HardLedUtil.getInstance().setClose();
//                    context.startActivity(intent);
                    Intent intent = new Intent();
                    intent.setClass(mContext, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("haveNoOperate", true);
                    mContext.startActivity(intent);

                    System.out.println("countDownTimer-----------onFinish");
                    DownTimerService.isRestartTouched = false;//重启后默认没触摸过
                }
            };
        }
        mCountDownTimer.start();
        return view;
    }

    /**
     * 隐藏弹出框
     */
    public static void hidePopupWindow() {
//        ElecApplication.getApplication().isDownTimeDialogShow = false;
//        Log.i(LOG_TAG, "hide " + isShown + ", " + mView);
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
        if (isShown && null != mView) {
            Log.i("WindowUtils:", "hidePopupWindow");
            mWindowManager.removeView(mView);
            isShown = false;
        }
    }

    /**
     * 显示弹出框
     *
     * @param context
     */
    public static void showPopupWindow(final Context context) {
        getDialogPermission(context);
        if (isShown) {
            return;
        }
        isShown = true;
        // 获取应用的Context
        mContext = context.getApplicationContext();
        // 获取WindowManager
        mWindowManager = (WindowManager) mContext
                .getSystemService(Context.WINDOW_SERVICE);
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
//        countDown(context);
        mView = countDown(context);
        final WindowManager.LayoutParams params;
        int LAYOUT_FLAG;
        // 类型
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {//8.0
            LAYOUT_FLAG = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//6.0
            LAYOUT_FLAG = WindowManager.LayoutParams.TYPE_PHONE;
        } else {
            LAYOUT_FLAG = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        }
        params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                LAYOUT_FLAG,
                WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM,
                PixelFormat.TRANSLUCENT);
        params.gravity = Gravity.CENTER;
        mWindowManager.addView(mView, params);
        if (mView != null) {
            mView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    hidePopupWindow();
                    resetAutoExitTime();
                    return false;
                }
            });
        }
        Log.i("WindowUtils", "add view");
    }

    private static void getDialogPermission(Context mContext) {
        //Android6.0 + need require permission
        if (Build.VERSION.SDK_INT >= 23) {
            if (!Settings.canDrawOverlays(mContext)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                startActivity(intent);
                return;
            }
        }
    }

    //触发重置60s倒计时
    public static void resetAutoExitTime() {
        System.out.println("WindowUtils:resetAutoExitTime");
        DownTimerService.downTimeResetCount = 0;
        DownTimerService.isRestartTouched = true;//重启后触摸过
    }

}
