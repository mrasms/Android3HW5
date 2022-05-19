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

    public int getCount() {
        return count;
    }

    public int getPages() {
        return pages;
    }

    public String getPrev() {
        return prev;
    }

    public String getNext() {
        return next;
    }
}
