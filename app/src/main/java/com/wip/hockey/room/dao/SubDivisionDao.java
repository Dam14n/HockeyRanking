package com.wip.hockey.room.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.wip.hockey.model.SubDivision;

import java.util.List;

@Dao
public interface SubDivisionDao {

    @Query("SELECT * FROM subdivision WHERE divisionId = (:divisionId) ORDER BY name")
    LiveData<List<SubDivision>> getSubDivisions(int divisionId);

    @Query("SELECT * FROM subdivision WHERE divisionId = (:divisionId) ORDER BY name")
    List<SubDivision> getSubDivisionsNow(int divisionId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertAll(List<SubDivision> subDivisions);

    @Insert
    Long insert(SubDivision subDivision);

    @Delete
    void delete(SubDivision subDivision);

}
