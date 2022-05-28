package com.example.android3hw3.data.database.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.android3hw3.models.EpisodeModel;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface EpisodeDao {

    @Query("SELECT * FROM episodeModel")
    List<EpisodeModel> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(ArrayList<EpisodeModel> episodeModels);
}
