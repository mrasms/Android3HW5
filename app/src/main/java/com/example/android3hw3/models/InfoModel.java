package com.example.android3hw3.models;

import com.google.gson.annotations.SerializedName;

public class InfoModel {

    @SerializedName("count")
    private int count;

    @SerializedName("pages")
    private int pages;

    @SerializedName("prev")
    private String prev;

    @SerializedName("next")
    private String next;
}
