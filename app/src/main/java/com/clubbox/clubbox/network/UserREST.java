package com.clubbox.clubbox.network;

import com.clubbox.clubbox.model.User;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;


/*
 * Created by cdsm16 on 06/06/2016.
 */
/*
 * Utilisation de Retrofit pour les services.
 */
public interface UserREST {
    public static final String ENDPOINT = "http://92.222.72.89:8080/";

    @FormUrlEncoded
    @POST("login")
    Call<User> getAuth(@Field("email") String username, @Field("password")String password);

    //Probleme avec les dates ? A corriger
    //TODO

    @GET("user/{id}")
    Call<User> getUserById(@Path("id") Integer id);
}
