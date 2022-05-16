package com.example.android3hw3.data.network.apiservices;

import com.example.android3hw3.models.CharacterModel;
import com.example.android3hw3.models.RickAndMortyResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CharacterApiService {

    @GET("api/character")
    Call<RickAndMortyResponse<CharacterModel>> fetchCharacters();
}
