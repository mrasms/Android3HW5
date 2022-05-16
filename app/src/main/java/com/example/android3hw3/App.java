package com.example.android3hw3;

import android.app.Application;

import com.example.android3hw3.data.network.RetrofitClient;
import com.example.android3hw3.data.network.apiservices.CharacterApiService;
import com.example.android3hw3.data.network.apiservices.EpisodeApiService;
import com.example.android3hw3.data.network.apiservices.LocationApiService;

public class App extends Application {

    public static CharacterApiService characterApiService;
    public static EpisodeApiService episodeApiService;
    public static LocationApiService locationApiService;

    @Override
    public void onCreate() {
        super.onCreate();
        setupRetrofitClient();
    }

    private void setupRetrofitClient() {
        RetrofitClient retrofitClient = new RetrofitClient();
        characterApiService = retrofitClient.provideCharacterApiService();
        episodeApiService = retrofitClient.provideEpisodeApiService();
        locationApiService = retrofitClient.provideLocationApiService();
    }
}
