package com.clubbox.clubbox.network;

import com.clubbox.clubbox.model.User;

import java.util.List;
import java.util.Map;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by cdsm16 on 05/10/2016.
 */

public interface ScorerREST {
    public static final String ENDPOINT = "http://10.0.2.2:8180/webservice/";

    @GET("/club/{idClub}/scorers")
    Call<Map<Integer,User>> listScorerByClub(@Path("idClub") Integer idClub);
    @GET("/match/{idMatch}/scorers")
    Call<List<User>> listScorerByMatch(@Path("idMatch") Integer idMatch);
}