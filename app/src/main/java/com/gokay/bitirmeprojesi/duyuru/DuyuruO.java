package com.gokay.bitirmeprojesi.duyuru;

import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.gokay.bitirmeprojesi.Api;
import com.gokay.bitirmeprojesi.R;
import com.gokay.bitirmeprojesi.m.URL;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DuyuruO extends AppCompatActivity {

    private ViewPager mViewPager;
    private DGonderPagerAdapter dgpAdapter;
    private TabLayout mtabLayout;
    private Toolbar toolbar;

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duyuru_o);
        toolbar=findViewById(R.id.duyuru_o_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Duyuru Yayınla");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mViewPager=findViewById(R.id.duyuruOVP);
        dgpAdapter=new DGonderPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(dgpAdapter);
        mtabLayout=findViewById(R.id.duyuruOTab);
        mtabLayout.setupWithViewPager(mViewPager);

    }


    private class BackTask extends AsyncTask<String,Void,Void>{
        ArrayList<Veli> veliList;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            veliList=new ArrayList<>();
        }

        @Override
        protected Void doInBackground(String... strings) {
            String g_email=strings[0];
            Log.d("veligetir","veligetirbaşı");
            URL url=new URL();
            Retrofit retrofit=new Retrofit.Builder()
                    .baseUrl(url.getUrl1())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            Api api=retrofit.create(Api.class);
            Call<VeliData> call=api.sinifVGetir(g_email);
            call.enqueue(new Callback<VeliData>() {
                @Override
                public void onResponse(Call<VeliData> call, Response<VeliData> response) {

                    if(!response.isSuccessful()){
                        Log.d("error data load res: ", response.message());
                        Log.d("when3","onresponsenotsuccessful");

                    }
                    else{
                        Log.d("succ data load res: ",response.message());
                        Log.d("when3","onresponsesuccessful");

                        ArrayList<Veli> veliArray=response.body().veliData;


                    }

                }

                @Override
                public void onFailure(Call<VeliData> call, Throwable t) {
                    Log.d("data load failure",t.getMessage());
                    Log.d("when3","onfailure");
                }
            });

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            
        }
    }
}
