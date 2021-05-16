package com.helixios.helixioswall.database;

import com.helixios.helixioswall.model.Photo;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface FavouriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Photo photo);

    @Query("SELECT * FROM photo_table ORDER BY time DESC")
    List<Photo> getFavourite();

    @Query("DELETE FROM photo_table where id LIKE :photo_id")
    void removeFavourite(String photo_id);

    @Query("SELECT * FROM photo_table WHERE id=:photo_id ")
    Photo getfaveStatus(String photo_id);
}
