package com.gokay.bitirmeprojesi;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

public class IlacTakipO extends ListActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilac_takip_o);

        ListAdapt listAdapt=new ListAdapt(this);
        setListAdapter(listAdapt);

        //TODO Progress dialog ekle
        mAuth=FirebaseAuth.getInstance();
        String current_email=mAuth.getCurrentUser().getEmail();
        LoadIlacData loadIlacData=new LoadIlacData(listAdapt);
        loadIlacData.execute(current_email);

    }
}
