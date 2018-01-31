package com.sonika.nepstra.pojo;

/**
 * Created by sonika on 9/23/2017.
 */

public class OrderedProducts_pojo {
    public String orderedname, orderedprice, orderedimage,orderedcat_id;
    public Integer orderid;
    public Integer count;

    public OrderedProducts_pojo() {
    }

    public String getOrderedname() {
        return orderedname;
    }

    public void setOrderedname(String orderedname) {
        this.orderedname = orderedname;
    }

    public String getOrderedprice() {
        return orderedprice;
    }

    public void setOrderedprice(String orderedprice) {
        this.orderedprice = orderedprice;
    }

    public String getOrderedimage() {
        return orderedimage;
    }

    public void setOrderedimage(String orderedimage) {
        this.orderedimage = orderedimage;
    }

    public String getOrderedcat_id() {
        return orderedcat_id;
    }

    public void setOrderedcat_id(String orderedcat_id) {
        this.orderedcat_id = orderedcat_id;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public OrderedProducts_pojo(String orderedname, String orderedprice, String orderedimage, String orderedcat_id, Integer orderid, Integer count) {
        this.orderedname = orderedname;
        this.orderedprice = orderedprice;
        this.orderedimage = orderedimage;
        this.orderedcat_id = orderedcat_id;
        this.orderid = orderid;
        this.count = count;
    }
}