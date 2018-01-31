package com.sonika.nepstra.pojo;

/**
 * Created by sonika on 10/17/2017.
 */

public class Kids_pojo {
    public String kidsname, kidsprice, kidsimage,kidsdesc;
    public Integer kidsid;
    public Integer kidscid, kidsi_id;

    public Kids_pojo() {
    }

    public String getKidsname() {
        return kidsname;
    }

    public void setKidsname(String kidsname) {
        this.kidsname = kidsname;
    }

    public String getKidsprice() {
        return kidsprice;
    }

    public void setKidsprice(String kidsprice) {
        this.kidsprice = kidsprice;
    }

    public String getKidsimage() {
        return kidsimage;
    }

    public void setKidsimage(String kidsimage) {
        this.kidsimage = kidsimage;
    }

    public String getKidsdesc() {
        return kidsdesc;
    }

    public void setKidsdesc(String kidsdesc) {
        this.kidsdesc = kidsdesc;
    }

    public Integer getKidsid() {
        return kidsid;
    }

    public void setKidsid(Integer kidsid) {
        this.kidsid = kidsid;
    }

    public Integer getKidscid() {
        return kidscid;
    }

    public void setKidscid(Integer kidscid) {
        this.kidscid = kidscid;
    }

    public Integer getKidsi_id() {
        return kidsi_id;
    }

    public void setKidsi_id(Integer kidsi_id) {
        this.kidsi_id = kidsi_id;
    }

    public Kids_pojo(String kidsname, String kidsprice, String kidsimage, String kidsdesc, Integer kidsid, Integer kidscid, Integer kidsi_id) {
        this.kidsname = kidsname;
        this.kidsprice = kidsprice;
        this.kidsimage = kidsimage;
        this.kidsdesc = kidsdesc;
        this.kidsid = kidsid;
        this.kidscid = kidscid;
        this.kidsi_id = kidsi_id;
    }
}
