package com.gokay.bitirmeprojesi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gokay.bitirmeprojesi.M.Kullanici;
import com.gokay.bitirmeprojesi.M.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    private EditText email,sifre;
    private Button girisButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email=findViewById(R.id.login_email);
        sifre=findViewById(R.id.login_password);
        girisButton=findViewById(R.id.login_button);

        girisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                giris();
            }
        });
    }

    private void giris(){
        URL url=new URL();
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(url.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api=retrofit.create(Api.class);
        Call<Kullanici> call=api.girisYap(email.getText().toString(),sifre.getText().toString());
        call.enqueue(new Callback<Kullanici>() {
            @Override
            public void onResponse(Call<Kullanici> call, Response<Kullanici> response) {
                if(response.code()==200){
                    Toast.makeText(getApplicationContext(),"Başarılı!",Toast.LENGTH_LONG).show();
                    Log.d("Başarılı: ","Giriş işlemi başarılı");
                }
                else if(response.code()==403){
                    Toast.makeText(getApplicationContext(),"Başarısız!",Toast.LENGTH_LONG).show();
                    Log.d("Başarısız: ","Giriş işlemi başarısız");
                }
            }

            @Override
            public void onFailure(Call<Kullanici> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"errorrrr",Toast.LENGTH_LONG).show();
                Log.d("tag3","error");

            }
        });
    }

}
