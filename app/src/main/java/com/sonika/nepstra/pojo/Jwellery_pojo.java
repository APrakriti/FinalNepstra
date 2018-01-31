package com.sonika.nepstra.pojo;

/**
 * Created by sonika on 10/17/2017.
 */

public class Jwellery_pojo {
    public String jwelleryname, jwelleryprice, jwelleryimage, jwellerydesc;
    public Integer jwelleryid;
    public Integer jwellerycid, jwelleryi_id;

    public Jwellery_pojo() {
    }

    public String getJwelleryname() {
        return jwelleryname;
    }

    public void setJwelleryname(String jwelleryname) {
        this.jwelleryname = jwelleryname;
    }

    public String getJwelleryprice() {
        return jwelleryprice;
    }

    public void setJwelleryprice(String jwelleryprice) {
        this.jwelleryprice = jwelleryprice;
    }

    public String getJwelleryimage() {
        return jwelleryimage;
    }

    public void setJwelleryimage(String jwelleryimage) {
        this.jwelleryimage = jwelleryimage;
    }

    public String getJwellerydesc() {
        return jwellerydesc;
    }

    public void setJwellerydesc(String jwellerydesc) {
        this.jwellerydesc = jwellerydesc;
    }

    public Integer getJwelleryid() {
        return jwelleryid;
    }

    public void setJwelleryid(Integer jwelleryid) {
        this.jwelleryid = jwelleryid;
    }

    public Integer getJwellerycid() {
        return jwellerycid;
    }

    public void setJwellerycid(Integer jwellerycid) {
        this.jwellerycid = jwellerycid;
    }

    public Integer getJwelleryi_id() {
        return jwelleryi_id;
    }

    public void setJwelleryi_id(Integer jwelleryi_id) {
        this.jwelleryi_id = jwelleryi_id;
    }

    public Jwellery_pojo(String jwelleryname, String jwelleryprice, String jwelleryimage, String jwellerydesc, Integer jwelleryid, Integer jwellerycid, Integer jwelleryi_id) {
        this.jwelleryname = jwelleryname;
        this.jwelleryprice = jwelleryprice;
        this.jwelleryimage = jwelleryimage;
        this.jwellerydesc = jwellerydesc;
        this.jwelleryid = jwelleryid;
        this.jwellerycid = jwellerycid;
        this.jwelleryi_id = jwelleryi_id;
    }
}