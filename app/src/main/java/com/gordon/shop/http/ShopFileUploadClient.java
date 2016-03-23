package com.gordon.shop.http;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Gordon
 * @since 2016/3/18
 * do(文件上传)
 */
public class ShopFileUploadClient {

    private Context context;
    private static ShopFileUploadClient mInstance;

    private ShopFileUploadClient(Context context) {
        this.context = context;
    }

    /**
     * 获取FileUploadClient实例
     *
     * @param context 上下文
     * @return
     */
    public static ShopFileUploadClient getClient(Context context) {
        if (mInstance == null) {
            synchronized (ShopAPIClient.class) {
                if (mInstance == null) {
                    mInstance = new ShopFileUploadClient(context);
                }
            }
        }
        return mInstance;
    }

    /**
     * 上传文件
     *
     * @param url        地址
     * @param filePath   文件路径（默认文件表单键file）
     * @param resultType 返回数据类型
     * @param handler    结果处理
     */
    public void upload(final String url, String filePath, final Type resultType, final ShopFileUploadBaseHandler handler) {
        Map<String, String> filePaths = new HashMap<>();
        filePaths.put("file", filePath);
        upload(url, filePaths, resultType, handler);
    }

    /**
     * 上传文件
     *
     * @param url        地址
     * @param fileParams 文件表单键、路径键值对
     * @param resultType 返回数据类型
     * @param handler    结果处理
     */
    public void upload(final String url, Map<String, String> fileParams, final Type resultType, final ShopFileUploadBaseHandler handler) {
        //异步的客户端对象
        AsyncHttpClient client = new AsyncHttpClient();
        client.removeAllHeaders();
        client.addHeader("enctype", "multipart/form-data");
        //封装文件上传的参数
        RequestParams params = new RequestParams();
        params.setForceMultipartEntityContentType(true);
        //根据路径创建文件
        try {
            //放入文件
            Set<String> keys = fileParams.keySet();
            for (String key : keys) {
                params.put(key, new File(fileParams.get(key)));
            }
            //执行post请求
            client.post(url, params, new AsyncHttpResponseHandler() {

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

}
