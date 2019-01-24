package com.gokay.bitirmeprojesi.gsd;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class OgrenciData {
    @SerializedName("ogrenciData")
    @Expose
    public ArrayList<Ogrenci> ogrenciData=null;
}
