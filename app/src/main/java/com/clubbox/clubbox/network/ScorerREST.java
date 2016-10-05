package com.clubbox.clubbox.network;

import com.clubbox.clubbox.model.Scorer;
import com.clubbox.clubbox.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/*
 * Created by cdsm16 on 05/10/2016.
 */

public interface ScorerREST {
    public static final String ENDPOINT = "http://92.222.72.89:8080/webservice/";

    @GET("/club/{idClub}/scorers")
    Call<ArrayList<Map<Integer,User>>> listScorerByClub(@Path("idClub") Integer idClub);
    @GET("/match/{idMatch}/scorers")
    Call<List<Scorer>> listScorerByMatch(@Path("idMatch") Integer idMatch);
}

