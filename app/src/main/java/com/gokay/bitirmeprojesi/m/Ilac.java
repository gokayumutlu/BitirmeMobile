package com.gokay.bitirmeprojesi.m;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ilac {


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

    public Ilac(String ilacAdi, String doz, String desc) {
        this.ilacAdi = ilacAdi;
        this.doz = doz;
        this.desc = desc;
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

    @Override
    public String toString() {
        return "Ilac{" +
                "ilacAdi='" + ilacAdi + '\'' +
                ", doz='" + doz + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
