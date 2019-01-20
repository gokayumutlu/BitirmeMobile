package com.gokay.bitirmeprojesi;

import android.app.ListActivity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.gokay.bitirmeprojesi.m.Ilac;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class IlacTakipO extends ListActivity{

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilac_takip_o);

        //adapt
        ListAdapt listAdapt=new ListAdapt(this);
        setListAdapter(listAdapt);

        //TODO Progress dialog ekle
        mAuth=FirebaseAuth.getInstance();
        String current_email=mAuth.getCurrentUser().getEmail();


        //LoadIlacDataWithoutAsyncTask loadwA=new LoadIlacDataWithoutAsyncTask(listAdapt);
        Log.d("when1","oncreate");
        //loadwA.loadData("farkliemail@outlook.com",this);

        A a=new A(listAdapt,"farkliemail@outlook.com");
        a.func(this);


    }
    /*
    @Override
    public void callList(ArrayList<Ilac> ilacList) {
        Log.d("when4","calllist");
        Log.d("calllist",ilacList.get(1).getDesc());
        mAdapter.update(ilacList);

    }
    */

}
