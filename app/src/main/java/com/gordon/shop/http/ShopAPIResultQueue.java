package com.gordon.shop.http;

import java.util.concurrent.ConcurrentHashMap;
/**
 * @author Gordon
 * @since 2016/3/18
 * do()
 */
public class ShopAPIResultQueue {

    private static ConcurrentHashMap<String, Object> resultMap = new ConcurrentHashMap<>();

    public static String putResult(String url, Object obj) {
        String key = System.currentTimeMillis() + ":" + url;
        resultMap.put(key, obj);
        return key;
    }

    public static Object getResult(String key) {
        Object obj = resultMap.get(key);
        resultMap.remove(key);
        return obj;
    }
}
