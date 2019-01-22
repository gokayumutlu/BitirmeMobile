package com.gokay.bitirmeprojesi.ilacTakip;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ilac {

    @SerializedName("ogrenci")
    @Expose
    private String ogrenci;
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

    public Ilac(String ogrenci,String ilacAdi, String doz, String desc) {
        this.ogrenci=ogrenci;
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

    public String getOgrenci() {
        return ogrenci;
    }

    public void setOgrenci(String ogrenci) {
        this.ogrenci = ogrenci;
    }


}
