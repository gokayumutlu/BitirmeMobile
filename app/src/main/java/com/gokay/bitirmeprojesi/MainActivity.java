package com.gokay.bitirmeprojesi;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.gokay.bitirmeprojesi.m.Kullanici;
import com.gokay.bitirmeprojesi.v.LoginActivity;
import com.gokay.bitirmeprojesi.v.SignupActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private LinearLayout ll;
    private String user_id;
    private String alici_email;
    public String alici_id;
    private String alici;

    //Firabase
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        //user_id=getIntent().getStringExtra("user_id");
        user_id=mAuth.getCurrentUser().getUid();
        alici_email=getIntent().getStringExtra("alici_email");
        //Log.d("tag main: ",alici_email);
        //alici_id=getIdByEmail(alici_email);
        //Log.d("tag",alici_id);

        ll=findViewById(R.id.linear_message);

        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Task myTask=new Task();
                myTask.execute(alici_email);
                try{
                    alici_id=myTask.get();
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    intent();
                }

            }
        });


        //TODO
        //Firebase haricinde kendi login işlemini kontrol et

        /*
        LoginStatus ls=new LoginStatus();
        if(!ls.getLoginStatus(getApplicationContext())){
            Intent loginIntent=new Intent(MainActivity.this,LoginActivity.class);
            startActivity(loginIntent);
            finish();
        }
        */

        toolbar=findViewById(R.id.main_app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Anaokulu Öğrenci Takip Sistemi");

    }
    public void intent(){
        Intent messageIntent=new Intent(MainActivity.this,ChatActivity.class);
        messageIntent.putExtra("user_id",user_id);
        messageIntent.putExtra("alici_id",alici_id);
        //Log.d("tag1",alici_id);
        //Log.d("tag2",user_id);
        startActivity(messageIntent);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser==null){
            cikis();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if(item.getItemId() == R.id.main_menu_logout){
            //Firebase Logout
            FirebaseAuth.getInstance().signOut();
            cikis();
            //TODO
            //kendi logout'unu yaz
        }

        return true;
    }

    public void cikis(){
        Intent i =new Intent(MainActivity.this,LoginActivity.class);
        startActivity(i);
        finish();
    }



    public String getIdByEmail(final String email){

        database=FirebaseDatabase.getInstance();
        Query qref=database.getReference("Users").orderByChild("email").equalTo(email);

        qref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                //Log.d("taggelenemail",email);
                Kullanici kullanici=dataSnapshot.getValue(Kullanici.class);
                //Log.d("tagoldu",dataSnapshot.getKey());
                alici=dataSnapshot.getKey();
                //Log.d("tag",alici);

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
    }

}
