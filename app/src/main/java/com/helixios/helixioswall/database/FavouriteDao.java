package com.helixios.helixioswall.database;

import com.helixios.helixioswall.model.Photo;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface FavouriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Photo photo);

    @Query("SELECT * FROM photo ORDER BY time DESC")
    List<Photo> getFavourite();

    @Query("DELETE FROM photo where id= :photo_id")
    void removeFavourite(String photo_id);

    @Query("SELECT * FROM photo WHERE id=:photo_id ")
    Photo getfaveStatus(String photo_id);
}
