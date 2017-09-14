package com.wip.hockey.room.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.wip.hockey.model.Category;
import com.wip.hockey.model.Date;
import com.wip.hockey.model.Division;
import com.wip.hockey.model.Favorite;
import com.wip.hockey.model.Match;
import com.wip.hockey.model.Position;
import com.wip.hockey.model.SubDivision;
import com.wip.hockey.model.User;
import com.wip.hockey.room.converter.DateConverter;
import com.wip.hockey.room.converter.ViewTypeConverter;
import com.wip.hockey.room.dao.CategoryDao;
import com.wip.hockey.room.dao.DateDao;
import com.wip.hockey.room.dao.DivisionDao;
import com.wip.hockey.room.dao.FavoriteDao;
import com.wip.hockey.room.dao.MatchDao;
import com.wip.hockey.room.dao.PositionDao;
import com.wip.hockey.room.dao.SubDivisionDao;
import com.wip.hockey.room.dao.UserDao;

@Database(entities = {User.class, Favorite.class, Division.class, SubDivision.class, Category.class, Date.class, Match.class, Position.class}, version = 1)
@TypeConverters({ViewTypeConverter.class, DateConverter.class})
public abstract class AppDataBase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract FavoriteDao favoriteDao();
    public abstract DivisionDao divisionDao();
    public abstract SubDivisionDao subDivisionDao();
    public abstract CategoryDao categoryDao();
    public abstract DateDao dateDao();
    public abstract MatchDao matchDao();
    public abstract PositionDao positionDao();

}
