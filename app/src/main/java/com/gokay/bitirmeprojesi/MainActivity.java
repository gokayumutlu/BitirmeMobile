package com.gokay.bitirmeprojesi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.gokay.bitirmeprojesi.M.Kullanici;
import com.gokay.bitirmeprojesi.M.URL;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoginStatus ls=new LoginStatus();
        if(!ls.getLoginStatus(getApplicationContext())){
            Intent loginIntent=new Intent(MainActivity.this,LoginActivity.class);
            startActivity(loginIntent);
            finish();
        }

    }
}
