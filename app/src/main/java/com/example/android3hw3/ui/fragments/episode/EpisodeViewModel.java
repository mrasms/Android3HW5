package com.example.android3hw3.ui.fragments.episode;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android3hw3.App;
import com.example.android3hw3.models.EpisodeModel;
import com.example.android3hw3.models.RickAndMortyResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EpisodeViewModel extends ViewModel {

    public MutableLiveData<RickAndMortyResponse<EpisodeModel>> mutableLiveData = new MutableLiveData<>();

    public void getList() {
        App.episodeApiService.fetchEpisodes().enqueue(new Callback<RickAndMortyResponse<EpisodeModel>>() {
            @Override
            public void onResponse(@NonNull Call<RickAndMortyResponse<EpisodeModel>> call, @NonNull Response<RickAndMortyResponse<EpisodeModel>> response) {
                mutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<RickAndMortyResponse<EpisodeModel>> call, @NonNull Throwable t) {
                mutableLiveData.setValue(null);
            }
        });
    }

}
