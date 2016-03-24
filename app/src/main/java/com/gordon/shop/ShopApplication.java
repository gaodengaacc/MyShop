package com.gordon.shop;

import android.app.Application;

import com.bumptech.glide.Glide;

/**
 * @author Gordon
 * @since 2016/3/21
 * do()
 */
public class ShopApplication extends Application {
    private static ShopApplication instance;

    public static ShopApplication getInstance() {
        return instance;
    }
    public void onLoginTimeOut() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
