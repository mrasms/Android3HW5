package com.example.android3hw3.data.repositories;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.android3hw3.App;
import com.example.android3hw3.models.CharacterModel;
import com.example.android3hw3.models.EpisodeModel;
import com.example.android3hw3.models.RickAndMortyResponse;

import java.util.ArrayList;

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
                    App.episodeDao.insertAll(response.body().getResults());
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

    public ArrayList<EpisodeModel> getEpisode(){
        ArrayList<EpisodeModel> list = new ArrayList<>();
        list.addAll(App.episodeDao.getAll());
        return list;
    }

    public MutableLiveData<EpisodeModel> fetchEpisodeId(int id) {
        MutableLiveData<EpisodeModel> mutableLiveDataID = new MutableLiveData<>();
        App.episodeApiService.fetchEpisode(id).enqueue(new Callback<EpisodeModel>() {
            @Override
            public void onResponse(Call<EpisodeModel> call, Response<EpisodeModel> response) {
                if (response.body() != null) {
                    mutableLiveDataID.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<EpisodeModel> call, Throwable t) {
                mutableLiveDataID.setValue(null);
            }
        });
        return mutableLiveDataID;
    }
}

