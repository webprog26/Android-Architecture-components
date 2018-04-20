package com.example.webprog26.androidarchitecturecomponents;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;

import java.util.List;

/**
 * Created by webprog26 on 20.04.18.
 */

@Dao
@TypeConverters(DateConverter.class)
public interface BorrowModelDao {

    @Query("select * from BorrowModel")
    LiveData<List<BorrowModel>> getAllBorrowModelItems();

    @Query("select * from BorrowModel where id = :id")
    BorrowModel getItemById(final String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addBorrow(final BorrowModel borrowModel);

    @Delete
    void deleteBorrow(final BorrowModel borrowModel);
}
