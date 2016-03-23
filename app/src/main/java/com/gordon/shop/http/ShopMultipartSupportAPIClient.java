package com.gordon.shop.http;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Gordon
 * @since 2016/3/18
 * do()
 */
public class ShopMultipartSupportAPIClient {

    private static ShopMultipartSupportAPIClient mInstance;
    private Context context;
    private AsyncHttpClient client;

    private ShopMultipartSupportAPIClient(Context context) {
        this.context = context;
        initClient();
    }

    /**
     * 获取APIClient实例
     *
     * @param context 上下文
     * @return
     */
    public static ShopMultipartSupportAPIClient getClient(Context context) {
        if (mInstance == null) {
            synchronized (ShopMultipartSupportAPIClient.class) {
                if (mInstance == null) {
                    mInstance = new ShopMultipartSupportAPIClient(context);
                }
            }
        }
        return mInstance;
    }

    private void initClient() {
        //异步的客户端对象
        client = new AsyncHttpClient();
        client.removeAllHeaders();
        client.addHeader("enctype", "multipart/form-data");
    }

    /**
     * 发送不带有tag的post网络请求
     *
     * @param url     请求url
     * @param obj     发送的消息实体
     * @param handler 网络请求处理者
     */
    public void post(final String url, Object obj, final ShopFileUploadBaseHandler handler) {
        post(url, obj, new TypeToken<ShopAPIResult<String>>() {}.getType(), handler);
    }

    /**
     * 发送不带有tag的post网络请求
     *
     * @param url        请求url
     * @param obj        发送的消息实体
     * @param resultType 返回数据type
     * @param handler    网络请求处理者
     */
    public void post(final String url, Object obj, final Type resultType, final ShopFileUploadBaseHandler handler) {
        //执行post请求
        try {
            client.post(url, getParams(obj), new AsyncHttpResponseHandler() {

                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    if (statusCode == 200) {
                        try {
                            solveResult(url, handler, new String(responseBody), resultType);
                        } catch (JsonSyntaxException e) {
                            solveError(url, handler, new VolleyError(e));
                        }
                    } else {
                        Map<String, String> vheaders = new HashMap<String, String>();
                        for (Header header : headers) {
                            vheaders.put(header.getName(), header.getValue());
                        }
                        solveError(url, handler, new VolleyError(new NetworkResponse(statusCode, responseBody, vheaders, false)));
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    error.printStackTrace();
                    solveError(url, handler, new VolleyError(error));
                }

                @Override
                public void onStart() {
                    super.onStart();
                    handler.sendEmptyMessage(ShopFileUploadBaseHandler.START);
                }

                @Override
                public void onProgress(long bytesWritten, long totalSize) {
                    super.onProgress(bytesWritten, totalSize);
                    solveProgress(url, handler, bytesWritten, totalSize);
                }
            });
        } catch (FileNotFoundException e) {
            solveError(url, handler, new VolleyError(e));
            //        }catch (UnsupportedEncodingException e){
            //            solveError(url, handler, new VolleyError(e));
        }
    }

    private void solveResult(String url, ShopFileUploadBaseHandler handler, String result, Type resultType) {
        ShopAPIResult response = new Gson().fromJson(result, resultType);
        Message msg = handler.obtainMessage();
        msg.what = ShopFileUploadBaseHandler.FINISH;
        Bundle b = new Bundle();
        b.putString("result-key", ShopAPIResultQueue.putResult(url, response));
        msg.setData(b);
        handler.sendMessage(msg);
    }

    private void solveProgress(String url, ShopFileUploadBaseHandler handler, long bytesWritten, long totalSize) {
        Message msg = handler.obtainMessage();
        msg.what = ShopFileUploadBaseHandler.PROGRESS;
        Bundle b = new Bundle();
        b.putLong("transferedBytes", bytesWritten);
        b.putLong("totalBytes", totalSize);
        msg.setData(b);
        handler.sendMessage(msg);
    }

    private void solveError(String url, ShopFileUploadBaseHandler handler, VolleyError e) {
        Message msg = handler.obtainMessage();
        msg.what = ShopFileUploadBaseHandler.ERROR;
        Bundle b = new Bundle();
        b.putString("result-key", ShopAPIResultQueue.putResult(url, new VolleyError(e)));
        msg.setData(b);
        handler.sendMessage(msg);
    }

    public RequestParams getParams(Object entity) throws FileNotFoundException {
        RequestParams params = new RequestParams();
        params.setForceMultipartEntityContentType(true);
        Field[] fields = entity.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            String fieldName = fields[i].getName();
            Object o = null;
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getMethodName = "get" + firstLetter + fieldName.substring(1);
            java.lang.reflect.Method getMethod;
            try {
                getMethod = entity.getClass().getMethod(getMethodName, new Class[]{});
                o = getMethod.invoke(entity, new Object[]{});
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (o != null && o instanceof File) {
                params.put(fieldName, (File) o);
            }
        }
        params.put("data", new Gson().toJson(entity));
        return params;
    }

}
