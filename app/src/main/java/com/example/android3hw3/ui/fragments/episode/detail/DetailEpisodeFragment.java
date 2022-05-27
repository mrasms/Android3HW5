package com.example.android3hw3.ui.fragments.episode.detail;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android3hw3.databinding.FragmentDetailEpisodeBinding;
import com.example.android3hw3.models.EpisodeModel;
import com.example.android3hw3.ui.fragments.episode.EpisodeViewModel;

public class DetailEpisodeFragment extends Fragment {
    private FragmentDetailEpisodeBinding binding;
    private EpisodeViewModel episodeViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailEpisodeBinding.inflate(inflater, container, false);
        episodeViewModel = new ViewModelProvider(requireActivity()).get(EpisodeViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupData();
    }

    private void setupData() {
        int args = DetailEpisodeFragmentArgs.fromBundle(getArguments()).getPosition();
        episodeViewModel.fetchEpisodeId(args).observe(getViewLifecycleOwner(), new Observer<EpisodeModel>() {
            @Override
            public void onChanged(EpisodeModel episodeModel) {
                binding.tvDetailEpisodeName.setText(episodeModel.getName());
                binding.tvDetailEpisodeAirDate.setText(episodeModel.getAirDate());
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}