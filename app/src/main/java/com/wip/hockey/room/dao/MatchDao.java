package com.wip.hockey.room.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.wip.hockey.model.Match;

import java.util.List;

@Dao
public interface MatchDao {

    @Query("SELECT * FROM matches WHERE dateId = (:dateId)")
    LiveData<List<Match>> getMatchs(int dateId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertAll(List<Match> matches);

    @Insert
    Long insert(Match Match);

    @Delete
    void delete(Match Match);

    @Query("SELECT * FROM matches WHERE dateId = (:dateId)")
    List<Match> getMatchsNow(int dateId);
}
