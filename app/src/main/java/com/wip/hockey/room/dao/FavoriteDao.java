package com.wip.hockey.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;

import com.wip.hockey.fragment.ViewType;
import com.wip.hockey.model.Favorite;
import com.wip.hockey.room.converter.ViewTypeConverter;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
@TypeConverters(ViewTypeConverter.class)
public interface FavoriteDao {


    @Query("SELECT * FROM favorite WHERE userId = (:userId)")
    Flowable<List<Favorite>> getFavoritesByUserId(int userId);

    @Insert
    List<Long> insertAll(Favorite... favorites);

    @Insert
    Long insert(Favorite favorite);

    @Delete
    void delete(Favorite favorite);

    @Query("SELECT * FROM favorite WHERE categoryId = (:categoryId)")
    Single<Favorite> getFavoriteByCategoryId(int categoryId);

    @Query("SELECT * FROM favorite WHERE userId = (:userId) AND favoriteType = (:favoriteType)")
    Flowable<List<Favorite>> getFixturesFavoritesByUserId(int userId, ViewType favoriteType);

    @Query("SELECT * FROM favorite WHERE userId = (:userId) AND favoriteType = (:favoriteType)")
    Flowable<List<Favorite>> getPositionsFavoritesByUserId(int userId, ViewType favoriteType);

    @Query("SELECT * FROM favorite WHERE categoryId = (:categoryId) AND favoriteType = (:type)")
    Flowable<Favorite> getFavoriteByCategoryIdAndType(int categoryId, ViewType type);
}
