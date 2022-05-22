package com.example.android3hw3.ui.fragments.location;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android3hw3.App;
import com.example.android3hw3.data.repositories.EpisodeRepository;
import com.example.android3hw3.data.repositories.LocationRepository;
import com.example.android3hw3.models.EpisodeModel;
import com.example.android3hw3.models.LocationModel;
import com.example.android3hw3.models.RickAndMortyResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationViewModel extends ViewModel {

    private LocationRepository locationRepository = new LocationRepository();
    public int locationPage = 1;

    public MutableLiveData<RickAndMortyResponse<LocationModel>> getList() {
        return locationRepository.getList(locationPage);
    }


}
