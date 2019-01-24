package com.gokay.bitirmeprojesi.gsd;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Gsd {
    @SerializedName("uyku")
    @Expose
    private String uyku;
    @SerializedName("yemek")
    @Expose
    private String yemek;
    @SerializedName("ogretmen_email")
    @Expose
    private String ogretmen_email;
    @SerializedName("ogretmen_id")
    @Expose
    private String ogrenci_id;
}
