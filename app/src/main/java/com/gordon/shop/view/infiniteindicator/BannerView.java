/**
 * BannerView Create on 2015/10/28
 * Copyright (c) 2013 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)
 */

package com.gordon.shop.view.infiniteindicator;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.gordon.shop.R;


/**
 * @author Gordon
 * @since 2016/3/18
 * do(广告控件)
 */
public class BannerView extends LinearLayout {

    protected Context context;
    private View view;
//    private LayoutInflater inflater;
    protected InfiniteIndicatorLayout mCustoemIndicatorLayout;

    private float xDown;// 记录手指按下时的横坐标。
    private float xMove;// 记录手指移动时的横坐标。
    private float yDown;// 记录手指按下时的纵坐标。
    private float yMove;// 记录手指移动时的纵坐标。
    private boolean viewpagersroll = false;// 当前是否是viewpager滑动

    public BannerView(Context context) {
        super(context);
        this.context = context;
//        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        initView();
    }

    public BannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    public BannerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    private void initView() {
        view = View.inflate(context, R.layout.banner, null);
        mCustoemIndicatorLayout = (InfiniteIndicatorLayout) view.findViewById(R.id.indicator_custome_circle);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        this.addView(view, params);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mCustoemIndicatorLayout.stopAutoScroll();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mCustoemIndicatorLayout.startAutoScroll();
    }

    /**
     * 垂直方向的移动交给ListView处理
     *
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            // 记录按下时的位置
            xDown = ev.getRawX();
            yDown = ev.getRawY();
        } else if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            xMove = ev.getRawX();
            yMove = ev.getRawY();
            if (Math.abs(yMove - yDown) >= 5 && Math.abs(xMove - xDown) <= 20) {
                getParent().requestDisallowInterceptTouchEvent(false);
                return false;
            }
        } else if (ev.getAction() == MotionEvent.ACTION_CANCEL) {
            mCustoemIndicatorLayout.startAutoScroll();
        }
        return super.dispatchTouchEvent(ev);
    }
}
