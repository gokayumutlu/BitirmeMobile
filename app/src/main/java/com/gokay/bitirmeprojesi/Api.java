package com.gokay.bitirmeprojesi;

import com.gokay.bitirmeprojesi.m.Kullanici;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

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


    @GET("login")
    Call<Kullanici> girisYap(@Query("email") String email,
                             @Query("sifre") String sifre);

    @GET("emailtakas")
    Call<Kullanici> emailTakas(@Query("email") String email);

}
