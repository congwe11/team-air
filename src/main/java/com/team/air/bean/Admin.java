package com.team.air.bean;

public class Admin {

    private String ad_username;
    private String ad_psw;
    private String identity;

    @Override
    public String toString() {
        return "Admin{" +
                "ad_username='" + ad_username + '\'' +
                ", ad_psw='" + ad_psw + '\'' +
                ", identity='" + identity + '\'' +
                '}';
    }

    public String getAd_username() {
        return ad_username;
    }

    public void setAd_username(String ad_username) {
        this.ad_username = ad_username;
    }

    public String getAd_psw() {
        return ad_psw;
    }

    public void setAd_psw(String ad_psw) {
        this.ad_psw = ad_psw;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }
}
