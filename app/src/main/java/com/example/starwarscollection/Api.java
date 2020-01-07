package com.example.starwarscollection;

import java.util.ArrayList;

import retrofit2.http.GET;
import retrofit2.Call;

public interface Api {

    String BASE_URL = "https://swapi.co/api/";

    @GET("films")
    Call<FilmQuery> getQuery();
}
