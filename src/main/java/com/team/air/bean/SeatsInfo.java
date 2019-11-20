package com.team.air.bean;

public class SeatsInfo {

    private Integer plane_id;
    private Integer seatType;
    private Integer seatCount;
    private double seatPrice;

    @Override
    public String toString() {
        return "SeatsInfo{" +
                "plane_id=" + plane_id +
                ", seatType=" + seatType +
                ", seatCount=" + seatCount +
                ", seatPrice=" + seatPrice +
                '}';
    }

    public Integer getPlane_id() {
        return plane_id;
    }

    public void setPlane_id(Integer plane_id) {
        this.plane_id = plane_id;
    }

    public Integer getSeatType() {
        return seatType;
    }

    public void setSeatType(Integer seatType) {
        this.seatType = seatType;
    }

    public Integer getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(Integer seatCount) {
        this.seatCount = seatCount;
    }

    public double getSeatPrice() {
        return seatPrice;
    }

    public void setSeatPrice(double seatPrice) {
        this.seatPrice = seatPrice;
    }
}
