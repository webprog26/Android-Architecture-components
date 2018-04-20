package com.example.webprog26.androidarchitecturecomponents;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by webprog26 on 20.04.18.
 */


@Database(entities = {BorrowModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase{

    private static AppDatabase instance;

    private static final String DB_NAME = "borrow_db";

    public static AppDatabase getInstance(final Context context) {
        if (instance == null) {
            instance = getAppDatabase(context);
        }
        return instance;
    }

    private static AppDatabase getAppDatabase(final Context context) {
        return Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class,
                DB_NAME).build();
    }

    public abstract BorrowModelDao itemAndPersonModel();
}
