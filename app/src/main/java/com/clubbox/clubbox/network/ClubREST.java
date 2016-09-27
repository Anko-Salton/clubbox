package com.clubbox.clubbox.network;

import com.clubbox.clubbox.model.Club;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by cdsm16 on 08/06/2016.
 */
public interface ClubREST {
    public static final String ENDPOINT = "http://10.0.2.2:8180/webservice/";

    @GET("club/{id}")
    Call<Club> getClubById(@Path("id") Integer id);
}
