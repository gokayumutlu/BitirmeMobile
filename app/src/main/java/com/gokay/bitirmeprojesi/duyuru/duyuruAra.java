package com.gokay.bitirmeprojesi.duyuru;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.gokay.bitirmeprojesi.ilacTakip.LoadIlacDataWithoutAsyncTask;
import java.util.ArrayList;

public class duyuruAra implements LoadVeliDataInterface{

    ArrayAdapter<Veli> veliAdapter;
    private String email;

    public duyuruAra(ArrayAdapter<Veli> veliAdapter,String email){
        this.veliAdapter=veliAdapter;
        this.email=email;
    }

    public void func(Context context){


        //adapt
        Log.d("func","inside func"+email+veliAdapter);



    }

    @Override
    public void callList(ArrayList<Veli> veliList) {

    }
}
