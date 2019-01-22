package com.gokay.bitirmeprojesi.ilacTakip;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gokay.bitirmeprojesi.Api;
import com.gokay.bitirmeprojesi.R;
import com.gokay.bitirmeprojesi.ilacTakip.Ilac;
import com.gokay.bitirmeprojesi.m.URL;
import com.google.firebase.auth.FirebaseAuth;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IlacTakipV extends AppCompatActivity {

    private Toolbar toolbar;

    private FirebaseAuth mAuth;
    private String current_email,ilacAdiT,dozT,descT;
    private EditText ilacAdi;
    private EditText doz;
    private EditText desc;
    private Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilac_takipv);

        toolbar=findViewById(R.id.medicine_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("İlaç Takip");

        ilacAdi=findViewById(R.id.et_ilacadi);
        doz=findViewById(R.id.et_ilacdoz);
        desc=findViewById(R.id.et_ilacaciklama);
        sendButton=findViewById(R.id.submitButton);

        mAuth=FirebaseAuth.getInstance();
        current_email=mAuth.getCurrentUser().getEmail();

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ilacAdiT=ilacAdi.getText().toString();
                dozT=doz.getText().toString();
                descT=desc.getText().toString();

                submit();
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
        Call<Ilac> call=api.ilacTakipE(current_email,ilacAdiT,dozT,descT);
        call.enqueue(new Callback<Ilac>() {
            @Override
            public void onResponse(Call<Ilac> call, Response<Ilac> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"başarısızzz",Toast.LENGTH_LONG).show();
                    Log.d("ilac gonderme basarisiz", response.message());

                }
                else{
                    Toast.makeText(getApplicationContext(),"başarılııı",Toast.LENGTH_LONG).show();
                    Log.d("ilac gonderme basarili","başarılı");
                }

            }

            @Override
            public void onFailure(Call<Ilac> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"errorrrr",Toast.LENGTH_LONG).show();
                Log.d("ilac gonderme failure",t.getMessage());

            }
        });

    }
}
