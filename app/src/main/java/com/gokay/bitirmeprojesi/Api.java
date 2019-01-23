package com.gokay.bitirmeprojesi;

import com.gokay.bitirmeprojesi.duyuru.Duyuru;
import com.gokay.bitirmeprojesi.duyuru.Veli;
import com.gokay.bitirmeprojesi.duyuru.VeliData;
import com.gokay.bitirmeprojesi.ilacTakip.Ilac;
import com.gokay.bitirmeprojesi.ilacTakip.ilacData;
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


    @FormUrlEncoded
    @POST("ilactakipekle")
    Call<Ilac> ilacTakipE(@Field("email") String email,
                          @Field("ilacAdi") String ilacAdi,
                          @Field("doz") String doz,
                          @Field("desc") String desc);


    @GET("ilactakipgetir")
    Call<Ilac> ilacTakipG(@Query("email") String email);



    @GET("ilactakipgetir")
    Call<ilacData> ilacTakipG3(@Query("email") String email);


    @FormUrlEncoded
    @POST("duyuruesinif")
    Call<Duyuru> duyuruESinif(@Field("ogretmen_email") String email,
                              @Field("icerik") String icerik);


    @GET("sinifvgetir")
    Call<VeliData> sinifVGetir(@Query("email") String email);


    @FormUrlEncoded
    @POST("duyuruekullanici")
    Call<Duyuru> duyuruEKullanici(@Field("ogretmen_email") String oemail,
                                  @Field("veli_email") String vemail,
                                  @Field("icerik") String icerik);




}
