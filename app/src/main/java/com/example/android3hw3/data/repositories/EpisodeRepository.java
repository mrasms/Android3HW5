package com.example.android3hw3.data.repositories;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.android3hw3.App;
import com.example.android3hw3.models.EpisodeModel;
import com.example.android3hw3.models.RickAndMortyResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EpisodeRepository {

    public MutableLiveData<RickAndMortyResponse<EpisodeModel>> getList(int page) {
        MutableLiveData<RickAndMortyResponse<EpisodeModel>> mutableLiveData = new MutableLiveData<>();
        App.episodeApiService.fetchEpisodes(page).enqueue(new Callback<RickAndMortyResponse<EpisodeModel>>() {
            @Override
            public void onResponse(Call<RickAndMortyResponse<EpisodeModel>> call,
                                   Response<RickAndMortyResponse<EpisodeModel>> response) {
                if (response.body() != null) {
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<RickAndMortyResponse<EpisodeModel>> call, @NonNull Throwable t) {
                mutableLiveData.setValue(null);
            }
        });
        return mutableLiveData;
    }
}
