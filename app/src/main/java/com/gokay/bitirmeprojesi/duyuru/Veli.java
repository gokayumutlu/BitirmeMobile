package com.gokay.bitirmeprojesi.duyuru;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Veli {
    @SerializedName("veli_adi")
    @Expose
    private String veli_adi;
    @SerializedName("veli_email")
    @Expose
    private String veli_email;

    public Veli(String veli_adi, String veli_email) {
        this.veli_adi = veli_adi;
        this.veli_email = veli_email;
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
