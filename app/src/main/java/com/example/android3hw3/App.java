package com.example.android3hw3;

import android.app.Application;

import com.example.android3hw3.data.database.RoomClient;
import com.example.android3hw3.data.database.daos.CharacterDao;
import com.example.android3hw3.data.database.daos.EpisodeDao;
import com.example.android3hw3.data.database.daos.LocationDao;
import com.example.android3hw3.data.network.RetrofitClient;
import com.example.android3hw3.data.network.apiservices.CharacterApiService;
import com.example.android3hw3.data.network.apiservices.EpisodeApiService;
import com.example.android3hw3.data.network.apiservices.LocationApiService;

public class App extends Application {

    public static CharacterApiService characterApiService;
    public static EpisodeApiService episodeApiService;
    public static LocationApiService locationApiService;
    public static CharacterDao characterDao;
    public static EpisodeDao episodeDao;
    public static LocationDao locationDao;
    private RoomClient roomClient;
    private RetrofitClient retrofitClient;

    @Override
    public void onCreate() {
        super.onCreate();
        setupRetrofitClient();
        setupRoomClient();
    }

    private void setupRetrofitClient() {
        retrofitClient = new RetrofitClient();
        characterApiService = retrofitClient.provideCharacterApiService();
        episodeApiService = retrofitClient.provideEpisodeApiService();
        locationApiService = retrofitClient.provideLocationApiService();
    }

    private void setupRoomClient() {
        roomClient = new RoomClient();
        characterDao = roomClient.provideCharacterDao(roomClient.provideRoom(this));
        episodeDao = roomClient.provideEpisodeDao(roomClient.provideRoom(this));
        locationDao = roomClient.provideLocationDao(roomClient.provideRoom(this));
    }
}
