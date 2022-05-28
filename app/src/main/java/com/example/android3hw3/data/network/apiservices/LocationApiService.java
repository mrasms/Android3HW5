package com.example.android3hw3.data.network.apiservices;

import com.example.android3hw3.models.LocationModel;
import com.example.android3hw3.models.RickAndMortyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface LocationApiService {

    @GET("api/location")
    Call<RickAndMortyResponse<LocationModel>> fetchLocations(
            @Query("page") int page);

    @GET("api/location/{id}")
    Call<LocationModel> fetchLocation(
            @Path("id") int id);
}
