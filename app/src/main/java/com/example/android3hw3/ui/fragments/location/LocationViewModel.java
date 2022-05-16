package com.example.android3hw3.ui.fragments.location;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android3hw3.App;
import com.example.android3hw3.models.EpisodeModel;
import com.example.android3hw3.models.LocationModel;
import com.example.android3hw3.models.RickAndMortyResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationViewModel extends ViewModel {

    public MutableLiveData<RickAndMortyResponse<LocationModel>> mutableLiveData = new MutableLiveData<>();

    public void getList() {
        App.locationApiService.fetchLocation().enqueue(new Callback<RickAndMortyResponse<LocationModel>>() {
            @Override
            public void onResponse(@NonNull Call<RickAndMortyResponse<LocationModel>> call, @NonNull Response<RickAndMortyResponse<LocationModel>> response) {
                mutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<RickAndMortyResponse<LocationModel>> call, @NonNull Throwable t) {
                mutableLiveData.setValue(null);
            }
        });
    }
}
