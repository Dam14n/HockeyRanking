package com.wip.hockey.room.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.wip.hockey.fragment.ViewType;
import com.wip.hockey.model.Favorite;

import java.util.List;

@Dao
public interface FavoriteDao {


    @Insert
    List<Long> insertAll(Favorite... favorites);

    @Insert
    Long insert(Favorite favorite);

    @Delete
    void delete(Favorite favorite);

    @Query("SELECT * FROM favorite WHERE userId = (:userId) AND favoriteType = (:favoriteType) ORDER BY subDivisionName ASC")
    LiveData<List<Favorite>> getFixturesFavoritesByUserId(int userId, ViewType favoriteType);

    @Query("SELECT * FROM favorite WHERE userId = (:userId) AND favoriteType = (:favoriteType) ORDER BY subDivisionName ASC")
    LiveData<List<Favorite>> getPositionsFavoritesByUserId(int userId, ViewType favoriteType);

    @Query("SELECT * FROM favorite WHERE favoriteType = (:type) AND userId = (:userId) ORDER BY subDivisionName ASC")
    LiveData<List<Favorite>> getFavoritesByTypeAndUser(ViewType type, int userId);
}
