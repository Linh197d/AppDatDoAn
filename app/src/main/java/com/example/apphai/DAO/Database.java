package com.example.apphai.DAO;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.apphai.model.MonAn;


@androidx.room.Database(entities = {MonAn.class}, version = 1)
public abstract class Database extends RoomDatabase {
    private static final String DATABASE_NAME = "monan.db";
    private static Database instance;

    public static synchronized Database getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), Database.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract DataGD_DAO dataGD_dao();
}
