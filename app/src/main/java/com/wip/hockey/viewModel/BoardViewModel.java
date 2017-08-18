package com.wip.hockey.viewModel;

import android.arch.lifecycle.ViewModel;

import com.wip.hockey.model.Board;
import com.wip.hockey.repository.Repository;

import java.util.List;

import io.reactivex.Observable;

public class BoardViewModel extends ViewModel{

    private Repository repository;
    private int categoryId;


    public BoardViewModel() {
        repository = Repository.getInstance();
    }


    public Observable<List<Board>> getBoards() {
        return repository.getBoardsByCategory(this.categoryId);
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
