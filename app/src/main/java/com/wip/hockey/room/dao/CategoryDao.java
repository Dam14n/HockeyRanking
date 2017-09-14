package com.wip.hockey.room.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.wip.hockey.model.Category;

import java.util.List;

@Dao
public interface CategoryDao {

    @Query("SELECT * FROM category WHERE subDivisionId = (:subDivisionId) ORDER BY name")
    LiveData<List<Category>> getCategories(int subDivisionId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertAll(List<Category> categorys);

    @Insert
    Long insert(Category category);

    @Delete
    void delete(Category category);

    @Query("SELECT * FROM category WHERE subDivisionId = (:subDivisionId) ORDER BY name")
    List<Category> getCategoriesNow(int subDivisionId);
}
