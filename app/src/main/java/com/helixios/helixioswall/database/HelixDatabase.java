package com.helixios.helixioswall.database;

import android.content.Context;

import com.helixios.helixioswall.model.Photo;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
//Database Class
//holds the database and serves as the main access point for the underlying connection to your app's persisted data.
@Database(entities = {Photo.class},version = 3)
public abstract class HelixDatabase extends RoomDatabase {

    public abstract FavouriteDao favDao();
    private static volatile HelixDatabase INSTANCE;

    //v3 stores the Flickr size URLs so favourites keep pointing at accessible sizes
    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase db) {
            for (String column : new String[]{"url_z", "url_c", "url_l", "url_h", "url_k"}) {
                db.execSQL("ALTER TABLE photo_table ADD COLUMN " + column + " TEXT");
            }
        }
    };

    public static HelixDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (HelixDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext() ,
                            HelixDatabase.class, "favourites-data").addMigrations(MIGRATION_2_3)
                            .fallbackToDestructiveMigration()
                            .build();

                }
            }
        }
        return INSTANCE;
    }
}
