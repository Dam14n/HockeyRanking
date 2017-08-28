package com.wip.hockey.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.wip.hockey.model.User;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    Flowable<List<User>> getAll();

    @Query("SELECT * FROM user WHERE uid == :id")
    Flowable<User> findById(String id);

    @Insert
    void insertUser(User user);

    @Delete
    void delete(User user);
}
