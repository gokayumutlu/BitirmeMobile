package com.gokay.bitirmeprojesi.v;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gokay.bitirmeprojesi.Api;
import com.gokay.bitirmeprojesi.AsyncResponse;
import com.gokay.bitirmeprojesi.ChangeEmail;
import com.gokay.bitirmeprojesi.EmailTakas;
import com.gokay.bitirmeprojesi.LoginStatus;
import com.gokay.bitirmeprojesi.MainActivity;
import com.gokay.bitirmeprojesi.m.Kullanici;
import com.gokay.bitirmeprojesi.m.URL;
import com.gokay.bitirmeprojesi.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.view.Change;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    private EditText email,sifre;
    private Button girisButton;
    private String user_id;
    private String alici_email;

    //TODO Progress Dialog Ekle

    //Firebase
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference ref;

    public static final String MyPREFERENCES = "MyPrefs" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //user_id=getIntent().getStringExtra("user_id");
        //Firebase
        mAuth = FirebaseAuth.getInstance();

        final SharedPreferences sharedPreferences=getSharedPreferences(MyPREFERENCES,Context.MODE_PRIVATE);

        email=findViewById(R.id.login_email);
        sifre=findViewById(R.id.login_password);
        girisButton=findViewById(R.id.login_button);

        girisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emailT=email.getText().toString();
                String sifreT=sifre.getText().toString();
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("current_email",emailT);
                editor.apply();
                if(!TextUtils.isEmpty(emailT) || !TextUtils.isEmpty(sifreT)){
                    //Benim giriş işlemim
                    //giris();

                    //Firabase giriş
                    //emailTakas(emailT);
                    girisYap(emailT,sifreT);
                }


            }
        });
    }
    /*
    private void giris(){
        URL url=new URL();
        final LoginStatus ls=new LoginStatus();
        Gson gson=new GsonBuilder().setLenient().create();
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(url.getUrl1())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        Api api=retrofit.create(Api.class);
        Call<Kullanici> call=api.girisYap(email.getText().toString(),sifre.getText().toString());
        call.enqueue(new Callback<Kullanici>() {
            @Override
            public void onResponse(Call<Kullanici> call, Response<Kullanici> response) {
                if(response.code()==200 || response.message()=="success"){
                    Toast.makeText(getApplicationContext(),"Başarılı!",Toast.LENGTH_LONG).show();
                    Log.d("Başarılı: ","Giriş işlemi başarılı");
                    ls.setUserEmail(email.getText().toString());
                    ls.setLoginStatus(getApplicationContext(),true);
                    ls.setUserEmail(email.getText().toString());
                    ls.setUser(getApplicationContext(),email.getText().toString());
                }
                else if(response.code()==403 || response.message()=="forbidden"){
                    Toast.makeText(getApplicationContext(),"Başarısız!",Toast.LENGTH_LONG).show();
                    Log.d("Başarısız: ","Giriş işlemi başarısız");

                    ls.setLoginStatus(getApplicationContext(),false);

                }
            }

            @Override
            public void onFailure(Call<Kullanici> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"errorrrr",Toast.LENGTH_LONG).show();
                Log.d("tag3",t.getMessage());

                ls.setLoginStatus(getApplicationContext(),false);
            }
        });
    }


    private void emailTakas(String emaill){
        URL url=new URL();
        Gson gson=new GsonBuilder().setLenient().create();

        //
        OkHttpClient client=new OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60,TimeUnit.SECONDS)
                .writeTimeout(60,TimeUnit.SECONDS)
                .build();
         //

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(url.getUrl1())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        Api api=retrofit.create(Api.class);
        Call<Kullanici> call=api.emailTakas(emaill);
        call.enqueue(new Callback<Kullanici>() {
            @Override
            public void onResponse(Call<Kullanici> call, Response<Kullanici> response) {
                //Log.d("tag5",response.message());
                alici_email=response.body().getEmail();
                //Log.d("asdqwe",alici_email);

            }

            @Override
            public void onFailure(Call<Kullanici> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"errorrrrrr",Toast.LENGTH_LONG).show();
                //Log.d("tag4",t.getMessage());
            }
        });
    }

    */
    private void girisYap(final String email, String sifre){
        mAuth.signInWithEmailAndPassword(email,sifre).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.d("tag_task_successful","successful");
                    Intent main=new Intent(LoginActivity.this,MainActivity.class);
                    //main.putExtra("current_email",email);
                    startActivity(main);
                    finish();
                }
                else{
                    Toast.makeText(LoginActivity.this,"Giriş Yapılamadı",Toast.LENGTH_LONG).show();
                    Log.d("tag_else","not successful");
                }
            }
        });
    }


    public class AT extends AsyncTask<String,Void,String>{
        private String g_email;
        private String a_email;


        @Override
        protected String doInBackground(String... strings) {
            g_email=strings[0];
            //Log.d("tag_g_email",g_email);
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
                    a_email=response.body().getEmail();
                    if(a_email!=null){
                        //Log.d("tag_response_not_null",a_email);
                    }
                    else{
                        //Log.d("tag_response_else","alici email null");
                    }


                }

                @Override
                public void onFailure(Call<Kullanici> call, Throwable t) {
                    //Toast.makeText(getApplicationContext(),"errorrrrrr",Toast.LENGTH_LONG).show();
                    //Log.d("tag4",t.getMessage());
                }
            });
            return a_email;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if(s!=null){
                Log.d("tag_post_exec_not_null",s);
                Intent main=new Intent(LoginActivity.this,MainActivity.class);
                main.putExtra("alici_email",s);
                startActivity(main);
                finish();
            }
            else{
                Log.d("tag_postexec_null","response null");
            }

        }

        public String returnV(String s){
            return s;
        }
    }


}
