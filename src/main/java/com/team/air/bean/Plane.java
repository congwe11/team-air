package com.team.air.bean;

public class Plane {
    private Integer plane_id;
    private String planeName;
    private Integer status;

    @Override
    public String toString() {
        return "Plane{" +
                "plane_id=" + plane_id +
                ", planeName='" + planeName + '\'' +
                ", status=" + status +
                '}';
    }

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
}
