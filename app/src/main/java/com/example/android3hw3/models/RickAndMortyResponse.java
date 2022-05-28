package com.example.android3hw3.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class RickAndMortyResponse<T> {

    @SerializedName("results")
    private ArrayList<T> results;

    public ArrayList<T> getResults() {
        return results;
    }

}
