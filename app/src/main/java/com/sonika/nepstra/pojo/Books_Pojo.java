package com.sonika.nepstra.pojo;

/**
 * Created by sonika on 12/22/2017.
 */

public class Books_Pojo {
    public String bookname, bookprice, bookimage, bookdesc;
    public Integer bookid;
    public Integer bookcid, bookimgid;
    public Books_Pojo() {
    }

    public Books_Pojo(String bookname, String bookprice, String bookimage, String bookdesc, Integer bookid, Integer bookcid, Integer bookimgid) {
        this.bookname = bookname;
        this.bookprice = bookprice;
        this.bookimage = bookimage;
        this.bookdesc = bookdesc;
        this.bookid = bookid;
        this.bookcid = bookcid;
        this.bookimgid = bookimgid;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getBookprice() {
        return bookprice;
    }

    public void setBookprice(String bookprice) {
        this.bookprice = bookprice;
    }

    public String getBookimage() {
        return bookimage;
    }

    public void setBookimage(String bookimage) {
        this.bookimage = bookimage;
    }

    public String getBookdesc() {
        return bookdesc;
    }

    public void setBookdesc(String bookdesc) {
        this.bookdesc = bookdesc;
    }

    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }

    public Integer getBookcid() {
        return bookcid;
    }

    public void setBookcid(Integer bookcid) {
        this.bookcid = bookcid;
    }

    public Integer getBookimgid() {
        return bookimgid;
    }

    public void setBookimgid(Integer bookimgid) {
        this.bookimgid = bookimgid;
    }
}
