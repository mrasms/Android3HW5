package com.example.android3hw3.ui.fragments.location;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android3hw3.data.repositories.LocationRepository;
import com.example.android3hw3.models.LocationModel;
import com.example.android3hw3.models.RickAndMortyResponse;

import java.util.ArrayList;


public class LocationViewModel extends ViewModel {

    private LocationRepository locationRepository = new LocationRepository();
    public int locationPage = 1;

    public MutableLiveData<RickAndMortyResponse<LocationModel>> getList() {
        return locationRepository.getList(locationPage);
    }

    public MutableLiveData<LocationModel> fetchlocationId(int id) {
        return locationRepository.fetchEpisodeId(id);
    }

    public ArrayList<LocationModel> getLocation() {
        return locationRepository.getLocation();
    }



}
