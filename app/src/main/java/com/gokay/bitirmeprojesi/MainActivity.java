package com.gokay.bitirmeprojesi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.gokay.bitirmeprojesi.duyuru.DuyuruO;
import com.gokay.bitirmeprojesi.ilacTakip.IlacTakipO;
import com.gokay.bitirmeprojesi.ilacTakip.IlacTakipV;
import com.gokay.bitirmeprojesi.m.Kullanici;
import com.gokay.bitirmeprojesi.messaging.ChatActivity;
import com.gokay.bitirmeprojesi.v.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;



public class MainActivity extends AppCompatActivity{

    private Toolbar toolbar;
    private LinearLayout ll;
    private LinearLayout medicine_layout;
    private LinearLayout gsd_linear;
    private LinearLayout anno_linear;
    private String user_id;
    private String alici_name;
    public String alici_id;
    private String alici;
    private String et;
    private String g_email;
    private int login_status;
    public int i;

    //Kolaylık olması açısından öğretmen emaili ve firebase uid'si sağlandı.
    private String amail="farkliemail@outlook.com";
    private String aid="EBlKe7Zo3eVu3vIEkB8cNBdTp7s1";
    private String alici_ad="Gökhan Umutsuz";
    //2
    private String amail2="nedenneden@gmail.com";
    private String aid2="ArsnUnmf2tYGEHhr9Zl0MUAIxJ62";
    private String alici_ad2="Gökay Umutlu";

    //Firabase
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference ref;

    public static final String MyPREFERENCES = "MyPrefs" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences=getSharedPreferences(MyPREFERENCES,Context.MODE_PRIVATE);
        g_email=sharedPreferences.getString("current_email","rong");
        //g_email=getIntent().getStringExtra("current_email");

        if (g_email.equals(amail)) {
            alici_id=aid2;
            alici_name=alici_ad2;
        }
        else if(g_email.equals(amail2)){
            alici_id=aid;
            alici_name=alici_ad;
        }
        else{
            Log.d("tag_main_email_error","email error");
        }




        //login_status=getIntent().getIntExtra("login_status");


        //login_status=0;


        ll=findViewById(R.id.linear_message);
        medicine_layout=findViewById(R.id.linear_medicine);
        gsd_linear=findViewById(R.id.linear_gsd);
        anno_linear=findViewById(R.id.linear_anno);
        mAuth = FirebaseAuth.getInstance();

        user_id=mAuth.getCurrentUser().getUid();

        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chat=new Intent(MainActivity.this, ChatActivity.class);
                chat.putExtra("alici_id",alici_id);
                chat.putExtra("gonderen_id",user_id);
                chat.putExtra("alici_ad",alici_name);
                startActivity(chat);

            }
        });

        medicine_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent medicine_layout=new Intent(MainActivity.this, IlacTakipV.class);
                startActivity(medicine_layout);
            }
        });

        gsd_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goster=new Intent(MainActivity.this, IlacTakipO.class);
                startActivity(goster);
            }
        });

        anno_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent anno_layout=new Intent(MainActivity.this, DuyuruO.class);
                startActivity(anno_layout);
            }
        });

        /*
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent messageIntent=new Intent(MainActivity.this,ChatActivity.class);
                messageIntent.putExtra("user_id",user_id);
                messageIntent.putExtra("alici_id",alici_id);
                //messageIntent.putExtra("alici_id2",et);
                Log.d("tag_user",user_id);
                //Log.d("tag_alici",et);
                //Log.d("tag_alici",alici_id);
                startActivity(messageIntent);

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
         */

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
    }



    /*
    public void func(final String aaa){

                Intent messageIntent=new Intent(MainActivity.this,ChatActivity.class);
                messageIntent.putExtra("user_id",user_id);
                messageIntent.putExtra("alici_id",aaa);
                //messageIntent.putExtra("alici_id2",et);
                Log.d("tag_user",user_id);
                //Log.d("tag_alici",et);
                //Log.d("tag_alici",alici_id);
    }
    */
}

    /*
    class Async extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... strings) {
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
        }

        @Override
        protected void onPostExecute(String s) {
            Log.d("tag55",s);
            //func();
        }
    }
    */


