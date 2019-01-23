package com.gokay.bitirmeprojesi.messaging;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;
import com.gokay.bitirmeprojesi.Api;
import com.gokay.bitirmeprojesi.R;
import com.gokay.bitirmeprojesi.duyuru.Veli;
import com.gokay.bitirmeprojesi.duyuru.VeliData;
import com.gokay.bitirmeprojesi.m.URL;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChatVeliList extends AppCompatActivity implements LoadVeliInterface{

    private Toolbar toolbar;
    private RecyclerView veliRcyc;
    private List<Veli> veliList;
    private VeliAdapter veliAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_veli_list);

        toolbar=findViewById(R.id.chat_list_layout);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Veli Listesi");
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        veliRcyc=findViewById(R.id.veliList);
        veliRcyc.setHasFixedSize(true);
        veliRcyc.setLayoutManager(new LinearLayoutManager(this));



    }

    @Override
    protected void onStart() {
        super.onStart();

        String current_email="farkliemail@outlook.com";
        loadData(current_email,this);

    }


    private void loadData(String email,final LoadVeliInterface callback){

        URL url=new URL();
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(url.getUrl1())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api=retrofit.create(Api.class);
        Call<VeliData> call=api.sinifVGetir(email);
        call.enqueue(new Callback<VeliData>() {
            @Override
            public void onResponse(Call<VeliData> call, Response<VeliData> response) {
                if(!response.isSuccessful()){
                    //Toast.makeText(context,"Bşarısız",Toast.LENGTH_LONG).show();
                    Log.d("not successfull", response.message());

                }
                else{
                    //Toast.makeText(context,"başarılı!",Toast.LENGTH_LONG).show();
                    Log.d("başarılı","başarılı");
                    ArrayList<Veli> veliArray=response.body().veliData;
                    callback.callVeliList(veliArray);
                }

            }

            @Override
            public void onFailure(Call<VeliData> call, Throwable t) {
                //Toast.makeText(context,"failure!",Toast.LENGTH_LONG).show();
                Log.d("failure",t.getMessage());

            }
        });
    }


    @Override
    public void callVeliList(ArrayList<Veli> veliList) {
        veliAdapter=new VeliAdapter(ChatVeliList.this,veliList);
        veliRcyc.setAdapter(veliAdapter);
    }
}
