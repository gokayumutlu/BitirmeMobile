package com.gokay.bitirmeprojesi.ilacTakip;

import android.content.Context;
import android.util.Log;

import com.gokay.bitirmeprojesi.ilacTakip.Ilac;
import com.gokay.bitirmeprojesi.ilacTakip.ListAdapt;
import com.gokay.bitirmeprojesi.ilacTakip.LoadDataInterface;
import com.gokay.bitirmeprojesi.ilacTakip.LoadIlacDataWithoutAsyncTask;

import java.util.ArrayList;

public class ilacAra implements LoadDataInterface {
    private String email;
    private ListAdapt mAdapt;

    public ilacAra(ListAdapt adapt, String email){
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
