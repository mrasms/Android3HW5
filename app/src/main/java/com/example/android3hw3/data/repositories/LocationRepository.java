package com.example.android3hw3.data.repositories;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.android3hw3.App;
import com.example.android3hw3.models.EpisodeModel;
import com.example.android3hw3.models.LocationModel;
import com.example.android3hw3.models.RickAndMortyResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationRepository {

    public MutableLiveData<RickAndMortyResponse<LocationModel>> getList(int page) {
        MutableLiveData<RickAndMortyResponse<LocationModel>> mutableLiveData = new MutableLiveData<>();
        App.locationApiService.fetchLocations(page).enqueue(new Callback<RickAndMortyResponse<LocationModel>>() {
            @Override
            public void onResponse(Call<RickAndMortyResponse<LocationModel>> call, @NonNull Response<RickAndMortyResponse<LocationModel>> response) {
                if (response.body() != null) {
                    App.locationDao.insertAll(response.body().getResults());
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<RickAndMortyResponse<LocationModel>> call, @NonNull Throwable t) {
                mutableLiveData.setValue(null);
            }
        });
        return mutableLiveData;
    }

    public ArrayList<LocationModel> getLocation(){
        ArrayList<LocationModel> list = new ArrayList<>();
        list.addAll(App.locationDao.getAll());
        return list;
    }

    public MutableLiveData<LocationModel> fetchEpisodeId(int id) {
        MutableLiveData<LocationModel> mutableLiveDataID = new MutableLiveData<>();
        App.locationApiService.fetchLocation(id).enqueue(new Callback<LocationModel>() {
            @Override
            public void onResponse(Call<LocationModel> call, Response<LocationModel> response) {
                if (response.body() != null) {
                    mutableLiveDataID.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<LocationModel> call, Throwable t) {
                mutableLiveDataID.setValue(null);
            }
        });
        return mutableLiveDataID;
    }

}
