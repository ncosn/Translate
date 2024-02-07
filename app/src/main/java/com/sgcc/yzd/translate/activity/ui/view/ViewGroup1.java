package com.sgcc.yzd.translate.activity.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class ViewGroup1 extends ViewGroup {
    public ViewGroup1(Context context) {
        super(context);
    }

    public ViewGroup1(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewGroup1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ViewGroup1(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measureWidth=MeasureSpec.getSize(widthMeasureSpec);
        int measureHeight=MeasureSpec.getSize(heightMeasureSpec);
        int measureWidhtMode=MeasureSpec.getMode(widthMeasureSpec);
        int measureHeightMode=MeasureSpec.getMode(heightMeasureSpec);
        //这里计算width,height
        int height=0;
        int width=0;
        int count=getChildCount();
        for(int i=0;i<count;i++){
            //测量子控件
            View child=getChildAt(i);
            measureChild(child,widthMeasureSpec,heightMeasureSpec);
            int childWidth=child.getMeasuredWidth();
            int childHeight=child.getMeasuredHeight();
            height+=childHeight;//高度叠加
            width=Math.max(width,childWidth);//宽度取最大
        }
        setMeasuredDimension((measureWidhtMode==MeasureSpec.EXACTLY)?measureWidth:width,(measureHeightMode==MeasureSpec.EXACTLY)?measureHeight:height);
    }

    @Override
    protected void onLayout(boolean b, int l, int i1, int i2, int i3) {
        int top=0;
        int count=getChildCount();
        for(int i =0;i<count;i++) {
            View child=getChildAt(i);
            int childWidth=child.getMeasuredWidth();
            int childHeight=child.getMeasuredHeight();
            child.layout(0,top,childWidth,childHeight);
            top+=childHeight;
        }
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("TAG", "ViewGroup1:dispatchTouchEvent: "+ ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("TAG", "ViewGroup1:onTouchEvent: "+event.getAction());
        return super.onTouchEvent(event);
    }
}
