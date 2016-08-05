package com.gordon.shop.pagemain.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;

import com.gordon.shop.R;

/**
 * @author Gordon
 * @since 2016/8/5
 * do()
 */
public class WindowManagerActivity extends Activity implements View.OnTouchListener {
    private Button mCreateWindowButton;
    private Button mFloatingButton;
    private LayoutParams mLayoutParams;
    private WindowManager mWindowManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.window_manage_activity);
        initView();
    }

    private void initView() {
        mCreateWindowButton = (Button) findViewById(R.id.window_button);
        mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
    }

    public void onButtonClick(View v) {
        if (v == mCreateWindowButton) {
            if (mFloatingButton != null)
                return;
                mFloatingButton = new Button(this);
            mFloatingButton.setText("move me");
            mLayoutParams = new LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0, 0,
                    PixelFormat.TRANSPARENT);
            mLayoutParams.flags = LayoutParams.FLAG_NOT_TOUCH_MODAL
                    | LayoutParams.FLAG_NOT_FOCUSABLE
                    | LayoutParams.FLAG_SHOW_WHEN_LOCKED;
            mLayoutParams.type = LayoutParams.TYPE_SYSTEM_ERROR;
            mLayoutParams.gravity = Gravity.LEFT | Gravity.TOP;
            mLayoutParams.x = 100;
            mLayoutParams.y = 300;
            mFloatingButton.setOnTouchListener(this);
            mWindowManager.addView(mFloatingButton, mLayoutParams);
        }
    }

    private int x, y, moveX, moveY;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //相对于屏幕的位置
                x = (int) event.getRawX();
                y = (int) event.getRawY();
                break;

            case MotionEvent.ACTION_MOVE:
                moveX = (int) (event.getRawX() - x);
                moveY = (int) (event.getRawY() - y);
                if (Math.abs(moveX) < 5 || Math.abs(moveY) < 5) { //如果手指一动的距离很小就break
                    break;
                }
                mLayoutParams.x += moveX;
                mLayoutParams.y += moveY;
                mWindowManager.updateViewLayout(mFloatingButton, mLayoutParams);
                x = (int) event.getRawX();
                y = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }

        return false;
    }

    @Override
    protected void onDestroy() {
        try {
            mWindowManager.removeView(mFloatingButton);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

}
