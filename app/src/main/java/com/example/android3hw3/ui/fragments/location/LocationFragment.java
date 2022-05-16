package com.example.android3hw3.ui.fragments.location;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android3hw3.R;
import com.example.android3hw3.base.BaseFragment;
import com.example.android3hw3.databinding.FragmentLocationBinding;
import com.example.android3hw3.ui.adapters.LocationAdapter;


public class LocationFragment extends BaseFragment<FragmentLocationBinding> {

    private LocationAdapter locationAdapter = new LocationAdapter(LocationAdapter.diffCallBack);
    private LocationViewModel locationViewModel = new LocationViewModel();

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLocationBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    protected void setupViews() {
        super.setupViews();
        binding.characterRecView.setAdapter(locationAdapter);
    }

    @Override
    protected void setupRequest() {
        super.setupRequest();
        locationViewModel.getList();
    }

    @Override
    protected void setupObserve() {
        super.setupObserve();
        locationViewModel.mutableLiveData.observe(getViewLifecycleOwner(), locationModelRickAndMortyResponse -> {
            locationAdapter.submitList(locationModelRickAndMortyResponse.getResults());
        });
    }
}