package com.wip.hockey.room.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.wip.hockey.model.Team;

import java.util.List;

@Dao
public interface TeamDao {

    @Query("SELECT * FROM team WHERE matchId = (:dateId)")
    LiveData<List<Team>> getTeams(int dateId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertAll(List<Team> Teames);

    @Insert
    Long insert(Team Team);

    @Delete
    void delete(Team Team);

}
