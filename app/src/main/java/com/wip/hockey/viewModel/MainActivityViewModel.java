package com.wip.hockey.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

import com.wip.hockey.model.User;
import com.wip.hockey.room.RoomFactory;
import com.wip.hockey.room.database.AppDataBase;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.internal.operators.completable.CompletableFromAction;

/**
 * Created by djorda on 22/08/2017.
 */

public class MainActivityViewModel extends AndroidViewModel {

    private final AppDataBase db;


    public MainActivityViewModel(Application application) {
        super(application);
        this.db =  RoomFactory.getAdapter(application.getApplicationContext());
    }

    public Completable updateUserName(User user) {
        return new CompletableFromAction(() -> db.userDao().insertUser(user));
    }

    public Flowable<List<User>> getAll(){
        return db.userDao().getAll();
    }

    public Flowable<User> findById(String id){
        return db.userDao().findById(id);
    }
}
