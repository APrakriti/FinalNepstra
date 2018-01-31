package com.sonika.nepstra.pojo;

/**
 * Created by sonika on 10/17/2017.
 */

public class Mens_pojo {
    public String menname, menprice, menimage, menDesc;
    public Integer menid;
    public Integer mencid, men_i_id;

    public Mens_pojo() {
    }

    public String getMenname() {
        return menname;
    }

    public void setMenname(String menname) {
        this.menname = menname;
    }

    public String getMenprice() {
        return menprice;
    }

    public void setMenprice(String menprice) {
        this.menprice = menprice;
    }

    public String getMenimage() {
        return menimage;
    }

    public void setMenimage(String menimage) {
        this.menimage = menimage;
    }

    public String getMenDesc() {
        return menDesc;
    }

    public void setMenDesc(String menDesc) {
        this.menDesc = menDesc;
    }

    public Integer getMenid() {
        return menid;
    }

    public void setMenid(Integer menid) {
        this.menid = menid;
    }

    public Integer getMencid() {
        return mencid;
    }

    public void setMencid(Integer mencid) {
        this.mencid = mencid;
    }

    public Integer getMen_i_id() {
        return men_i_id;
    }

    public void setMen_i_id(Integer men_i_id) {
        this.men_i_id = men_i_id;
    }

    public Mens_pojo(String menname, String menprice, String menimage, String menDesc, Integer menid, Integer mencid, Integer men_i_id) {
        this.menname = menname;
        this.menprice = menprice;
        this.menimage = menimage;
        this.menDesc = menDesc;
        this.menid = menid;
        this.mencid = mencid;
        this.men_i_id = men_i_id;
    }
}