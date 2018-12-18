package com.gokay.bitirmeprojesi;

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
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=findViewById(R.id.textView);
        URL url=new URL();
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(url.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api=retrofit.create(Api.class);

        Call<List<Kullanici>> call=api.tumKullanicilar();
        call.enqueue(new Callback<List<Kullanici>>() {
            @Override
            public void onResponse(Call<List<Kullanici>> call, Response<List<Kullanici>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"başarısızzzz",Toast.LENGTH_LONG).show();
                    Log.d("tag1","başarısız");
                    textView.setText("code: "+response.code());


                }
                else{
                    Toast.makeText(getApplicationContext(),"başarılııı",Toast.LENGTH_LONG).show();
                    Log.d("tag2","başarılı");
                    List<Kullanici> k=response.body();
                    for(Kullanici kk:k){
                        String c="";
                        c+="Ad: "+kk.getAd()+"\n";
                        c+="Soyad: "+kk.getSoyad()+"\n";
                        c+="Email: "+kk.getEmail()+"\n";
                        c+="Sifre: "+kk.getSifre()+"\n\n";

                        textView.append(c);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Kullanici>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"errorrrr",Toast.LENGTH_LONG).show();
                Log.d("tag3","error");
                textView.setText(t.getMessage());
            }
        });
    }
}
