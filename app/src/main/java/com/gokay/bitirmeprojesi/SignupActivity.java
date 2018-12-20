package com.gokay.bitirmeprojesi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.gokay.bitirmeprojesi.M.Kullanici;
import com.gokay.bitirmeprojesi.M.URL;




public class SignupActivity extends AppCompatActivity {
    private EditText ad,soyad,email,sifre;
    private Button kayitButton;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        ad=findViewById(R.id.signup_ad);
        soyad=findViewById(R.id.signup_soyad);
        email=findViewById(R.id.signup_email);
        sifre=findViewById(R.id.signup_sifre);
        kayitButton=findViewById(R.id.signup_button);
        textView=findViewById(R.id.textView2);
        kayitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submit();
            }
        });
    }


    private void submit(){
        URL url=new URL();
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(url.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api=retrofit.create(Api.class);
        Call<Kullanici> call=api.kayitol(ad.getText().toString(),soyad.getText().toString(),email.getText().toString(),sifre.getText().toString());
        call.enqueue(new Callback<Kullanici>() {
            @Override
            public void onResponse(Call<Kullanici> call, Response<Kullanici> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"başarısızzz",Toast.LENGTH_LONG).show();
                    Log.d("tag1","başarısız");
                    Log.d("status code", response.message());
                    textView.setText("response code: "+response.code());

                }
                else{
                    Toast.makeText(getApplicationContext(),"başarılııı",Toast.LENGTH_LONG).show();
                    Log.d("tag2","başarılı");
                }

            }

            @Override
            public void onFailure(Call<Kullanici> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"errorrrr",Toast.LENGTH_LONG).show();
                Log.d("tag3","error");
                textView.setText("throw: "+t.getMessage());

            }
        });

    }



}
