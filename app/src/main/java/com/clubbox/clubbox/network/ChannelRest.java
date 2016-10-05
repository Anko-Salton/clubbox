package com.clubbox.clubbox.network;

import com.clubbox.clubbox.model.Channel;
import com.clubbox.clubbox.model.Message;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by cdsm16 on 04/10/2016.
 */

public interface ChannelREST {
    public static final String ENDPOINT = "http://92.222.72.89:8080/";

    @GET("/club/{idClub}/channels/{idChannel}/messages")
    Call<List<Message>> getAllMessageFromChannel(@Path("idClub") Integer id, @Path("idChannel") Integer idChannel);
    @GET("/club/{idClub}/channels")
    Call<List<Channel>> getAllChannelFromClub(@Path("idClub") Integer id);
}
