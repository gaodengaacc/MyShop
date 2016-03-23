package com.gordon.shop.http;

import android.os.Bundle;
import android.os.Message;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * @author Gordon
 * @since 2016/3/18
 * do()
 */
public class ShopAPIErrorListener implements Response.ErrorListener {

    private String url;
    private ShopAPIResponseBaseHandler handler;

    public ShopAPIErrorListener(String url, ShopAPIResponseBaseHandler handler) {
        this.url = url;
        this.handler = handler;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        error.printStackTrace();
        Message msg = handler.obtainMessage();
        msg.what = 1;
        Bundle b = new Bundle();
        b.putString("result-key", ShopAPIResultQueue.putResult(url, error));
        msg.setData(b);
        handler.sendMessage(msg);
    }

}
