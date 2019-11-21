package com.team.air.bean;


public class OrderLine {

    private Integer order_id;
    private Integer user_id;
    private String time;
    private double all_price;
    private Integer status;

    @Override
    public String toString() {
        return "OrderLine{" +
                "order_id=" + order_id +
                ", user_id=" + user_id +
                ", time='" + time + '\'' +
                ", all_price=" + all_price +
                ", status=" + status +
                '}';
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getAll_price() {
        return all_price;
    }

    public void setAll_price(double all_price) {
        this.all_price = all_price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
