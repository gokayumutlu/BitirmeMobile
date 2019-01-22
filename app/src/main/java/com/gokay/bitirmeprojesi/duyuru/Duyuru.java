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

    public Duyuru(String ogretmen_email, String sinif_adi, String icerik) {
        this.ogretmen_email = ogretmen_email;
        this.sinif_adi = sinif_adi;
        this.icerik = icerik;
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
}
