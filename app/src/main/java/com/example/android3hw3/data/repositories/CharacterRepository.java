package com.example.android3hw3.data.repositories;

import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.android3hw3.App;
import com.example.android3hw3.models.CharacterModel;
import com.example.android3hw3.models.InfoModel;
import com.example.android3hw3.models.RickAndMortyResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharacterRepository {

    public MutableLiveData<RickAndMortyResponse<CharacterModel>> getList(int page) {
        MutableLiveData<RickAndMortyResponse<CharacterModel>> mutableLiveData = new MutableLiveData<>();
        App.characterApiService.fetchCharacters(page).enqueue(new Callback<RickAndMortyResponse<CharacterModel>>() {
            @Override
            public void onResponse(Call<RickAndMortyResponse<CharacterModel>> call,
                                   Response<RickAndMortyResponse<CharacterModel>> response) {
                if (response.body() != null) {
                    App.characterDao.insertAll(response.body().getResults());
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<RickAndMortyResponse<CharacterModel>> call, Throwable t) {
                mutableLiveData.setValue(null);
            }
        });
        return mutableLiveData;
    }

    public ArrayList<CharacterModel> getCharacters(){
        ArrayList<CharacterModel> list = new ArrayList<>();
        list.addAll(App.characterDao.getAll());
        return list;
    }

    public MutableLiveData<CharacterModel> fetchCharacterId(int id) {
        MutableLiveData<CharacterModel> mutableLiveDataID = new MutableLiveData<>();
        App.characterApiService.fetchCharacter(id).enqueue(new Callback<CharacterModel>() {
            @Override
            public void onResponse(Call<CharacterModel> call, Response<CharacterModel> response) {
                if (response.body() != null) {
                    mutableLiveDataID.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<CharacterModel> call, Throwable t) {
                mutableLiveDataID.setValue(null);
            }
        });
        return mutableLiveDataID;
    }
}
