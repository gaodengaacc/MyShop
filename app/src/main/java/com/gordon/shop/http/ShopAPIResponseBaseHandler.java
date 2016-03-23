package com.gordon.shop.http;

import android.os.Handler;
import android.os.Looper;

import com.android.volley.VolleyError;

/**
 * @author Gordon
 * @since 2016/3/18
 * do(请求结果处理)
 */
public abstract class ShopAPIResponseBaseHandler<T> extends Handler {

    public ShopAPIResponseBaseHandler(){
        super();
    }

    public ShopAPIResponseBaseHandler(Looper looper){
        super(looper);
    }

    protected abstract void onSuccess(T result);

    protected abstract void onError(VolleyError error);

}
