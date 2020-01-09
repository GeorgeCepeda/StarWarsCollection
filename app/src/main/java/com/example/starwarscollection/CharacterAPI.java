package com.example.starwarscollection;

import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface CharacterAPI {
    String BASE_URL = "https://swapi.co/api/";

    @GET
    Call<Character> getPerson(@Url String url);
}
