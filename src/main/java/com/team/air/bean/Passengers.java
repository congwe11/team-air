package com.team.air.bean;

public class Passengers {

    private Integer passer_id;
    private Integer flight_id;
    private Integer user_id;
    private String name;
    private String ID;
    private Integer sex;

    public Integer getPasser_id() {
        return passer_id;
    }

    public void setPasser_id(Integer passer_id) {
        this.passer_id = passer_id;
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
