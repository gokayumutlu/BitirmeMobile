package com.gokay.bitirmeprojesi;

import com.gokay.bitirmeprojesi.m.Ilac;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class IlacList {
    @SerializedName("result")
    private List<Ilac> ListIlac;

    public List<Ilac> getListIlac() {
        return ListIlac;
    }
}
