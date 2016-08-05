package com.gordon.shop;

import android.app.Application;
import android.content.Intent;
import android.media.projection.MediaProjectionManager;

import com.bumptech.glide.Glide;

/**
 * @author Gordon
 * @since 2016/3/21
 * do()
 */
public class ShopApplication extends Application {

    public Intent getResultIntent() {
        return mResultIntent;
    }

    public void setResultIntent(Intent mResultIntent) {
        this.mResultIntent = mResultIntent;
    }

    public int getResultCode() {
        return mResultCode;
    }

    public void setResultCode(int mResultCode) {
        this.mResultCode = mResultCode;
    }

    private Intent mResultIntent = null;
    private int mResultCode = 0;

    public MediaProjectionManager getMpmngr() {
        return mMpmngr;
    }

    public void setMpmngr(MediaProjectionManager mMpmngr) {
        this.mMpmngr = mMpmngr;
    }

    private MediaProjectionManager mMpmngr;
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
