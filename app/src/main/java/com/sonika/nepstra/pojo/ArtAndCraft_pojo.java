package com.sonika.nepstra.pojo;

/**
 * Created by sonika on 10/17/2017.
 */

public class ArtAndCraft_pojo {
    public String artname, artprice, artimage, artdesc;
    public Integer artid;
    public Integer artcid, artimgid;

    public ArtAndCraft_pojo() {
    }

    public String getArtname() {
        return artname;
    }

    public void setArtname(String artname) {
        this.artname = artname;
    }

    public String getArtprice() {
        return artprice;
    }

    public void setArtprice(String artprice) {
        this.artprice = artprice;
    }

    public String getArtimage() {
        return artimage;
    }

    public void setArtimage(String artimage) {
        this.artimage = artimage;
    }

    public String getArtdesc() {
        return artdesc;
    }

    public void setArtdesc(String artdesc) {
        this.artdesc = artdesc;
    }

    public Integer getArtid() {
        return artid;
    }

    public void setArtid(Integer artid) {
        this.artid = artid;
    }

    public Integer getArtcid() {
        return artcid;
    }

    public void setArtcid(Integer artcid) {
        this.artcid = artcid;
    }

    public Integer getArtimgid() {
        return artimgid;
    }

    public void setArtimgid(Integer artimgid) {
        this.artimgid = artimgid;
    }

    public ArtAndCraft_pojo(String artname, String artprice, String artimage, String artdesc, Integer artid, Integer artcid, Integer artimgid) {
        this.artname = artname;
        this.artprice = artprice;
        this.artimage = artimage;
        this.artdesc = artdesc;
        this.artid = artid;
        this.artcid = artcid;
        this.artimgid = artimgid;
    }
}