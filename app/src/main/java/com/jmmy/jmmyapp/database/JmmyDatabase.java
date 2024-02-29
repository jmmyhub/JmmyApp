package com.jmmy.jmmyapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {DataEntity.class}, version = 1,exportSchema = false)
public abstract class JmmyDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "jmmy_db.db";
    private static JmmyDatabase databaseInstance;

    public static synchronized JmmyDatabase getInstance(Context context) {
        if(databaseInstance == null) {
            databaseInstance = Room
                .databaseBuilder(context.getApplicationContext(), JmmyDatabase.class, DATABASE_NAME)
                .build();
        }
        return databaseInstance;
    }

    public abstract DataDao dataDao();

}
