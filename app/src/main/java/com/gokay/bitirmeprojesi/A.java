package com.gokay.bitirmeprojesi;

import android.content.Context;
import android.util.Log;

import com.gokay.bitirmeprojesi.m.Ilac;

import java.util.ArrayList;
import java.util.List;

public class A implements LoadDataInterface{
    private String email;
    private ListAdapt mAdapt;

    public A(ListAdapt adapt, String email){
        mAdapt=adapt;
        this.email=email;
    }

    public void func(Context context){
        //adapt
        Log.d("func","inside func"+email+mAdapt);
        LoadIlacDataWithoutAsyncTask loadWA=new LoadIlacDataWithoutAsyncTask(mAdapt);
        loadWA.loadData(email,this);
    }

    @Override
    public void callList(ArrayList<Ilac> ilacList) {
        Log.d("when4","calllist");
        Log.d("calllist",ilacList.get(1).getDesc());
        mAdapt.update(ilacList);
    }
}
