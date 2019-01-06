package com.gokay.bitirmeprojesi;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.gokay.bitirmeprojesi.m.Kullanici;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class Task extends AsyncTask<String,Void,String>{
    MainActivity main=new MainActivity();

    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference ref;
    private String alici;

    @Override
    protected String doInBackground(String... strings) {
        /*
        final String email=strings[0];
        database=FirebaseDatabase.getInstance();
        Query qref=database.getReference("Users").orderByChild("email").equalTo(email);

        qref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.d("taggelenemail",email);
                Kullanici kullanici=dataSnapshot.getValue(Kullanici.class);
                Log.d("tagoldu",dataSnapshot.getKey());
                alici=dataSnapshot.getKey();
                Log.d("tag",alici);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                //Log.d("tagchanged",dataSnapshot.getKey());
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                //Log.d("tagremoved",dataSnapshot.getKey());
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                //Log.d("tagmoved",dataSnapshot.getKey());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("tagcancel",databaseError.getDetails());
            }
        });
        return alici;

        */
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        //main.func(s);
    }
}
