/**
 * Banner Create on 2015/7/25
 * <p/>
 * <p/>
 * Copyright (c) 2013 by GreenShore Network
 * <p/>
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)
 */

package com.gordon.shop.pagemain.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;


import com.gordon.shop.R;
import com.gordon.shop.view.infiniteindicator.BannerView;
import com.gordon.shop.view.infiniteindicator.InfiniteIndicatorLayout;
import com.gordon.shop.view.infiniteindicator.indicator.CircleIndicator;
import com.gordon.shop.view.infiniteindicator.slideview.BaseSliderView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 */
public class NewsBannerView extends BannerView {
    private onItemClick onItemClickListener;

    public NewsBannerView(Context context) {
        super(context);
        List<Integer> list = new ArrayList<>();
        list.add(R.mipmap.bottom_select1);
        list.add(R.mipmap.bottom_select2);
        list.add(R.mipmap.bottom_select3);
        list.add(R.mipmap.bottom_select4);
        list.add(R.mipmap.bottom_select5);
        addBannerSliderViewNew(list);
    }

    public interface onItemClick {
        public void onClick(List list, int position);
    }

    public void setOnItemClickListener(onItemClick clickListener) {
        this.onItemClickListener = clickListener;

    }
    public NewsBannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NewsBannerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 保持宽高比为640×280
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // Children are just made to fill our space.
        int childWidthSize = getMeasuredWidth();
        //宽高比640×280
        int childHeightSize = Math.round(childWidthSize * 0.4375f);// 0.4375f = 280/640
        widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(childWidthSize, View.MeasureSpec.EXACTLY);
        heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(childHeightSize, View.MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void addBannerSliderViewNew(final List<Integer> topicDetailsList) {
        if (topicDetailsList != null && topicDetailsList.size() > 0) {
//			final Map<Long, Msgs.PostElement> postElementMap = new HashMap<>();
            for (int i=0;i<topicDetailsList.size();i++) {
//				Msgs.PostContent postContent = topicDetail.getPostContent();
//				List<Msgs.PostElement> postElementList = postContent.getElementsList();
//				if (postElementList != null && postElementList.size() > 0) {
//					for (Msgs.PostElement pe : postElementList) {
//						if (pe.getType() == Msgs.PostElementType.PE_IMAGE_ID_REF) {
//							postElementMap.put(topicDetail.getId(), pe);
//							break;
//						}
//					}
//				}
//				Msgs.PostElement element = postElementMap.get(topicDetail.getId());
                BannerSliderView bannerSliderView = new BannerSliderView(context, topicDetailsList.get(i));
//				bannerSliderView.setScaleType(BaseSliderView.ScaleType.CenterCrop);
                final int finalI = i;
                bannerSliderView.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                    @Override
                    public void onSliderClick(BaseSliderView slider) {
                       onItemClickListener.onClick(topicDetailsList, finalI);
                    }
                });
                mCustoemIndicatorLayout.addSlider(bannerSliderView);
            }
            mCustoemIndicatorLayout.setIndicatorPosition(InfiniteIndicatorLayout.IndicatorPosition.Right_Bottom);
            mCustoemIndicatorLayout.setInterval(8 * 1000);
            CircleIndicator circleIndicator = ((CircleIndicator) mCustoemIndicatorLayout.getPagerIndicator());
            final float density = getResources().getDisplayMetrics().density;
            circleIndicator.setRadius(5 * density);
            circleIndicator.setDrawBitmap(true);
            circleIndicator.setDrawStrock(false);
            circleIndicator.setPointBitmap(BitmapFactory.decodeResource(getContext().getResources(), R.mipmap.news_banner_dot_nor));
            circleIndicator.setPointPreBitmap(BitmapFactory.decodeResource(getContext().getResources(), R.mipmap.news_banner_dot_pre));
        }
    }

}