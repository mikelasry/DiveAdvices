package com.example.diveadvices.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SiteAdviceDao {
    @Query("select * from SiteAdvice")
    LiveData<List<SiteAdvice>> getAll();

    @Query("SELECT * FROM SiteAdvice WHERE id=:sa_id")
    LiveData<List<SiteAdvice>> getAdviceById(String sa_id);

    @Query("SELECT * FROM SiteAdvice WHERE owner=:sa_owner")
    LiveData<List<SiteAdvice>> getAllByOwner(String sa_owner);

    //if id already exist replace it.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(SiteAdvice... advices);

    @Delete
    void delete(SiteAdvice advices);
}

