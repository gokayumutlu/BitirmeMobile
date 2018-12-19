package com.gokay.bitirmeprojesi;

import com.gokay.bitirmeprojesi.M.Kullanici;
import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface Api {
    @GET("users")
    Call<List<Kullanici>> tumKullanicilar();

    /*  örnek query
    @GET("users")
    Call<List<Kullanici>> tumKullanicilarvequery(@Query("userId") int userId,
                                                 @Query("_sort") String sort,
                                                 @Query("_order") String order);
        örnek query
    @GET("users")
    Call<List<Kullanici>> kullanicilar(@QueryMap Map<String, String> params);
    */


    @GET("group/users/{kullanici_id}")
    Call<List<Kullanici>> birKullanici(@Path("kullanici_id") int kullanici_id);

    /*
    @Multipart
    @POST("signup")
    Call<Kullanici> kayit(@Part("ad") RequestBody ad,
                          @Part("soyad") RequestBody soyad,
                          @Part("email") RequestBody email,
                          @Part("sifre") RequestBody sifre);

    */


    @FormUrlEncoded
    @POST("signup")
    Call<Kullanici> kayitol(@Field("ad") String ad,
                            @Field("soyad") String soyad,
                            @Field("email") String email,
                            @Field("sifre") String sifre);

    
    @FormUrlEncoded
    @GET("login")
    Call<Kullanici> girisYap(@Field("email") String email,
                             @Field("sifre") String sifre);

}
