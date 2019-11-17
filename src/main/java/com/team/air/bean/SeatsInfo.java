package com.team.air.bean;

public class SeatsInfo {

    private Plane plane_id;
    private SeatType seatType_id;
    private Integer seatCount;
    private double seatPrice;

    public Plane getPlane_id() {
        return plane_id;
    }

    public void setPlane_id(Plane plane_id) {
        this.plane_id = plane_id;
    }

    public SeatType getSeatType_id() {
        return seatType_id;
    }

    public void setSeatType_id(SeatType seatType_id) {
        this.seatType_id = seatType_id;
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
