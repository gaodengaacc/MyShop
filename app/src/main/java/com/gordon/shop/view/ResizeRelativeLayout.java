package com.gordon.shop.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
/**
 * @author Gordon
 * @since 2016/3/18
 * do()
 */
public class ResizeRelativeLayout extends RelativeLayout {

    private OnResizeListener mListener;

    public interface OnResizeListener {
        void OnResize(int w, int h, int oldw, int oldh);
    }

    public void setOnResizeListener(OnResizeListener l) {
        mListener = l;
    }

    public ResizeRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        if (mListener != null) {
            mListener.OnResize(w, h, oldw, oldh);
        }
    }
}