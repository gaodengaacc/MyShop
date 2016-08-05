package com.gordon.shop.pagemain.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

import com.gordon.shop.R;
import com.gordon.shop.view.MyButton;
import com.gordon.shop.view.MyImageView;
import com.gordon.shop.view.MyTextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author Gordon
 * @since 2016/7/27
 * do()
 */
public class OnClickEventActivity extends Activity {
    @Bind(R.id.button_onclick)
    MyButton click_button;
    @Bind(R.id.text_onclick)
    MyTextView click_text;
    @Bind(R.id.image_onclick)
    MyImageView click_image;
    @Bind(R.id.layout_button_onclick)
    ViewGroup click_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onclick_layout);
        ButterKnife.bind(this);
        clickAble();
        initView();
    }

    private void clickAble() {
        Log.i("button", "Button_isClick="+click_button.isClickable());
        Log.i("textview", "TextView_isClick="+click_text.isClickable());
        Log.i("imageview", "ImageView_isClick="+click_image.isClickable());
    }

    private void initView() {
//        click_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.i("button", "Button_OnClick");
//            }
//        });
//        click_button.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                Log.i("button", "Button_setOnTouchListener");
//                return false;
//            }
//        });
//        click_text.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.i("textview", "textview_OnClick");
//            }
//        });
//        click_text.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                Log.i("textview", "textview_setOnTouchListener");
//                return false;
//            }
//        });
//        click_image.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.i("imageview", "imageview_OnClick");
//            }
//        });
//        click_image.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                Log.i("imageview", "imageview_setOnTouchListener");
//                return false;
//            }
//        });
        click_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("layout", "Layout_OnClick");
            }
        });
        click_layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i("layout", "Layout_OnTouchListener");
                return false;
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("activity", "Activity_onTouchEvent");
        return super.onTouchEvent(event);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i("activity", "Activity_dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

}
