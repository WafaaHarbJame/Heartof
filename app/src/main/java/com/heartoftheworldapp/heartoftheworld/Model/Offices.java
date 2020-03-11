package com.heartoftheworldapp.heartoftheworld.Model;

public class Offices {
    private  int id;
    private String officetype_ar;
    private String officetype_en;
    private String officearname;
    private String officeenname ;
    private String officenumber;
    private boolean is_favorite;
    private String City_Name;

    public Offices(int id, String officetype_ar, String officetype_en, String officearname, String officeenname, String officenumber, boolean is_favorite, String city_Name) {
        this.id = id;
        this.officetype_ar = officetype_ar;
        this.officetype_en = officetype_en;
        this.officearname = officearname;
        this.officeenname = officeenname;
        this.officenumber = officenumber;
        this.is_favorite = is_favorite;
        City_Name = city_Name;
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

    public boolean isIs_favorite() {
        return is_favorite;
    }

    public void setIs_favorite(boolean is_favorite) {
        this.is_favorite = is_favorite;
    }

    public String getCity_Name() {
        return City_Name;
    }

    public void setCity_Name(String city_Name) {
        City_Name = city_Name;
    }
}
