package com.heartoftheworldapp.heartoftheworld.Model;

public class Alsoaq {
    private int id;
    private String soaq_ar_name;
    private String soaq_en_name;
    private String soaq__ar_desc;
    private String soaq__en__desc;
    private String soaq_image;
    private boolean is_favorite;
    private String City_Name;

    public Alsoaq(int id, String soaq_ar_name, String soaq_en_name, String soaq__ar_desc, String soaq__en__desc, String soaq_image, boolean is_favorite, String city_Name) {
        this.id = id;
        this.soaq_ar_name = soaq_ar_name;
        this.soaq_en_name = soaq_en_name;
        this.soaq__ar_desc = soaq__ar_desc;
        this.soaq__en__desc = soaq__en__desc;
        this.soaq_image = soaq_image;
        this.is_favorite = is_favorite;
        City_Name = city_Name;
    }

    public Alsoaq() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSoaq_ar_name() {
        return soaq_ar_name;
    }

    public void setSoaq_ar_name(String soaq_ar_name) {
        this.soaq_ar_name = soaq_ar_name;
    }

    public String getSoaq_en_name() {
        return soaq_en_name;
    }

    public void setSoaq_en_name(String soaq_en_name) {
        this.soaq_en_name = soaq_en_name;
    }

    public String getSoaq__ar_desc() {
        return soaq__ar_desc;
    }

    public void setSoaq__ar_desc(String soaq__ar_desc) {
        this.soaq__ar_desc = soaq__ar_desc;
    }

    public String getSoaq__en__desc() {
        return soaq__en__desc;
    }

    public void setSoaq__en__desc(String soaq__en__desc) {
        this.soaq__en__desc = soaq__en__desc;
    }

    public String getSoaq_image() {
        return soaq_image;
    }

    public void setSoaq_image(String soaq_image) {
        this.soaq_image = soaq_image;
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
