package com.team.air.bean;

public class AirLine {

    private Integer plane_id;
    private Integer flight_id;

    @Override
    public String toString() {
        return "AirLine{" +
                "plane_id=" + plane_id +
                ", flight_id=" + flight_id +
                '}';
    }

    public Integer getPlane_id() {
        return plane_id;
    }

    public void setPlane_id(Integer plane_id) {
        this.plane_id = plane_id;
    }

    public Integer getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(Integer flight_id) {
        this.flight_id = flight_id;
    }
}
