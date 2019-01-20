package com.gokay.bitirmeprojesi;

import com.gokay.bitirmeprojesi.m.Ilac;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ilacData {
    @SerializedName("ilacData")
    @Expose
    public ArrayList<Ilac> ilacData = null;
}
