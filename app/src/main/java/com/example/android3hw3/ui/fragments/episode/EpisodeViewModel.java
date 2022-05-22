package com.example.android3hw3.ui.fragments.episode;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android3hw3.App;
import com.example.android3hw3.data.repositories.CharacterRepository;
import com.example.android3hw3.data.repositories.EpisodeRepository;
import com.example.android3hw3.models.CharacterModel;
import com.example.android3hw3.models.EpisodeModel;
import com.example.android3hw3.models.RickAndMortyResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EpisodeViewModel extends ViewModel {

    private EpisodeRepository episodeRepository = new EpisodeRepository();

    public int episodePage = 1;

    public MutableLiveData<RickAndMortyResponse<EpisodeModel>> getList (){
        return episodeRepository.getList(episodePage);
    }



}
