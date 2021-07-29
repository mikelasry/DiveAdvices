package com.example.diveadvices.model;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {SiteAdvice.class}, version = 1)

abstract class AppLocalDbRepository extends RoomDatabase {
    public abstract SiteAdviceDao siteAdviceDao();
}

public class AppLocalDB{
    final static public AppLocalDbRepository db =
            Room.databaseBuilder(MyApplication.context,
                    AppLocalDbRepository.class,
                    "DiveAdvice.db")
                    .fallbackToDestructiveMigration()
                    .build();
}
