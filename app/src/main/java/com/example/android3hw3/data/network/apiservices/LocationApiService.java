package com.example.android3hw3.data.network.apiservices;

import com.example.android3hw3.models.EpisodeModel;
import com.example.android3hw3.models.LocationModel;
import com.example.android3hw3.models.RickAndMortyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LocationApiService {
    @GET("api/location")
    Call<RickAndMortyResponse<LocationModel>> fetchLocation(
            @Query("page") int page
    );
}
