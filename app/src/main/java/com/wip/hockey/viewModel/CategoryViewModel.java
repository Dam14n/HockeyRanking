package com.wip.hockey.viewModel;

import android.arch.lifecycle.ViewModel;

import com.wip.hockey.model.Category;
import com.wip.hockey.repository.Repository;

import java.util.List;

import io.reactivex.Observable;

public class CategoryViewModel extends ViewModel{

    private Repository repository;
    private int subDivisionId;


    public CategoryViewModel() {
        repository = Repository.getInstance();
    }

    public Observable<List<Category>> getCategories() {
       return repository.getCategoriesBySubDivision(subDivisionId);
    }

    public void setSubDivisionId(int subDivisionId) {
        this.subDivisionId = subDivisionId;
    }


}
