package com.gordon.shop.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * @author Gordon
 * @since 2016/7/27
 * do()
 */
public class MyLinearLayout extends LinearLayout {


    public MyLinearLayout(Context context) {
        super(context);
        initView();
    }

    public MyLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void initView() {
        requestDisallowInterceptTouchEvent(true);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i("layout","Layout_dispatchTouchEvent");
//        initView();
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i("layout","Layout_onInterceptTouchEvent");
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("layout","Layout_onTouchEvent");
        return super.onTouchEvent(event);
    }


}
