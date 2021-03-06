package com.xunfang.pojo;

public class OrderInfo {
//    采用int的封装类： 防止前端参数 为 null的情况
    private Integer id;
    private int uid;
    private UserInfo ui;
    private String status;
    private String ordertime;
    private double orderprice;

    @Override
    public String toString() {
        return "OrderInfo{" +
                "id=" + id +
                ", uid=" + uid +
                ", ui=" + ui +
                ", status='" + status + '\'' +
                ", ordertime='" + ordertime + '\'' +
                ", orderprice=" + orderprice +
                ", orderTimeFrom='" + orderTimeFrom + '\'' +
                ", orderTimeTo='" + orderTimeTo + '\'' +
                '}';
    }

    private String orderTimeFrom;
    private String orderTimeTo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public UserInfo getUi() {
        return ui;
    }

    public void setUi(UserInfo ui) {
        this.ui = ui;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime;
    }

    public double getOrderprice() {
        return orderprice;
    }

    public void setOrderprice(double orderprice) {
        this.orderprice = orderprice;
    }

    public String getOrderTimeFrom() {
        return orderTimeFrom;
    }

    public void setOrderTimeFrom(String orderTimeFrom) {
        this.orderTimeFrom = orderTimeFrom;
    }

    public String getOrderTimeTo() {
        return orderTimeTo;
    }

    public void setOrderTimeTo(String orderTimeTo) {
        this.orderTimeTo = orderTimeTo;
    }
}
