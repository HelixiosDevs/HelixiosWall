package com.helixios.helixioswall.database;

import android.content.Context;

import com.helixios.helixioswall.model.Photo;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
//Database Class
//holds the database and serves as the main access point for the underlying connection to your app's persisted data.
@Database(entities = {Photo.class},version = 2)
public abstract class HelixDatabase extends RoomDatabase {

    public abstract FavouriteDao favDao();
    private static volatile HelixDatabase INSTANCE;

    public static HelixDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (HelixDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext() ,
                            HelixDatabase.class, "favourites-data").fallbackToDestructiveMigration()
                            .build();

                }
            }
        }
        return INSTANCE;
    }
}
