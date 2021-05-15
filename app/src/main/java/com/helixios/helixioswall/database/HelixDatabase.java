package com.helixios.helixioswall.database;

import com.helixios.helixioswall.model.Photo;
import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Photo.class},version = 1)
public abstract class HelixDatabase extends RoomDatabase {
    public abstract FavouriteDao favDao();
}
