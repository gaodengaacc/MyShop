package com.gordon.shop.http;

import java.io.Serializable;

/**
 * @author Gordon
 * @since 2016/3/18
 * do()
 */
public class ShopAPIResult<T> implements Serializable {

    private int status;
    private String describe;
    private T content;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
