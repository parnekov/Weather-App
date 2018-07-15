package com.parnekov.sasha.weathertwo.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.parnekov.sasha.weathertwo.model.WeatherModel;

import java.util.List;

/** Interface for work with Database*/
@Dao
public interface WeatherDao {

    @Insert
    void insertAll(List<WeatherModel> products);

    @Query("SELECT * FROM weather")
    List<WeatherModel> getAll();

    @Query("DELETE FROM weather")
    void deleteAll();

}
