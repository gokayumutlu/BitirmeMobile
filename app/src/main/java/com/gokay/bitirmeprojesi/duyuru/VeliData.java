package com.gokay.bitirmeprojesi.duyuru;

import com.gokay.bitirmeprojesi.ilacTakip.Ilac;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class VeliData {
    @SerializedName("veliData")
    @Expose
    public ArrayList<Veli> veliData = null;
}
