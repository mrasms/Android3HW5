package com.example.android3hw3.ui.fragments.character;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android3hw3.base.BaseFragment;
import com.example.android3hw3.data.repositories.CharacterRepository;
import com.example.android3hw3.databinding.FragmentCharacterBinding;
import com.example.android3hw3.models.CharacterModel;
import com.example.android3hw3.models.RickAndMortyResponse;
import com.example.android3hw3.ui.adapters.CharacterAdapter;

import java.util.Collections;
import java.util.List;

public class CharacterFragment extends BaseFragment<FragmentCharacterBinding> {

    private CharacterViewModel characterViewModel;
    private CharacterAdapter characterAdapter = new CharacterAdapter(CharacterAdapter.diffCallBack);
    private LinearLayoutManager linearLayoutManager;
    private boolean loading = true;
    private int postVisible, visibleCount, totalCount;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCharacterBinding.inflate(inflater, container, false);
        characterViewModel = new ViewModelProvider(requireActivity()).get(CharacterViewModel.class);
        return binding.getRoot();
    }

    @Override
    protected void setupViews() {
        linearLayoutManager = new LinearLayoutManager(requireContext());
        binding.characterRecView.setLayoutManager(linearLayoutManager);
        binding.characterRecView.setAdapter(characterAdapter);
        binding.characterRecView.onScrolled(0,0);
        binding.characterRecView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    visibleCount = linearLayoutManager.getChildCount();
                    totalCount = linearLayoutManager.getItemCount();
                    postVisible = linearLayoutManager.findFirstVisibleItemPosition();
                    if (loading) {
                        linearLayoutManager.setStackFromEnd(false);

                        if ((visibleCount + postVisible) >= totalCount) {
                            linearLayoutManager.setStackFromEnd(false);

                            loading = false;

                            characterViewModel.characterPage++;
                            characterViewModel.getList().observe(getViewLifecycleOwner(), new Observer<RickAndMortyResponse<CharacterModel>>() {
                                @Override
                                public void onChanged(RickAndMortyResponse<CharacterModel> characterModelRickAndMortyResponse) {
                                    characterAdapter.submitList(characterModelRickAndMortyResponse.getResults());
                                    loading=true;

                                }
                            });
                       }
                    }
                }
            }
        });
        binding.characterRecView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy < 0) {
                    visibleCount = linearLayoutManager.getChildCount();
                    totalCount = linearLayoutManager.getItemCount();
                    postVisible = linearLayoutManager.findLastVisibleItemPosition();
                    if (loading) {
                        linearLayoutManager.setStackFromEnd(true);
                        if ((visibleCount + postVisible) >= totalCount) {
                            linearLayoutManager.setStackFromEnd(true);
                            if (totalCount-visibleCount==postVisible){
                            loading = false;
                            characterViewModel.characterPage--;
                            characterViewModel.getList().observe(getViewLifecycleOwner(), new Observer<RickAndMortyResponse<CharacterModel>>() {

                                @Override
                                public void onChanged(RickAndMortyResponse<CharacterModel> characterModelRickAndMortyResponse) {
                                    characterAdapter.submitList(characterModelRickAndMortyResponse.getResults());
                                    characterAdapter.submitList(characterModelRickAndMortyResponse.getResults());
                                    loading=true;
                                }
                            });}
                        }
                    }
                }
            }
        });
    }

    @Override
    protected void setupRequest() {
        characterViewModel.getList().observe(getViewLifecycleOwner(), new Observer<RickAndMortyResponse<CharacterModel>>() {
            @Override
            public void onChanged(RickAndMortyResponse<CharacterModel> characterModelRickAndMortyResponse) {
                characterAdapter.submitList(characterModelRickAndMortyResponse.getResults());
            }
        });
    }
 }