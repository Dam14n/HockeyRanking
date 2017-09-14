package com.wip.hockey.room.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.wip.hockey.model.Category;
import com.wip.hockey.model.Position;

import java.util.List;

@Dao
public interface PositionDao {

    @Query("SELECT * FROM position WHERE categoryId = (:categoryId) ORDER BY rank")
    LiveData<List<Position>> getPositions(int categoryId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertAll(List<Position> positions);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insert(Position position);

    @Delete
    void delete(Position position);

    @Query("SELECT * FROM position WHERE categoryId = (:categoryId) ORDER BY rank")
    List<Position> getPositionsNow(int categoryId);
}
