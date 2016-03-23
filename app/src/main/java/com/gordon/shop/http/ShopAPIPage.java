package com.gordon.shop.http;

import java.util.List;

/**
 * @author Gordon
 * @since 2016/3/18
 * do(分页数据)
 */
public class ShopAPIPage<T>{

    private List<T> data;
    private int totalPages;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
