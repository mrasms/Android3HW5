package com.example.android3hw3.ui.fragments.episode;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android3hw3.base.BaseFragment;
import com.example.android3hw3.databinding.FragmentEpisodeBinding;

import com.example.android3hw3.ui.adapters.EpisodeAdapter;


public class EpisodeFragment extends BaseFragment<FragmentEpisodeBinding> {

    private EpisodeViewModel episodeViewModel;
    private EpisodeAdapter episodeAdapter = new EpisodeAdapter(EpisodeAdapter.diffCallBack);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEpisodeBinding.inflate(inflater, container, false);
        episodeViewModel = new ViewModelProvider(requireActivity()).get(EpisodeViewModel.class);
        return (binding.getRoot());
    }

    @Override
    protected void setupViews() {
        super.setupViews();
        binding.characterRecView.setAdapter(episodeAdapter);
    }

    @Override
    protected void setupRequest() {
        super.setupRequest();
        episodeViewModel.getList();
    }

    @Override
    protected void setupObserve() {
        super.setupObserve();
        episodeViewModel.mutableLiveData.observe(getViewLifecycleOwner(), episodeModelRickAndMortyResponse -> {
            episodeAdapter.submitList(episodeModelRickAndMortyResponse.getResults());
        });
    }


}