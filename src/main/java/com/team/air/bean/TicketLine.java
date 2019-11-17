package com.team.air.bean;

import java.util.Date;

public class TicketLine {

    private Integer ticket_id;
    private Integer passer_id;
    private Integer seatNo;
    private Date start_time;
    private Date end_time;
    private String start_city;
    private String terminus_city;
    private Double firstPrice;
    private Integer orderNum;
    private String sType;
    private String seatNum;

    public Integer getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(Integer ticket_id) {
        this.ticket_id = ticket_id;
    }

    public Integer getPasser_id() {
        return passer_id;
    }

    public void setPasser_id(Integer passer_id) {
        this.passer_id = passer_id;
    }

    public Integer getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(Integer seatNo) {
        this.seatNo = seatNo;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public String getStart_city() {
        return start_city;
    }

    public void setStart_city(String start_city) {
        this.start_city = start_city;
    }

    public String getTerminus_city() {
        return terminus_city;
    }

    public void setTerminus_city(String terminus_city) {
        this.terminus_city = terminus_city;
    }

    public Double getFirstPrice() {
        return firstPrice;
    }

    public void setFirstPrice(Double firstPrice) {
        this.firstPrice = firstPrice;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getsType() {
        return sType;
    }

    public void setsType(String sType) {
        this.sType = sType;
    }

    public String getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(String seatNum) {
        this.seatNum = seatNum;
    }
}
