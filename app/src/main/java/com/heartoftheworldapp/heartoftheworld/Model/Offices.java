package com.heartoftheworldapp.heartoftheworld.Model;

public class Offices {
    private  int id;
    private String officetype_ar;
    private String officetype_en;
    private String officearname;
    private String officeenname ;
    private String officenumber;

    public Offices(int id, String officetype_ar, String officetype_en, String officearname, String officeenname, String officenumber) {
        this.id = id;
        this.officetype_ar = officetype_ar;
        this.officetype_en = officetype_en;
        this.officearname = officearname;
        this.officeenname = officeenname;
        this.officenumber = officenumber;
    }

    public Offices() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOfficetype_ar() {
        return officetype_ar;
    }

    public void setOfficetype_ar(String officetype_ar) {
        this.officetype_ar = officetype_ar;
    }

    public String getOfficetype_en() {
        return officetype_en;
    }

    public void setOfficetype_en(String officetype_en) {
        this.officetype_en = officetype_en;
    }

    public String getOfficearname() {
        return officearname;
    }

    public void setOfficearname(String officearname) {
        this.officearname = officearname;
    }

    public String getOfficeenname() {
        return officeenname;
    }

    public void setOfficeenname(String officeenname) {
        this.officeenname = officeenname;
    }

    public String getOfficenumber() {
        return officenumber;
    }

    public void setOfficenumber(String officenumber) {
        this.officenumber = officenumber;
    }
}
