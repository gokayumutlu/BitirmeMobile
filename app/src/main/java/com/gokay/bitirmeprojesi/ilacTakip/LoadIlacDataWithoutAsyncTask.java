package com.gokay.bitirmeprojesi.ilacTakip;

import android.util.Log;

import com.gokay.bitirmeprojesi.Api;
import com.gokay.bitirmeprojesi.ilacTakip.Ilac;
import com.gokay.bitirmeprojesi.ilacTakip.ListAdapt;
import com.gokay.bitirmeprojesi.ilacTakip.LoadDataInterface;
import com.gokay.bitirmeprojesi.ilacTakip.ilacData;
import com.gokay.bitirmeprojesi.m.URL;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoadIlacDataWithoutAsyncTask{

    private String g_email;
    private final ListAdapt mAdapt;

    public LoadIlacDataWithoutAsyncTask(ListAdapt adapt){
        mAdapt=adapt;
    }


    public void loadData(String email,final LoadDataInterface callback){
        Log.d("when2","loadData");
        g_email=email;
        URL url=new URL();
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(url.getUrl1())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api=retrofit.create(Api.class);
        Call<ilacData> call=api.ilacTakipG3(g_email);
        call.enqueue(new Callback<ilacData>() {
            @Override
            public void onResponse(Call<ilacData> call, Response<ilacData> response) {

                if(!response.isSuccessful()){
                    Log.d("error data load res: ", response.message());
                    Log.d("when3","onresponsenotsuccessful");

                }
                else{
                    Log.d("succ data load res: ",response.message());
                    Log.d("when3","onresponsesuccessful");

                    ArrayList<Ilac> ilacarray=response.body().ilacData;
                    for(int i=0;i<ilacarray.size();i++){
                        Log.d("ilac_list","ogrenci:"+ilacarray.get(i).getOgrenci()+"\n ilac_adi:"+ilacarray.get(i).getIlacAdi()+"\n adet:"+ilacarray.get(i).getDoz()+"\n aciklama:"+ilacarray.get(i).getDesc());
                    }

                    callback.callList(ilacarray);
                }

            }

            @Override
            public void onFailure(Call<ilacData> call, Throwable t) {
                Log.d("data load failure",t.getMessage());
                Log.d("when3","onfailure");
            }
        });
    }



}
