package com.gokay.bitirmeprojesi;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.gokay.bitirmeprojesi.m.Kullanici;
import com.gokay.bitirmeprojesi.m.URL;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EmailTakas implements Runnable{
    private String g_email;
    private volatile String alici_email;
    public Context context;
    public EmailTakas(Context gcontext,String email){
        g_email=email;
        context=gcontext;
    }

    public EmailTakas(){

    }
    @Override
    public void run() {
        URL url=new URL();
        Gson gson=new GsonBuilder().setLenient().create();
        /*
        OkHttpClient client=new OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60,TimeUnit.SECONDS)
                .writeTimeout(60,TimeUnit.SECONDS)
                .build();
                */
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(url.getUrl1())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        Api api=retrofit.create(Api.class);
        Call<Kullanici> call=api.emailTakas(g_email);
        call.enqueue(new Callback<Kullanici>() {
            @Override
            public void onResponse(Call<Kullanici> call, Response<Kullanici> response) {
                //Log.d("tag5",response.message());
                alici_email=response.body().getEmail();
                Log.d("asdqwe",alici_email);

            }

            @Override
            public void onFailure(Call<Kullanici> call, Throwable t) {
                Toast.makeText(context,"errorrrrrr",Toast.LENGTH_LONG).show();
                Log.d("tag4",t.getMessage());
            }
        });
    }

    public String getAlici_email(){
        return alici_email;
    }
}
