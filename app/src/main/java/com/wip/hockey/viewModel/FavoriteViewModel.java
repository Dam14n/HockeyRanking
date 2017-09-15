package com.wip.hockey.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.wip.hockey.fragment.ViewType;
import com.wip.hockey.model.Category;
import com.wip.hockey.model.Favorite;
import com.wip.hockey.model.User;
import com.wip.hockey.repository.FavoriteRepository;
import com.wip.hockey.room.database.AppDataBase;

import java.util.List;

/**
 * Created by djorda on 22/08/2017.
 */

public class FavoriteViewModel extends ViewModel {

    private final AppDataBase db = null;
    private final FavoriteRepository favoriteRepository;

    public FavoriteViewModel(FavoriteRepository repository) {
        this.favoriteRepository = repository;
    }

    public LiveData<List<Favorite>> getPositionsFavoritesByUserId(int userId) {
        return favoriteRepository.getPositionsFavoritesByUserId(userId);
    }

    public LiveData<List<Favorite>>  getFixturesFavoritesByUserId(int userId) {
        return  favoriteRepository.getFixturesFavoritesByUserId(userId);
    }

    public void deleteFavorite(Favorite favorite) {
        favoriteRepository.deleteFavorite(favorite);
    }

    public void newFavorite(Category category, User user, ViewType type,String subDivisionName) {
        Favorite favorite = new Favorite();
        favorite.setCategoryId(category.getId());
        favorite.setUserId(user.getId());
        favorite.setFavoriteType(type);
        favorite.setSubDivisionName(subDivisionName);
        favorite.setCategory(category);
        favoriteRepository.addFavorite(favorite);
    }

    public LiveData<List<Favorite>> getFavoritesByTypeAndUser(ViewType type, int userId) {
        return favoriteRepository.getFavoritesByTypeAndUser(type,userId);
    }
}
