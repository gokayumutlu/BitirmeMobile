package com.gokay.bitirmeprojesi.gsd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.gokay.bitirmeprojesi.Api;
import com.gokay.bitirmeprojesi.R;
import com.gokay.bitirmeprojesi.duyuru.Duyuru;
import com.gokay.bitirmeprojesi.duyuru.Veli;
import com.gokay.bitirmeprojesi.duyuru.VeliData;
import com.gokay.bitirmeprojesi.m.URL;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GsdOgretmen extends AppCompatActivity implements LoadOgrenciDataInteface{

    private EditText uykuEt;
    private EditText yemekEt;
    private Button sendButton;
    private FirebaseAuth mAuth;
    private String current_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gsd_ogretmen);

        uykuEt=findViewById(R.id.gsd_o_uyku_et);
        yemekEt=findViewById(R.id.gsd_o_yemek_et);
        sendButton=findViewById(R.id.gsd_o_sendB);
        mAuth=FirebaseAuth.getInstance();
        current_email=mAuth.getCurrentUser().getEmail();

        ogrencileriGetir(current_email,this);

    }

    public void ogrencileriGetir(String email,final LoadOgrenciDataInteface callback){
        URL url=new URL();
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(url.getUrl1())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api=retrofit.create(Api.class);
        Call<OgrenciData> call=api.sinifOGetir(email);
        call.enqueue(new Callback<OgrenciData>() {
            @Override
            public void onResponse(Call<OgrenciData> call, Response<OgrenciData> response) {

                if(!response.isSuccessful()){
                    Log.d("error data load res: ", response.message());
                    Log.d("when3","onresponsenotsuccessful");

                }
                else{
                    Log.d("succ data load res: ",response.message());
                    Log.d("when3","onresponsesuccessful");

                    ArrayList<Ogrenci> ogrenciArray=response.body().ogrenciData;
                    for(int i=0;i<ogrenciArray.size();i++){
                        Log.d("ogrenciler","ogrenci adi: "+ogrenciArray.get(i).getOgrenci_adi());
                    }
                    callback.callList(ogrenciArray);

                }

            }

            @Override
            public void onFailure(Call<OgrenciData> call, Throwable t) {
                Log.d("data load failure",t.getMessage());
                Log.d("when3","veligetir onfailure");
            }
        });
    }


    @Override
    public void callList(ArrayList<Ogrenci> ogrenciList) {
        String[] spinnerArray=new String[ogrenciList.size()];
        final HashMap<Integer,Integer> spinnerMap=new HashMap<Integer,Integer>();

        for(int i=0;i<ogrenciList.size();i++){
            spinnerMap.put(i,ogrenciList.get(i).getOgrenci_id());
            spinnerArray[i]=ogrenciList.get(i).getOgrenci_adi();
        }

        final Spinner spinner=findViewById(R.id.gsd_o_spinner);
        ArrayAdapter<String> ogrenciAdapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,spinnerArray);
        ogrenciAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(ogrenciAdapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                final int secilen_id=spinnerMap.get(spinner.getSelectedItemPosition());
                Log.d("secilen ogrenci id","id: "+secilen_id);

                if(secilen_id!=0){
                    sendButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String uykuText=uykuEt.getText().toString();
                            String yemekText=yemekEt.getText().toString();
                            if(!uykuText.isEmpty() && !yemekText.isEmpty()){
                                URL url=new URL();
                                Retrofit retrofit=new Retrofit.Builder()
                                        .baseUrl(url.getUrl1())
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build();
                                Api api=retrofit.create(Api.class);
                                Call<Gsd> call=api.gsdEkle(current_email,secilen_id,uykuText,yemekText);
                                call.enqueue(new Callback<Gsd>() {
                                    @Override
                                    public void onResponse(Call<Gsd> call, Response<Gsd> response) {
                                        if(!response.isSuccessful()){
                                            Toast.makeText(getApplicationContext(),"Değerlendirme gönderme başarısız!",Toast.LENGTH_LONG).show();
                                            Log.d("gsd gonder basarisiz", response.message());

                                        }
                                        else{
                                            Toast.makeText(getApplicationContext(),"Değerlendirme gönderme başarılı!",Toast.LENGTH_LONG).show();
                                            Log.d("gsd gonder basarili","başarılı");
                                            uykuEt.setText("");
                                            yemekEt.setText("");
                                        }

                                    }

                                    @Override
                                    public void onFailure(Call<Gsd> call, Throwable t) {
                                        Toast.makeText(getApplicationContext(),"Değerlendirme gönderme başarısız!",Toast.LENGTH_LONG).show();
                                        Log.d("gsd gonderme failure",t.getMessage());

                                    }
                                });
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"Uyku ve yemek içeriklerini girin!",Toast.LENGTH_LONG).show();
                                Log.d("icerik_bos","icerik bos");
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(getApplicationContext(),"Öğrenci seçimi yapın!",Toast.LENGTH_LONG).show();
                    Log.d("ogrenci_secim","ogrenci seçimi yapın");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
