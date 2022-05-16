package com.example.android3hw3.models;

import com.google.gson.annotations.SerializedName;

public class EpisodeModel {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("air_date")
    private String airDate;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAirDate() {
        return airDate;
    }
}
