package com.gokay.bitirmeprojesi.m;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ilac {

    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("ilac_adi")
    @Expose
    private String ilacAdi;
    @SerializedName("adet")
    @Expose
    private String doz;
    @SerializedName("aciklama")
    @Expose
    private String desc;



    public Ilac(){

    }

    public Ilac(String email, String ilacAdi, String doz, String desc) {
        this.email = email;
        this.ilacAdi = ilacAdi;
        this.doz = doz;
        this.desc = desc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIlacAdi() {
        return ilacAdi;
    }

    public void setIlacAdi(String ilacAdi) {
        this.ilacAdi = ilacAdi;
    }

    public String getDoz() {
        return doz;
    }

    public void setDoz(String doz) {
        this.doz = doz;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
