package com.example.android3hw3.ui.fragments.character;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android3hw3.App;
import com.example.android3hw3.data.repositories.CharacterRepository;
import com.example.android3hw3.models.CharacterModel;
import com.example.android3hw3.models.RickAndMortyResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharacterViewModel extends ViewModel {

    private CharacterRepository characterRepository = new CharacterRepository();

    public int characterPage = 1;

    public MutableLiveData<RickAndMortyResponse<CharacterModel>> getList() {
        return characterRepository.getList(characterPage);
    }

}
