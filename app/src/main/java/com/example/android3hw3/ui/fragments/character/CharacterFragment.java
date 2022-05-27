package com.example.android3hw3.ui.fragments.character;

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
import com.example.android3hw3.databinding.FragmentCharacterBinding;
import com.example.android3hw3.models.CharacterModel;
import com.example.android3hw3.models.RickAndMortyResponse;
import com.example.android3hw3.ui.adapters.CharacterAdapter;
import com.example.android3hw3.ui.adapters.clickers.OnCharacterItemClick;

import java.util.ArrayList;

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
    }

    @Override
    protected void setupListener() {
        binding.characterRecView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    visibleCount = linearLayoutManager.getChildCount();
                    totalCount = linearLayoutManager.getItemCount();
                    postVisible = linearLayoutManager.findFirstVisibleItemPosition();
                    if ((visibleCount + postVisible) >= totalCount) {
                        if (characterViewModel.characterPage != totalCount && (characterViewModel.characterPage < totalCount)) {
                            characterViewModel.characterPage++;
                            if (!loading && (characterViewModel.characterPage < totalCount)) {
                                fetchCharacters();
                            }
                        }
                    }
                }
            }
        });

        characterAdapter.setOnItemClick(new OnCharacterItemClick() {
            @Override
            public void onItemClick(CharacterModel model) {
                Navigation
                        .findNavController(requireView())
                        .navigate(CharacterFragmentDirections
                                .actionCharacterFragmentToDetailCharacterFragment()
                                .setPosition(model.getId()));
            }
        });
    }

    private void fetchCharacters() {
        if (isNetwork()) {
            characterViewModel.getList().observe(getViewLifecycleOwner(), new Observer<RickAndMortyResponse<CharacterModel>>() {
                @Override
                public void onChanged(RickAndMortyResponse<CharacterModel> characterModelRickAndMortyResponse) {
                    if (!loading) {
                        ArrayList<CharacterModel> list = new ArrayList<>(characterAdapter.getCurrentList());
                        list.addAll(characterModelRickAndMortyResponse.getResults());
                        characterAdapter.submitList(list);
                        if (list != characterAdapter.getCurrentList()) {
                            characterAdapter.submitList(list);
                        }
                    }
                }
            });
        } else
            characterAdapter.submitList(characterViewModel.getCharacters());
    }

    private boolean isNetwork() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        characterViewModel.getList().observe(getViewLifecycleOwner(), new Observer<RickAndMortyResponse<CharacterModel>>() {
            @Override
            public void onChanged(RickAndMortyResponse<CharacterModel> characterModelRickAndMortyResponse) {
                if (loading) {
                    ArrayList<CharacterModel> list = new ArrayList<>(characterAdapter.getCurrentList());
                    list.addAll(characterModelRickAndMortyResponse.getResults());
                    characterAdapter.submitList(list);
                    loading = false;
                }
            }
        });
    }
}
