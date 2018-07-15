package com.parnekov.sasha.weathertwo.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.parnekov.sasha.weathertwo.model.WeatherModel;

import java.util.List;

/** Class for work with SQLite Database, Room Library   */
@Database(entities = {WeatherModel.class}, version = 4)
public abstract class MyDataBase extends RoomDatabase {

    private final static String DATABASE_NAME = "weather.db";
    public abstract WeatherDao weatherDao();


    // create static instance for create DB
    private static MyDataBase createDB(Context context) {
        return (MyDataBase) Room.databaseBuilder(context, MyDataBase.class, MyDataBase.DATABASE_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }

    // create static instance for insert data to DB
    public static void insertRoom(Context context, List<WeatherModel> weathers) {
        createDB(context).weatherDao().insertAll(weathers);
    }

    // create static instance for get List<WeatherModel> from DB
    public static List<WeatherModel> getRoom(Context context) {
        return createDB(context).weatherDao().getAll();
    }

    // create static instance for delete all information in DB
    public static void deleteAll(Context context) {
        createDB(context).weatherDao().deleteAll();
    }
}
