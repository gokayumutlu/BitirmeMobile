package com.gokay.bitirmeprojesi.m;

public class Kullanici {
    private String ad;
    private String soyad;
    private String email;
    private String sifre;

    public Kullanici(String ad, String soyad, String email, String sifre){
        this.ad=ad;
        this.soyad=soyad;
        this.email=email;
        this.sifre=sifre;
    }

    public String getAd() {
        return ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public String getEmail() {
        return email;
    }

    public String getSifre() {
        return sifre;
    }


}
