package com.example.android3hw3.data.database;

import android.content.Context;

import androidx.room.Room;

import com.example.android3hw3.data.database.daos.CharacterDao;
import com.example.android3hw3.data.database.daos.EpisodeDao;
import com.example.android3hw3.data.database.daos.LocationDao;

public class RoomClient {

    public AppDatabase provideRoom(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "app_database")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }

    public CharacterDao provideCharacterDao(AppDatabase appDatabase) {
        return appDatabase.characterDao();
    }

    public EpisodeDao provideEpisodeDao(AppDatabase appDatabase) {
        return appDatabase.episodeDao();

    } public LocationDao provideLocationDao(AppDatabase appDatabase) {
        return appDatabase.locationDao();
    }

}
