package com.example.myapplication2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface Api {
    String BASE_URL="http://wxs.ign.fr/choisirgeoportail/itineraire/rest/";
    @GET
    Call<info> getData(@Url String url);
}
