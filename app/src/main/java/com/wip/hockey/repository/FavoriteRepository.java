package com.wip.hockey.repository;

import android.arch.lifecycle.LiveData;

import com.wip.hockey.AppExecutors;
import com.wip.hockey.fragment.ViewType;
import com.wip.hockey.model.Favorite;
import com.wip.hockey.room.dao.FavoriteDao;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class FavoriteRepository {

    private final WebService webservice;
    private final FavoriteDao favoriteDao;
    private final AppExecutors executor;

    @Inject
    public FavoriteRepository(WebService webservice, FavoriteDao favoriteDao, AppExecutors executor) {
        this.webservice = webservice;
        this.favoriteDao = favoriteDao;
        this.executor = executor;
    }

    public LiveData<List<Favorite>> getFixturesFavoritesByUserId(int userId){
        return favoriteDao.getFixturesFavoritesByUserId(userId, ViewType.FIXTURE_VIEW);
    }

    public LiveData<List<Favorite>> getPositionsFavoritesByUserId(int userId){
        return favoriteDao.getPositionsFavoritesByUserId(userId, ViewType.POSITIONS_VIEW);
    }

    public void deleteFavorite(Favorite favorite) {
       executor.diskIO().execute(() ->favoriteDao.delete(favorite) );
    }

    public void addFavorite(Favorite favorite) {
      executor.diskIO().execute(() -> favoriteDao.insert(favorite));
    }

    public LiveData<List<Favorite>> getFavoritesByTypeAndUser(ViewType type, int userId) {
        return favoriteDao.getFavoritesByTypeAndUser(type,userId);
    }
}
