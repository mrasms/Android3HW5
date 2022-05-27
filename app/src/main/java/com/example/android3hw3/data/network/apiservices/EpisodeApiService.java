package com.example.android3hw3.data.network.apiservices;

import com.example.android3hw3.models.EpisodeModel;
import com.example.android3hw3.models.RickAndMortyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface EpisodeApiService {
    @GET("api/episode")
    Call<RickAndMortyResponse<EpisodeModel>> fetchEpisodes(
            @Query("page") int page);

    @GET("api/episode/{id}")
    Call<EpisodeModel> fetchEpisode(
            @Path("id") int id);
}
