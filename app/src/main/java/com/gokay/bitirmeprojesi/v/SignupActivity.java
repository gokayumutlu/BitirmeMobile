package com.gokay.bitirmeprojesi.v;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.gokay.bitirmeprojesi.Api;
import com.gokay.bitirmeprojesi.MainActivity;
import com.gokay.bitirmeprojesi.m.Kullanici;
import com.gokay.bitirmeprojesi.m.URL;
import com.gokay.bitirmeprojesi.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import org.w3c.dom.Text;

import java.util.HashMap;


public class SignupActivity extends AppCompatActivity {
    private EditText ad,soyad,email,sifre;
    private Button kayitButton;
    private TextView textView;
    private Toolbar toolbar;

    //TODO Progress Dialog Ekle

    //Firebase
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //Firebase
        mAuth = FirebaseAuth.getInstance();

        toolbar=findViewById(R.id.register_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Kayıt Ol");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ad=findViewById(R.id.signup_ad);
        soyad=findViewById(R.id.signup_soyad);
        email=findViewById(R.id.signup_email);
        sifre=findViewById(R.id.signup_sifre);
        kayitButton=findViewById(R.id.signup_button);
        textView=findViewById(R.id.textView2);


        kayitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //My Register
                //submit();

                String adT=ad.getText().toString();
                String soyadT=soyad.getText().toString();
                String emailT=email.getText().toString();
                String sifreT=sifre.getText().toString();

                if(!TextUtils.isEmpty(adT) || !TextUtils.isEmpty(soyadT) || !TextUtils.isEmpty(emailT) || !TextUtils.isEmpty(sifreT)){
                    //Firebase Register
                    register(ad.getText().toString(),soyad.getText().toString(),email.getText().toString(),sifre.getText().toString());
                }


            }
        });
    }


    private void submit(){
        URL url=new URL();
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(url.getUrl1())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api=retrofit.create(Api.class);
        Call<Kullanici> call=api.kayitol(ad.getText().toString(),soyad.getText().toString(),email.getText().toString(),sifre.getText().toString());
        call.enqueue(new Callback<Kullanici>() {
            @Override
            public void onResponse(Call<Kullanici> call, Response<Kullanici> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"başarısızzz",Toast.LENGTH_LONG).show();
                    Log.d("tag1","başarısız");
                    Log.d("status code", response.message());
                    textView.setText("response code: "+response.code());

                }
                else{
                    Toast.makeText(getApplicationContext(),"başarılııı",Toast.LENGTH_LONG).show();
                    Log.d("tag2","başarılı");
                }

            }

            @Override
            public void onFailure(Call<Kullanici> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"errorrrr",Toast.LENGTH_LONG).show();
                Log.d("tag3","error");
                textView.setText("throw: "+t.getMessage());

            }
        });

    }

    private void register(final String ad, final String soyad, final String email, String sifre){
        mAuth.createUserWithEmailAndPassword(email,sifre).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    FirebaseUser current_user=FirebaseAuth.getInstance().getCurrentUser();
                    final String uid=current_user.getUid();

                    //Toast.makeText(getApplicationContext(),uid,Toast.LENGTH_LONG).show();

                    database=FirebaseDatabase.getInstance();
                    ref=database.getReference().child("Users").child(uid);

                    HashMap<String,String> userMap=new HashMap<>();
                    userMap.put("ad",ad);
                    userMap.put("soyad",soyad);
                    userMap.put("email",email);
                    ref.setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Intent loginIntent=new Intent(SignupActivity.this, LoginActivity.class);
                                //loginIntent.putExtra("user_id",uid);
                                startActivity(loginIntent);
                                finish();
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"Error Maalesef",Toast.LENGTH_LONG).show();
                            }

                        }
                    });





                }
                else{
                    Toast.makeText(SignupActivity.this,"Errorrr",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



}
