package com.wip.hockey.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

import com.wip.hockey.fragment.ViewType;
import com.wip.hockey.model.Category;
import com.wip.hockey.model.Favorite;
import com.wip.hockey.room.RoomFactory;
import com.wip.hockey.room.database.AppDataBase;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.internal.operators.completable.CompletableFromAction;

/**
 * Created by djorda on 22/08/2017.
 */

public class FavoriteViewModel extends AndroidViewModel {

    private final AppDataBase db;
   // private final WebService webService;

    public FavoriteViewModel(Application application) {
        super(application);
        this.db =  RoomFactory.getAdapter(application.getApplicationContext());
        //webService = WebService.getInstance();
    }

    public Flowable<List<Favorite>> getFavoritesByUserId(int userId){
        return db.favoriteDao().getFavoritesByUserId(userId);
    }

    public Flowable<List<Favorite>> getFixturesFavoritesByUserId(int userId){
        return db.favoriteDao().getFixturesFavoritesByUserId(userId, ViewType.FIXTURE_VIEW);
    }

    public Flowable<List<Favorite>> getPositionsFavoritesByUserId(int userId){
        return db.favoriteDao().getPositionsFavoritesByUserId(userId, ViewType.POSITIONS_VIEW);
    }

    public Single<Favorite> getFavoriteByCategoryId(int categoryId) {
        return db.favoriteDao().getFavoriteByCategoryId(categoryId);
    }

    public CompletableFromAction deleteFavorite(Favorite favorite) {
        return new CompletableFromAction(() -> db.favoriteDao().delete(favorite));
    }

    public CompletableFromAction addFavorite(Favorite favorite) {
         return new CompletableFromAction(() -> {
             Long id = db.favoriteDao().insert(favorite);
             favorite.setId(id.intValue());
         });
    }

    public Flowable<Favorite> getFavoriteByCategoryIdAndType(int userId, ViewType type) {
        return db.favoriteDao().getFavoriteByCategoryIdAndType(userId,type);
    }

    public Observable<Category> getCategory(int categoryId) {
        return  null;
        // return webService.getCategory(categoryId);
    }
}
