package com.team.air.bean;


//航班票余
public class Fleave {

    private Integer flight_id;
    private Integer seatType;
    private int leaveTickets;

    public Integer getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(Integer flight_id) {
        this.flight_id = flight_id;
    }

    public Integer getSeatType() {
        return seatType;
    }

    public void setSeatType(Integer seatType) {
        this.seatType = seatType;
    }

    public int getLeaveTickets() {
        return leaveTickets;
    }

    public void setLeaveTickets(int leaveTickets) {
        this.leaveTickets = leaveTickets;
    }
}
