package com.gordon.shop.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.gordon.shop.R;

/**
 * @author Gordon
 * @since 2016/3/24
 * do()
 */
public class ImageUtils {
    /**
     * 加载图片
     *
     * @param view
     * @param resid
     * @param defaultResid
     */
    public static void showImage(Context context,ImageView view, String resid, int defaultResid) {
        Glide.with(context)
                .load(resid)
                .placeholder(defaultResid)
                .animate(R.anim.fade_in)
                .crossFade()
                .into(view);
    }
    /**
     * 加载图片
     *
     * @param view
     * @param resid
     */
    public static void showImage(Context context,ImageView view, String resid) {
        Glide.with(context)
                .load(resid)
                .animate(R.anim.fade_in)
                .crossFade()
                .into(view);
    }
    /**
     * 加载图片
     *
     * @param view
     * @param resid
     * @param defaultResid
     */
    public static void showMediumImage(ImageView view, String resid, int defaultResid) {

    }

    /**
     * 加载图片
     *
     * @param view
     * @param resid
     * @param defaultResid
     */
    public static void showBigImage(ImageView view, String resid, int defaultResid) {

    }


    /**
     * 加载图片
     *
     * @param view
     * @param resid
     * @param defaultResid
     */
    public static boolean showImageFromCache(ImageView view, String resid, int defaultResid) {
       return true;
    }


    /**
     * 加载图片
     *
     * @param view
     * @param resid
     * @param defaultResid
     */
    public static boolean showMediumImageFromCache(ImageView view, String resid, int defaultResid) {
        return true;
    }

    /**
     * 加载图片
     *
     * @param view
     * @param resid
     * @param defaultResid
     */
    public static boolean showBigImageFromCache(ImageView view, String resid, int defaultResid) {
        return true;
    }

}
