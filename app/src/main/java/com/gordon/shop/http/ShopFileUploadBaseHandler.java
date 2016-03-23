package com.gordon.shop.http;

import com.android.volley.VolleyError;
/**
 * @author Gordon
 * @since 2016/3/18
 * do()
 */
public abstract class ShopFileUploadBaseHandler extends ShopAPIResponseBaseHandler<ShopAPIResult> {

    public static final int START = 1;
    public static final int FINISH = 2;
    public static final int ERROR = 3;
    public static final int PROGRESS = 4;

    public abstract void onStart();

    public abstract void onProgress(long transferedBytes, long totalBytes);

    public abstract void onSuccess(ShopAPIResult result);

    public abstract void onError(VolleyError error);
}
