package com.gordon.shop.http;

import android.os.Bundle;
import android.os.Message;

import com.android.volley.Response;

/**
 * @author Gordon
 * @since 2016/3/18
 * do()
 */
public class ShopAPIResponseListener<T> implements Response.Listener<T>{

    private String url;
    private ShopAPIResponseBaseHandler handler;

    public ShopAPIResponseListener(String url, ShopAPIResponseBaseHandler handler) {
        this.url = url;
        this.handler = handler;
    }

    @Override
    public void onResponse(T response) {
        Message msg = handler.obtainMessage();
        msg.what = 0;
        Bundle b = new Bundle();
        b.putString("result-key", ShopAPIResultQueue.putResult(url, response));
        msg.setData(b);
        handler.sendMessage(msg);
    }
}