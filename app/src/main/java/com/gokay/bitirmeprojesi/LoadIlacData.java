package com.gokay.bitirmeprojesi;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.gokay.bitirmeprojesi.m.Ilac;
import com.gokay.bitirmeprojesi.m.URL;
import com.google.gson.JsonArray;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoadIlacData extends AsyncTask<String,Void,Void> {

    private final ListAdapt mAdapter;


    public LoadIlacData(ListAdapt mAdapter) {
        this.mAdapter = mAdapter;
    }

    @Override
    protected Void doInBackground(String... params) {
        String current_email=params[0];

        URL url=new URL();
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(url.getUrl1())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api=retrofit.create(Api.class);
        Call<List<Ilac>> call=api.ilacTakipG3("farkliemail@outlook.com");
        call.enqueue(new Callback<List<Ilac>>() {
            @Override
            public void onResponse(Call<List<Ilac>> call, Response<List<Ilac>> response) {

                if(!response.isSuccessful()){
                    Log.d("error data load res: ", response.message());

                }
                else{
                    Log.d("succ data load res: ",response.message());

                    List<Ilac> ill=response.body();

                }

            }

            @Override
            public void onFailure(Call<List<Ilac>> call, Throwable t) {
                Log.d("data load failure",t.getMessage());

            }
        });
        return null;
    }
}
