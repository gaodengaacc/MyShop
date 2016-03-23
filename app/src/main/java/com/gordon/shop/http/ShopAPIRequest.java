package com.gordon.shop.http;

import android.net.Uri;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
/**
 * @author Gordon
 * @since 2016/3/18
 * do()
 */
public class ShopAPIRequest<T> extends Request<T> {

    private Gson mGson = new Gson();
    private Response.Listener<T> mListener;
    private Class<T> mClazz;
    private Type resultType;
    private Map<String, String> mHeaders;
    private Object object;
    private int method;

    private static final String BOUNDRAY = "v5ZMguRAj3vzlzt5";

    public ShopAPIRequest(int method, String url, Object object, Type type, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.method = method;
        this.object = object;
        this.mListener = listener;
        this.resultType = type;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            Map<String, String> responseHeaders = response.headers;
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            if (json != null) {
//                L.d("lyun http url", getUrl());
//                L.d("lyun http response", json);
            }
            return Response.success((T) mGson.fromJson(json, resultType), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }

    @Override
    public String getUrl() {
        if (method == Method.GET) {
            Map<String, String> params = beanToMap(object);
            Set<String> paramNames = params.keySet();
            String url = "";
            for (String name : paramNames) {
                url += "&" + name + "=" + Uri.encode(params.get(name));
            }
//            L.e(super.getUrl() + url);
            return super.getUrl() + url;
        }
        return super.getUrl();
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> header = new HashMap<String, String>();
        //        header.put("version", AppApplication.getInstance().getAppVersion());
        header.put("platform", "android");
        header.put("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
        header.put("Content-Type", "multipart/form-data; boundary=----" + BOUNDRAY);
        header.put("Accept", "application/json,text/html,text/plain,image/*");
        // add Cookie or token here
        //        header.put("Cookie", AppApplication.getInstance().getCookie());
        return header;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return beanToMap(object);
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        if (method == Method.GET) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        sb.append("------" + BOUNDRAY);
        sb.append("\r\n");
        sb.append("Content-Disposition: form-data; name=\"data\"");
        sb.append("\r\n");
        sb.append("\r\n");
        sb.append(mGson.toJson(object));
        sb.append("\r\n");
        sb.append("------" + BOUNDRAY + "--");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            bos.write(sb.toString().getBytes("utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        L.e("http body","====getBody=====\n" + new String(bos.toByteArray()));
        return bos.toByteArray();
    }

    public Map<String, String> beanToMap(Object entity) {
        Map<String, String> parameter = new HashMap<String, String>();
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
            if (o != null) {
                parameter.put(fieldName, o.toString());
            }
        }
        return parameter;
    }
}