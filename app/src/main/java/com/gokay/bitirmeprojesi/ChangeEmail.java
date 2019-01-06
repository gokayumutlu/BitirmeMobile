package com.gokay.bitirmeprojesi;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.gokay.bitirmeprojesi.m.Kullanici;
import com.gokay.bitirmeprojesi.m.URL;
import com.gokay.bitirmeprojesi.v.LoginActivity;
import com.google.firebase.database.core.view.Change;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public  class ChangeEmail extends AsyncTask<String,Void,String>{
    MainActivity main=new MainActivity();
    private String g_email;
    private String alici_email;
    Context context;
    private AsyncResponse res=null;


    public ChangeEmail(AsyncResponse res){
        this.res=res;
    }




    @Override
    protected String doInBackground(String... strings) {
        g_email=strings[0];
        Log.d("tag_g_email",g_email);
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
        return alici_email;
    }


    @Override
    protected void onPostExecute(String s) {
        res.responseEmail(s);
    }

}
