package com.sonika.nepstra.pojo;

import java.io.Serializable;

/**
 * Created by sonika on 10/16/2017.
 */

public class Newarrivals_pojo implements Serializable {
    public String newname, newprice, newimage, newdesc;
    public Integer newid;
    public Integer newcid, img_id;

    public Newarrivals_pojo() {
    }

    public String getNewname() {
        return newname;
    }

    public void setNewname(String newname) {
        this.newname = newname;
    }

    public String getNewprice() {
        return newprice;
    }

    public void setNewprice(String newprice) {
        this.newprice = newprice;
    }

    public String getNewimage() {
        return newimage;
    }

    public void setNewimage(String newimage) {
        this.newimage = newimage;
    }

    public String getNewdesc() {
        return newdesc;
    }

    public void setNewdesc(String newdesc) {
        this.newdesc = newdesc;
    }

    public Integer getNewid() {
        return newid;
    }

    public void setNewid(Integer newid) {
        this.newid = newid;
    }

    public Integer getNewcid() {
        return newcid;
    }

    public void setNewcid(Integer newcid) {
        this.newcid = newcid;
    }

    public Integer getImg_id() {
        return img_id;
    }

    public void setImg_id(Integer img_id) {
        this.img_id = img_id;
    }

    public Newarrivals_pojo(String newname, String newprice, String newimage, String newdesc, Integer newid, Integer newcid, Integer img_id) {
        this.newname = newname;
        this.newprice = newprice;
        this.newimage = newimage;
        this.newdesc = newdesc;
        this.newid = newid;
        this.newcid = newcid;
        this.img_id = img_id;
    }
}