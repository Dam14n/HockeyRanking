package com.wip.hockey.room.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.wip.hockey.model.Favorite;
import com.wip.hockey.model.User;
import com.wip.hockey.room.dao.FavoriteDao;
import com.wip.hockey.room.dao.UserDao;

@Database(entities = {User.class, Favorite.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract FavoriteDao favoriteDao();
}
