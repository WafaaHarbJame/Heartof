package com.heartoftheworldapp.heartoftheworld.Model;

public class Azkar {
    private int id;
    private String Azkar_ar_name_title;
    private  String Azkar_text_ar_name;

    public Azkar(int id, String azkar_ar_name_title, String azkar_text_ar_name) {
        this.id = id;
        Azkar_ar_name_title = azkar_ar_name_title;
        Azkar_text_ar_name = azkar_text_ar_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAzkar_ar_name_title() {
        return Azkar_ar_name_title;
    }

    public void setAzkar_ar_name_title(String azkar_ar_name_title) {
        Azkar_ar_name_title = azkar_ar_name_title;
    }

    public String getAzkar_text_ar_name() {
        return Azkar_text_ar_name;
    }

    public void setAzkar_text_ar_name(String azkar_text_ar_name) {
        Azkar_text_ar_name = azkar_text_ar_name;
    }
}
