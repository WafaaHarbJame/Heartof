package com.heartoftheworldapp.heartoftheworld.Model;

public class Resturants {
    private int id;
    private String Resturants_ar_name;
    private String Resturants_en_name;
    private String Resturants_ar_desc;
    private String Resturants_en__desc;
    private String Resturants_link;
    private String Resturants_phone;
    private String Resturants_ar_address;
    private String Resturants_en_address;
    private boolean is_favorite;
    private  String City_Name;

    public Resturants(int id, String resturants_ar_name, String resturants_en_name, String resturants_ar_desc, String resturants_en__desc, String resturants_link, String resturants_phone, String resturants_ar_address, String resturants_en_address, boolean is_favorite, String city_Name) {
        this.id = id;
        Resturants_ar_name = resturants_ar_name;
        Resturants_en_name = resturants_en_name;
        Resturants_ar_desc = resturants_ar_desc;
        Resturants_en__desc = resturants_en__desc;
        Resturants_link = resturants_link;
        Resturants_phone = resturants_phone;
        Resturants_ar_address = resturants_ar_address;
        Resturants_en_address = resturants_en_address;
        this.is_favorite = is_favorite;
        City_Name = city_Name;
    }

    public Resturants() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResturants_ar_name() {
        return Resturants_ar_name;
    }

    public void setResturants_ar_name(String resturants_ar_name) {
        Resturants_ar_name = resturants_ar_name;
    }

    public String getResturants_en_name() {
        return Resturants_en_name;
    }

    public void setResturants_en_name(String resturants_en_name) {
        Resturants_en_name = resturants_en_name;
    }

    public String getResturants_ar_desc() {
        return Resturants_ar_desc;
    }

    public void setResturants_ar_desc(String resturants_ar_desc) {
        Resturants_ar_desc = resturants_ar_desc;
    }

    public String getResturants_en__desc() {
        return Resturants_en__desc;
    }

    public void setResturants_en__desc(String resturants_en__desc) {
        Resturants_en__desc = resturants_en__desc;
    }

    public String getResturants_link() {
        return Resturants_link;
    }

    public void setResturants_link(String resturants_link) {
        Resturants_link = resturants_link;
    }

    public String getResturants_phone() {
        return Resturants_phone;
    }

    public void setResturants_phone(String resturants_phone) {
        Resturants_phone = resturants_phone;
    }

    public String getResturants_ar_address() {
        return Resturants_ar_address;
    }

    public void setResturants_ar_address(String resturants_ar_address) {
        Resturants_ar_address = resturants_ar_address;
    }

    public String getResturants_en_address() {
        return Resturants_en_address;
    }

    public void setResturants_en_address(String resturants_en_address) {
        Resturants_en_address = resturants_en_address;
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
