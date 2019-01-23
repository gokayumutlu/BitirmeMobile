package com.gokay.bitirmeprojesi.duyuru;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Duyuru {
    @SerializedName("ogretmen_email")
    @Expose
    private String ogretmen_email;
    @SerializedName("sinif_adi")
    @Expose
    private String sinif_adi;
    @SerializedName("icerik")
    @Expose
    private String icerik;
    @SerializedName("veli_adi")
    @Expose
    private String veli_adi;
    @SerializedName("veli_email")
    @Expose
    private String veli_email;

    public Duyuru(String ogretmen_email, String sinif_adi, String icerik, String veli_adi, String veli_email) {
        this.ogretmen_email = ogretmen_email;
        this.sinif_adi = sinif_adi;
        this.icerik = icerik;
        this.veli_adi=veli_adi;
        this.veli_email=veli_email;
    }

    public String getOgretmen_email() {
        return ogretmen_email;
    }

    public void setOgretmen_email(String ogretmen_email) {
        this.ogretmen_email = ogretmen_email;
    }

    public String getSinif_adi() {
        return sinif_adi;
    }

    public void setSinif_adi(String sinif_adi) {
        this.sinif_adi = sinif_adi;
    }

    public String getIcerik() {
        return icerik;
    }

    public void setIcerik(String icerik) {
        this.icerik = icerik;
    }

    public String getVeli_adi() {
        return veli_adi;
    }

    public void setVeli_adi(String veli_adi) {
        this.veli_adi = veli_adi;
    }

    public String getVeli_email() {
        return veli_email;
    }

    public void setVeli_email(String veli_email) {
        this.veli_email = veli_email;
    }
}
