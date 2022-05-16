package com.example.android3hw3.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RickAndMortyResponse<T> {

    @SerializedName("results")
    private ArrayList<T> results;

    @SerializedName("info")
    private InfoModel infoModel;

    @SerializedName("episode")
    private EpisodeModel episodeModel;

    @SerializedName("location")
    private LocationModel locationModel;

    public ArrayList<T> getResults() {
        return results;
    }

    public InfoModel getInfoModel() {
        return infoModel;
    }

    public EpisodeModel getEpisodeModel() {
        return episodeModel;
    }

    public LocationModel getLocationModel() {
        return locationModel;
    }
}
