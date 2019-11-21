package com.team.air.bean;

public class Passengers {

    private Integer passenger_id;
    private Integer flight_id;
    private Integer user_id;
    private String name;
    private String ID;
    private Integer sex;
    private Integer status;

    @Override
    public String toString() {
        return "Passengers{" +
                "passer_id=" + passenger_id +
                ", flight_id=" + flight_id +
                ", user_id=" + user_id +
                ", name='" + name + '\'' +
                ", ID='" + ID + '\'' +
                ", sex=" + sex +
                '}';
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPassenger_id() {
        return passenger_id;
    }

    public void setPassenger_id(Integer passenger_id) {
        this.passenger_id = passenger_id;
    }

    public Integer getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(Integer flight_id) {
        this.flight_id = flight_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }
}
