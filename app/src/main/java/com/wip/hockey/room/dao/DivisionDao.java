package com.wip.hockey.room.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.wip.hockey.model.Division;

import java.util.List;

@Dao
public interface DivisionDao {


    @Query("SELECT * FROM division ORDER BY name")
    LiveData<List<Division>> getDivisions();

    @Query("SELECT * FROM division ORDER BY name")
    List<Division> getDivisionsNow();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertAll(List<Division> divisions);

    @Insert
    Long insert(Division division);

    @Delete
    void delete(Division division);

}
