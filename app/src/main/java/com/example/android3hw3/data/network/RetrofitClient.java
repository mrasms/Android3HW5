package com.example.android3hw3.data.network;

import com.example.android3hw3.data.network.apiservices.CharacterApiService;
import com.example.android3hw3.data.network.apiservices.EpisodeApiService;
import com.example.android3hw3.data.network.apiservices.LocationApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/")
            .addConverterFactory(GsonConverterFactory.create()).build();

    public CharacterApiService provideCharacterApiService() {
        return retrofit.create(CharacterApiService.class);
    }

    public EpisodeApiService provideEpisodeApiService() {
        return retrofit.create(EpisodeApiService.class);
    }

    public LocationApiService provideLocationApiService(){
        return retrofit.create(LocationApiService.class);
    }
}
