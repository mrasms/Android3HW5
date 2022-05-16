package com.example.android3hw3.ui.fragments.character;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android3hw3.App;
import com.example.android3hw3.models.CharacterModel;
import com.example.android3hw3.models.RickAndMortyResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharacterViewModel extends ViewModel {

    public MutableLiveData<RickAndMortyResponse<CharacterModel>> mutableLiveData = new MutableLiveData<>();

    public void getList() {
        App.characterApiService.fetchCharacters().enqueue(new Callback<RickAndMortyResponse<CharacterModel>>() {
            @Override
            public void onResponse(Call<RickAndMortyResponse<CharacterModel>> call,
                                   Response<RickAndMortyResponse<CharacterModel>> response) {
                mutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<RickAndMortyResponse<CharacterModel>> call, Throwable t) {
                mutableLiveData.setValue(null);
            }
        });
    }
}
