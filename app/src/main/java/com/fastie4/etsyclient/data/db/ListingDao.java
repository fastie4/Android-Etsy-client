package com.fastie4.etsyclient.data.db;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import io.reactivex.Observable;

@Dao
public interface ListingDao {
    @Query("SELECT * FROM listing")
    Observable<List<ListingEntity>> getAll();

    @Insert
    void insert(ListingEntity listing);

    @Delete
    void delete(ListingEntity listing);
}
