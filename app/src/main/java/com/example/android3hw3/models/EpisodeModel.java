package com.example.android3hw3.models;

import androidx.room.Entity;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "episodeModel")
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
