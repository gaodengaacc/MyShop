package com.gordon.shop.pagemain.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.gordon.shop.R;
import com.gordon.shop.view.infiniteindicator.slideview.BaseSliderView;

public class BannerSliderView extends BaseSliderView {

	private int postElement;

	public BannerSliderView(Context context, int postElement) {
		super(context);
		this.postElement = postElement;
	}

	@Override
	public View getView() {
		View v = LayoutInflater.from(getContext()).inflate(R.layout.banner_sliderview_layout,null);
		ImageView target = (ImageView)v.findViewById(R.id.slider_image);
//		if (postElement != 0) {
//			ImageViewUtil.showBigImage(target, postElement.getResourceId(), R.drawable.banner_icon_default);
//		} else {
//			ImageViewUtil.showImage(target, "", R.drawable.banner_icon_default);
//		}
		target.setImageResource(postElement);
		bindEventAndShow(v, target);
		return v;
	}
}