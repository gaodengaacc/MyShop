/**
 * InfiniteViewPager Create on 2015/7/27
 * <p/>
 * <p/>
 * Copyright (c) 2013 by GreenShore Network
 * <p/>
 * Company: 
 * 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)
 */

package com.gordon.shop.view.infiniteindicator;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @ClassName: InfiniteViewPager
 * @author 彭赞
 * @date 2015/7/27
 * @Version 1.0
 *
 */
public class InfiniteViewPager extends ViewPager {

	private float xDown;// 记录手指按下时的横坐标。
	private float xMove;// 记录手指移动时的横坐标。
	private float yDown;// 记录手指按下时的纵坐标。
	private float yMove;// 记录手指移动时的纵坐标。
	private boolean viewpagersroll = false;// 当前是否是viewpager滑动

	public InfiniteViewPager(Context context) {
		super(context);
	}

	public InfiniteViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}


	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		//垂直滑动，返回给父类处理
		if (ev.getAction() == MotionEvent.ACTION_DOWN) {
			// 记录按下时的位置
			xDown = ev.getRawX();
			yDown = ev.getRawY();
		} else if (ev.getAction() == MotionEvent.ACTION_MOVE) {
			xMove = ev.getRawX();
			yMove = ev.getRawY();
			if (viewpagersroll) {
				// viewpager自己处理滑动效果
				getParent().requestDisallowInterceptTouchEvent(true);
				return super.dispatchTouchEvent(ev);
			}
			// 这里的动作判断是Viewpager滑动,ListView不滑动
			if (Math.abs(yMove - yDown) < 5 && Math.abs(xMove - xDown) > 20) {
				viewpagersroll = true;
			} else {
				// 由父容器listview来处理滑动效果
				return false;
			}
		} else if (ev.getAction() == MotionEvent.ACTION_UP) {
			viewpagersroll = false;
		}
		return super.dispatchTouchEvent(ev);
	}
}