package com.gokay.bitirmeprojesi.gsd;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ogrenci {

    @SerializedName("ogrenci")
    @Expose
    private String ogrenci_adi;
    @SerializedName("ogrenci_id")
    @Expose
    private int ogrenci_id;

    public Ogrenci(String ogrenci_adi,int ogrenci_id) {
        this.ogrenci_adi = ogrenci_adi;
        this.ogrenci_id=ogrenci_id;
    }

    public String getOgrenci_adi() {
        return ogrenci_adi;
    }

    public void setOgrenci_adi(String ogrenci_adi) {
        this.ogrenci_adi = ogrenci_adi;
    }

    public int getOgrenci_id() {
        return ogrenci_id;
    }

    public void setOgrenci_id(int ogrenci_id) {
        this.ogrenci_id = ogrenci_id;
    }
}
