package com.example.android3hw3.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.android3hw3.data.database.daos.CharacterDao;
import com.example.android3hw3.data.database.daos.EpisodeDao;
import com.example.android3hw3.data.database.daos.LocationDao;
import com.example.android3hw3.models.CharacterModel;
import com.example.android3hw3.models.EpisodeModel;
import com.example.android3hw3.models.LocationModel;

@Database(entities = {CharacterModel.class, EpisodeModel.class, LocationModel.class},version = 2)
abstract class AppDatabase extends RoomDatabase {
    public abstract CharacterDao characterDao();
    public abstract EpisodeDao episodeDao();
    public abstract LocationDao locationDao();
}
