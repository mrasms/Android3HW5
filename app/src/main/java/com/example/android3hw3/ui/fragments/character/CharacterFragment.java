package com.example.android3hw3.ui.fragments.character;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android3hw3.base.BaseFragment;
import com.example.android3hw3.databinding.FragmentCharacterBinding;
import com.example.android3hw3.ui.adapters.CharacterAdapter;

public class CharacterFragment extends BaseFragment<FragmentCharacterBinding> {

    private CharacterViewModel characterViewModel;
    private CharacterAdapter characterAdapter = new CharacterAdapter(CharacterAdapter.diffCallBack);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCharacterBinding.inflate(inflater, container, false);
        characterViewModel = new ViewModelProvider(requireActivity()).get(CharacterViewModel.class);
        return binding.getRoot();
    }

    @Override
    protected void setupViews() {
        binding.characterRecView.setAdapter(characterAdapter);
    }

    @Override
    protected void setupRequest() {
        characterViewModel.getList();
    }

    @Override
    protected void setupObserve() {
        characterViewModel.mutableLiveData.observe(getViewLifecycleOwner(), characterModelRickAndMortyResponse -> {
            characterAdapter.submitList(characterModelRickAndMortyResponse.getResults());
        });
    }
}