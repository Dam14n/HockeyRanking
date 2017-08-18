package com.wip.hockey.viewModel;

import android.arch.lifecycle.ViewModel;

import com.wip.hockey.model.Date;
import com.wip.hockey.repository.Repository;

import java.util.List;

import io.reactivex.Observable;

public class DateViewModel extends ViewModel{

    private Repository repository;
    private int categoryId;

    public DateViewModel() {
        repository = Repository.getInstance();
    }


    public Observable<List<Date>> getDates() {
        return repository.getDatesByCategory(categoryId);
    }


    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }


}
