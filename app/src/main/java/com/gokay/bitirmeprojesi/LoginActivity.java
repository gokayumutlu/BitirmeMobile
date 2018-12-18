package com.gokay.bitirmeprojesi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    EditText ad,soyad,email,sifre;
    Button kayitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ad=findViewById(R.id.signup_ad);
        soyad=findViewById(R.id.signup_soyad);
        email=findViewById(R.id.signup_email);
        sifre=findViewById(R.id.signup_sifre);
        kayitButton=findViewById(R.id.signup_button);
    }

}
