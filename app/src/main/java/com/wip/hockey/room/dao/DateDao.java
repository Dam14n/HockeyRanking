package com.wip.hockey.room.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.wip.hockey.model.Date;

import java.util.List;

@Dao
public interface DateDao {

    @Query("SELECT * FROM date WHERE categoryId = (:categoryId) ORDER BY dateNumber ASC")
    LiveData<List<Date>> getDates(int categoryId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertAll(List<Date> Dates);

    @Insert
    Long insert(Date Date);

    @Delete
    void delete(Date Date);

    @Query("SELECT * FROM date WHERE categoryId = (:categoryId) ORDER BY dateNumber ASC")
    List<Date> getDatesNow(int categoryId);
}
