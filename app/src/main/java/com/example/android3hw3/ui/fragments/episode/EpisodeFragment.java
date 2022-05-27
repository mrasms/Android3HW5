package com.example.android3hw3.ui.fragments.episode;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android3hw3.base.BaseFragment;
import com.example.android3hw3.databinding.FragmentEpisodeBinding;

import com.example.android3hw3.models.EpisodeModel;
import com.example.android3hw3.models.RickAndMortyResponse;
import com.example.android3hw3.ui.adapters.EpisodeAdapter;
import com.example.android3hw3.ui.adapters.clickers.OnEpisodeItemClick;

import java.util.ArrayList;

public class EpisodeFragment extends BaseFragment<FragmentEpisodeBinding> {

    private EpisodeViewModel episodeViewModel;
    private EpisodeAdapter episodeAdapter = new EpisodeAdapter(EpisodeAdapter.diffCallBack);
    private LinearLayoutManager linearLayoutManager;
    private boolean loading = true;
    private int postVisible, visibleCount, totalCount;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEpisodeBinding.inflate(inflater, container, false);
        episodeViewModel = new ViewModelProvider(requireActivity()).get(EpisodeViewModel.class);
        return binding.getRoot();
    }

    @Override
    protected void setupViews() {
        linearLayoutManager = new LinearLayoutManager(requireContext());
        binding.episodeRecView.setLayoutManager(linearLayoutManager);
        binding.episodeRecView.setAdapter(episodeAdapter);
    }

    @Override
    protected void setupListener() {
        binding.episodeRecView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    visibleCount = linearLayoutManager.getChildCount();
                    totalCount = linearLayoutManager.getItemCount();
                    postVisible = linearLayoutManager.findFirstVisibleItemPosition();
                    if ((visibleCount + postVisible) >= totalCount) {
                        if (episodeViewModel.episodePage != totalCount && (episodeViewModel.episodePage < totalCount)) {
                            episodeViewModel.episodePage++;
                            if (!loading && (episodeViewModel.episodePage < totalCount)) {

                                fetchEpisodes();
                            }
                        }
                    }
                }
            }
        });
        episodeAdapter.setOnEpisodeItemClick(new OnEpisodeItemClick() {
            @Override
            public void onItemClick(EpisodeModel model) {
                Navigation.findNavController(requireView())
                        .navigate(EpisodeFragmentDirections.actionEpisodeFragmentToDetailEpisodeFragment().setPosition(model.getId()));
            }
        });
    }

    private void fetchEpisodes() {
        if (isNetwork()) {
            episodeViewModel.getList().observe(getViewLifecycleOwner(), new Observer<RickAndMortyResponse<EpisodeModel>>() {
                @Override
                public void onChanged(RickAndMortyResponse<EpisodeModel> episodeModelRickAndMortyResponse) {
                    if (!loading) {
                        ArrayList<EpisodeModel> list = new ArrayList<>(episodeAdapter.getCurrentList());
                        list.addAll(episodeModelRickAndMortyResponse.getResults());
                        episodeAdapter.submitList(list);
                        if (list != episodeAdapter.getCurrentList()) {
                            episodeAdapter.submitList(list);
                        }
                    }
                }
            });
        } else
            episodeAdapter.submitList(episodeViewModel.getEpisode());

    }

    private boolean isNetwork() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        episodeViewModel.episodePage = 1;
        binding = null;
        loading = true;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isNetwork()) {
            episodeViewModel.getList().observe(getViewLifecycleOwner(), new Observer<RickAndMortyResponse<EpisodeModel>>() {
                @Override
                public void onChanged(RickAndMortyResponse<EpisodeModel> episodeModelRickAndMortyResponse) {
                    if (loading) {
                        ArrayList<EpisodeModel> list = new ArrayList<>(episodeAdapter.getCurrentList());
                        list.addAll(episodeModelRickAndMortyResponse.getResults());
                        episodeAdapter.submitList(list);
                        loading = false;
                    } else episodeAdapter.submitList(episodeViewModel.getEpisode());
                }
            });
        } else episodeAdapter.submitList(episodeViewModel.getEpisode());
    }
}