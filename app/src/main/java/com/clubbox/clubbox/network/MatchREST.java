package com.clubbox.clubbox.network;

import com.clubbox.clubbox.model.Club;
import com.clubbox.clubbox.model.Match;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by cdsm16 on 09/06/2016.
 */
public interface MatchREST {
    public static final String ENDPOINT = "http://92.222.72.89:8080/";

    @GET("match/{id}")
    Call<Match> getMatchById(@Path("id") Integer id);

    @GET("club/{id}/lastMatch")
    Call<Match> getLastMatchByClubId(@Path("id") Integer id);
    @GET("club/{id}/allMatch")
    Call<Match> getAllMatchByClubId(@Path("id") Integer id);
    @GET("club/{id}/nextMatch")
    Call<Match> getNextMatchByClubId(@Path("id") Integer id);

}
