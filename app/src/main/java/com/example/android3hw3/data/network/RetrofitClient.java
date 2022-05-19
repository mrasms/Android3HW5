package com.example.android3hw3.data.network;

import com.example.android3hw3.data.network.apiservices.CharacterApiService;
import com.example.android3hw3.data.network.apiservices.EpisodeApiService;
import com.example.android3hw3.data.network.apiservices.LocationApiService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {


    private OkHttpClient okHttpClient = new OkHttpClient()
            .newBuilder()
            .addInterceptor(interceptor())
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build();

    private HttpLoggingInterceptor interceptor (){
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }
    private Retrofit retrofit = new Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://rickandmortyapi.com/")
            .addConverterFactory(GsonConverterFactory.create()).build();

    public CharacterApiService provideCharacterApiService() {
        return retrofit.create(CharacterApiService.class);
    }

    public EpisodeApiService provideEpisodeApiService() {
        return retrofit.create(EpisodeApiService.class);
    }

    public LocationApiService provideLocationApiService() {
        return retrofit.create(LocationApiService.class);
    }
}
