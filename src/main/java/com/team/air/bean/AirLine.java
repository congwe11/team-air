package com.team.air.bean;

public class AirLine {

    private Plane plane_id;
    private Flight flight_id;

    public Plane getPlane_id() {
        return plane_id;
    }

    public void setPlane_id(Plane plane_id) {
        this.plane_id = plane_id;
    }

    public Flight getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(Flight flight_id) {
        this.flight_id = flight_id;
    }
}
