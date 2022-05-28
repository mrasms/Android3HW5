package com.example.android3hw3.ui.fragments.episode;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android3hw3.data.repositories.EpisodeRepository;
import com.example.android3hw3.models.EpisodeModel;
import com.example.android3hw3.models.RickAndMortyResponse;

import java.util.ArrayList;

public class EpisodeViewModel extends ViewModel {

    private EpisodeRepository episodeRepository = new EpisodeRepository();
    public int episodePage = 1;

    public MutableLiveData<RickAndMortyResponse<EpisodeModel>> getList() {
        return episodeRepository.getList(episodePage);
    }

    public MutableLiveData<EpisodeModel> fetchEpisodeId(int id) {
        return episodeRepository.fetchEpisodeId(id);
    }

    public ArrayList<EpisodeModel> getEpisode() {
        return episodeRepository.getEpisode();
    }

}
