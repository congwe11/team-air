package com.team.air.bean;

import java.util.List;

public class PlanePlus {
    private Integer plane_id;
    private String planeName;
    private Integer status;
    private List<SeatsInfo> seat;


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPlane_id() {
        return plane_id;
    }

    public void setPlane_id(Integer plane_id) {
        this.plane_id = plane_id;
    }

    public String getPlaneName() {
        return planeName;
    }

    public void setPlaneName(String planeName) {
        this.planeName = planeName;
    }

    public List<SeatsInfo> getSeat() {
        return seat;
    }

    public void setSeat(List<SeatsInfo> seat) {
        this.seat = seat;
    }
}
