package com.gordon.shop.http;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * @author Gordon
 * @since 2016/3/18
 * do()
 */
public class ShopAPIClient {

    private static ShopAPIClient mInstance;
    private RequestQueue mRequestQueue;

    private ShopAPIClient(Context context) {
        mRequestQueue = Volley.newRequestQueue(context);
    }

    /**
     * 获取APIClient实例
     *
     * @param context 上下文
     * @return
     */
    public static ShopAPIClient getClient(Context context) {
        if (mInstance == null) {
            synchronized (ShopAPIClient.class) {
                if (mInstance == null) {
                    mInstance = new ShopAPIClient(context);
                }
            }
        }
        return mInstance;
    }

    /**
     * 发送不带有tag的post网络请求
     *
     * @param url     请求url
     * @param obj     发送的消息实体
     * @param handler 网络请求处理者
     */
    public void post(final String url, Object obj, final ShopAPIResponseBaseHandler handler) {
        post(url, obj, new TypeToken<ShopAPIResult<String>>() {}.getType(), handler, null);
    }

    /**
     * 发送不带有tag的post网络请求
     *
     * @param url        请求url
     * @param obj        发送的消息实体
     * @param resultType 返回数据type
     * @param handler    网络请求处理者
     */
    public void post(final String url, Object obj, Type resultType, final ShopAPIResponseBaseHandler handler) {
        post(url, obj, resultType, handler, null);
    }

    /**
     * 发送带有tag的post网络请求
     *
     * @param url     请求url
     * @param obj     发送的消息实体
     * @param handler 网络请求处理者
     * @param tag     该请求的tag
     */
    public void post(final String url, Object obj, final ShopAPIResponseBaseHandler handler, Object tag) {
        post(url, obj, new TypeToken<ShopAPIResult<String>>() {}.getType(), handler, tag);
    }

    /**
     * 发送带有tag的post网络请求
     *
     * @param url        请求url
     * @param obj        发送的消息实体
     * @param resultType 返回数据类型
     * @param handler    网络请求处理者
     * @param tag        该请求的tag
     */
    public void post(final String url, Object obj, Type resultType, final ShopAPIResponseBaseHandler handler, Object tag) {
        post(url, obj, resultType, handler, tag, false);
    }

    /**
     * 发送带有tag的post网络请求
     *
     * @param url         请求url
     * @param obj         发送的消息实体
     * @param resultType  返回数据类型
     * @param handler     网络请求处理者
     * @param tag         该请求的tag
     * @param shouldCache 是否缓存
     */
    public void post(final String url, Object obj, Type resultType, final ShopAPIResponseBaseHandler handler, Object tag, boolean shouldCache) {
        ShopAPIRequest request = new ShopAPIRequest(Request.Method.POST, url, obj, resultType, new ShopAPIResponseListener(url, handler), new ShopAPIErrorListener(url, handler));
        request.setTag(tag);
        request.setShouldCache(shouldCache);
        request.setRetryPolicy(new DefaultRetryPolicy(10 * 1000, 1, 1.0f));
        mRequestQueue.add(request);
    }

    /**
     * 发送不带有tag的get网络请求
     *
     * @param url        请求url
     * @param obj        发送的消息实体
     * @param resultType 返回数据类型
     * @param handler    网络请求处理者
     */
    public void get(final String url, Object obj, Type resultType, final ShopAPIResponseBaseHandler handler) {
        get(url, obj, resultType, handler, null);
    }

    /**
     * 发送不带有tag的get网络请求
     *
     * @param url     请求url
     * @param obj     发送的消息实体
     * @param handler 网络请求处理者
     */
    public void get(final String url, Object obj, final ShopAPIResponseBaseHandler handler) {
        get(url, obj, new TypeToken<ShopAPIResult<String>>() {}.getType(), handler, null);
    }

    /**
     * 发送带有tag的get网络请求
     *
     * @param url        请求url
     * @param obj        发送的消息实体
     * @param resultType 返回的数据type
     * @param handler    网络请求处理者
     * @param tag        该请求的tag
     */
    public void get(final String url, Object obj, Type resultType, final ShopAPIResponseBaseHandler handler, Object tag) {
        get(url, obj, resultType, handler, tag, false);
    }

    /**
     * 发送带有tag的get网络请求
     *
     * @param url         请求url
     * @param obj         发送的消息实体
     * @param resultType  返回的数据type
     * @param handler     网络请求处理者
     * @param tag         该请求的tag
     * @param shouldCache 是否缓存数据
     */
    public void get(final String url, Object obj, Type resultType, final ShopAPIResponseBaseHandler handler, Object tag, boolean shouldCache) {
        ShopAPIRequest<ShopAPIResult> request = new ShopAPIRequest(Request.Method.GET, url, obj, resultType, new ShopAPIResponseListener(url, handler), new ShopAPIErrorListener(url, handler));
        request.setTag(tag);
        request.setShouldCache(shouldCache);
        mRequestQueue.add(request);
    }

    /**
     * 发送带有tag的get网络请求
     *
     * @param url     请求url
     * @param obj     发送的消息实体
     * @param handler 网络请求处理者
     * @param tag     该请求的tag
     */
    public void get(final String url, Object obj, final ShopAPIResponseBaseHandler handler, Object tag) {
        get(url, obj, new TypeToken<ShopAPIResult<String>>() {}.getType(), handler, tag);
    }

    /**
     * 取消指定tag的网络请求
     *
     * @param tag
     */
    public void cancle(Object tag) {
        mRequestQueue.cancelAll(tag);
    }

}
