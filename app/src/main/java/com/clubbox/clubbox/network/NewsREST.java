package com.clubbox.clubbox.network;

import com.clubbox.clubbox.model.News;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Part;
import retrofit.http.Path;

/**
 * Created by THIMOTHEE on 28/09/2016.
 */

public interface NewsREST {
    public static final String ENDPOINT = "http://92.222.72.89:8080/";

    @GET("club/{id}/news")
    Call<List<News>> allNewsFromClub(@Path("id") Integer id);
    @GET("club/{id}/news/{dateadd}")
    Call<News> allNewsFromClub(@Path("id") Integer id,@Path("dateadd") String dateadd);
}
