package com.gokay.bitirmeprojesi.duyuru;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gokay.bitirmeprojesi.Api;
import com.gokay.bitirmeprojesi.R;
import com.gokay.bitirmeprojesi.m.URL;
import com.google.firebase.auth.FirebaseAuth;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class SinifDFragment extends Fragment {

    private View msdView;
    private EditText icerikEt;
    private Button sendButton;
    private String icerikText;

    private String current_email;
    private FirebaseAuth mAuth;

    public SinifDFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        msdView=inflater.inflate(R.layout.fragment_sinif_d, container, false);

        icerikEt=msdView.findViewById(R.id.dSinifGonderEt);
        sendButton=msdView.findViewById(R.id.dSinifGonderB);

        mAuth=FirebaseAuth.getInstance();
        current_email=mAuth.getCurrentUser().getEmail();

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                icerikText=icerikEt.getText().toString();

                submit();
            }
        });


        // Inflate the layout for this fragment
        return msdView;
    }



    public void submit(){
        URL url=new URL();
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(url.getUrl1())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api=retrofit.create(Api.class);
        Call<Duyuru> call=api.duyuruESinif(current_email,icerikText);
        call.enqueue(new Callback<Duyuru>() {
            @Override
            public void onResponse(Call<Duyuru> call, Response<Duyuru> response) {
                if(!response.isSuccessful()){
                    //Toast.makeText(getApplicationContext(),"başarısızzz",Toast.LENGTH_LONG).show();
                    Log.d("duyuru gonder basarisiz", response.message());

                }
                else{
                    //Toast.makeText(getApplicationContext(),"başarılııı",Toast.LENGTH_LONG).show();
                    Log.d("duyuru gonder basarili","başarılı");
                }

            }

            @Override
            public void onFailure(Call<Duyuru> call, Throwable t) {
                //Toast.makeText(getApplicationContext(),"errorrrr",Toast.LENGTH_LONG).show();
                Log.d("duyuru gonderme failure",t.getMessage());

            }
        });

    }

}
