package com.example.android3hw3.ui.fragments.character;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android3hw3.data.repositories.CharacterRepository;
import com.example.android3hw3.models.CharacterModel;
import com.example.android3hw3.models.RickAndMortyResponse;

import java.util.ArrayList;

public class CharacterViewModel extends ViewModel {
    private final CharacterRepository characterRepository = new CharacterRepository();
    public int characterPage = 1;

    public MutableLiveData<RickAndMortyResponse<CharacterModel>> getList() {
        return characterRepository.getList(characterPage);
    }

    public MutableLiveData<CharacterModel> fetchCharacterId(int id) {
        return characterRepository.fetchCharacterId(id);
    }

    public ArrayList<CharacterModel> getCharacters(){
        return characterRepository.getCharacters();
    }
}
