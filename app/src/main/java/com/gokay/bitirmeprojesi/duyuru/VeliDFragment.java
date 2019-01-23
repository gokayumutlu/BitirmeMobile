package com.gokay.bitirmeprojesi.duyuru;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.gokay.bitirmeprojesi.Api;
import com.gokay.bitirmeprojesi.R;
import com.gokay.bitirmeprojesi.ilacTakip.Ilac;
import com.gokay.bitirmeprojesi.ilacTakip.ilacData;
import com.gokay.bitirmeprojesi.m.URL;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class VeliDFragment extends Fragment implements LoadVeliDataInterface{

    private FirebaseAuth mAuth;
    private String current_email;
    private String g_email;
    private View mvdView;
    private Context context;
    private Button sendButton;
    private EditText icerikText;

    public VeliDFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mvdView=inflater.inflate(R.layout.fragment_veli_d, container, false);
        mAuth=FirebaseAuth.getInstance();
        current_email=mAuth.getCurrentUser().getEmail();

        sendButton=mvdView.findViewById(R.id.dVeliGonderB);
        icerikText=mvdView.findViewById(R.id.dVeliGonderEt);

        velilerGetir(current_email,this);

        // Inflate the layout for this fragment
        return mvdView;
    }



    public void velilerGetir(String email,final LoadVeliDataInterface callback){
        Log.d("veligetir","veligetirbaşı");
        g_email=email;
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
                    for(int i=0;i<veliArray.size();i++){
                        Log.d("arraylist","veli_adi: "+veliArray.get(i).getVeli_adi()+"\n veli_email: "+veliArray.get(i).getVeli_email());
                    }
                    callback.callList(veliArray);

                }

            }

            @Override
            public void onFailure(Call<VeliData> call, Throwable t) {
                Log.d("data load failure",t.getMessage());
                Log.d("when3","veligetir onfailure");
            }
        });
    }

    @Override
    public void callList(ArrayList<Veli> veliList) {
        String[] spinnerArray=new String[veliList.size()];
        final HashMap<Integer,String> spinnerMap=new HashMap<Integer, String>();
        for(int i=0;i<veliList.size();i++){
            spinnerMap.put(i,veliList.get(i).getVeli_email());
            spinnerArray[i]=veliList.get(i).getVeli_adi();
        }
        final Spinner spinner=mvdView.findViewById(R.id.dVeliGonderSpinner);
        ArrayAdapter<String> veliAdapter=new ArrayAdapter<String>(context,R.layout.support_simple_spinner_dropdown_item,spinnerArray);
        veliAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(veliAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                final String secilen_email=spinnerMap.get(spinner.getSelectedItemPosition());
                Log.d("seçilen email",secilen_email);

                if(!secilen_email.isEmpty()){
                    sendButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String icerik=icerikText.getText().toString();
                            if(!icerik.isEmpty()){
                                URL url=new URL();
                                Retrofit retrofit=new Retrofit.Builder()
                                        .baseUrl(url.getUrl1())
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build();
                                Api api=retrofit.create(Api.class);
                                Call<Duyuru> call=api.duyuruEKullanici(current_email,secilen_email,icerik);
                                call.enqueue(new Callback<Duyuru>() {
                                    @Override
                                    public void onResponse(Call<Duyuru> call, Response<Duyuru> response) {
                                        if(!response.isSuccessful()){
                                            Toast.makeText(context,"Duyuru gönderme başarısız!",Toast.LENGTH_LONG).show();
                                            Log.d("duyuru gonder basarisiz", response.message());

                                        }
                                        else{
                                            Toast.makeText(context,"Duyuru gönderme başarılı!",Toast.LENGTH_LONG).show();
                                            Log.d("duyuru gonder basarili","başarılı");
                                        }

                                    }

                                    @Override
                                    public void onFailure(Call<Duyuru> call, Throwable t) {
                                        Toast.makeText(context,"Duyuru gönderme başarısız!",Toast.LENGTH_LONG).show();
                                        Log.d("duyuru gonderme failure",t.getMessage());

                                    }
                                });
                            }
                            else{
                                Log.d("icerikbos","İçerik boş");
                                Toast.makeText(context,"Lütfen duyuru içeriği girin",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
                else {
                    Log.d("emptyseçilenemail","seçilen email boş");
                    Toast.makeText(context,"Lütfen duyuru gönderilecek veliyi seçin",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}
