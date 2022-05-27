package com.example.android3hw3.ui.fragments.character.detail;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.android3hw3.R;
import com.example.android3hw3.databinding.FragmentDetailCharacterBinding;
import com.example.android3hw3.models.CharacterModel;
import com.example.android3hw3.ui.fragments.character.CharacterViewModel;


public class DetailCharacterFragment extends Fragment {
    private FragmentDetailCharacterBinding binding;
    private CharacterViewModel characterViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailCharacterBinding.inflate(inflater, container, false);
        characterViewModel = new ViewModelProvider(requireActivity()).get(CharacterViewModel.class);
        return (binding.getRoot());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupData();
    }

    private void setupData() {
        int args = DetailCharacterFragmentArgs.fromBundle(getArguments()).getPosition();
        characterViewModel.fetchCharacterId(args).observe(getViewLifecycleOwner(), new Observer<CharacterModel>() {
            @Override
            public void onChanged(CharacterModel characterModel) {
                Glide.with(binding.characterImage).load(characterModel.getImage()).into(binding.characterImage);
                binding.tvCharacterName.setText(characterModel.getName());
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}