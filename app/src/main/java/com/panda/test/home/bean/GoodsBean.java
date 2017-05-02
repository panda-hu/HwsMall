package com.panda.test.home.bean;

import java.io.Serializable;

/**
 * author:huweshuai
 * time:2017/5/2
 */

public class GoodsBean implements Serializable{
    private String name;
    private String cover_price;
    private String figure;
    private String product_id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCover_price() {
        return cover_price;
    }

    public void setCover_price(String cover_price) {
        this.cover_price = cover_price;
    }

    public String getFigure() {
        return figure;
    }

    public void setFigure(String figure) {
        this.figure = figure;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public GoodsBean() {
    }

    public GoodsBean(String name, String cover_price, String figure, String product_id) {
        this.name = name;
        this.cover_price = cover_price;
        this.figure = figure;
        this.product_id = product_id;
    }
}
