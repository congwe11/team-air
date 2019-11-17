package com.team.air.bean;


//航班票余
public class Fleave {

    private Flight flight_id;
    private SeatType seatType;
    private int leaveTickets;

    public Flight getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(Flight flight_id) {
        this.flight_id = flight_id;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }

    public int getLeaveTickets() {
        return leaveTickets;
    }

    public void setLeaveTickets(int leaveTickets) {
        this.leaveTickets = leaveTickets;
    }
}
