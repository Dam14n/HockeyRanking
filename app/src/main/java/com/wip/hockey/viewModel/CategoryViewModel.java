package com.wip.hockey.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.wip.hockey.model.Category;
import com.wip.hockey.networking.mock.Status;
import com.wip.hockey.repository.CategoryRepository;

import java.util.List;

public class CategoryViewModel extends ViewModel{

    private CategoryRepository categoryRepository;
    private MutableLiveData<Integer> subdivision = new MutableLiveData<>();
    private LiveData<List<Category>> categories =
            Transformations.switchMap( subdivision , (subDivisionId) ->
                    categoryRepository.getCategories(subDivisionId));


    public CategoryViewModel(CategoryRepository repository) {
        this.categoryRepository = repository;
    }

    public void init(int subDivisionId){
        if (this.subdivision.getValue() != null){
            return;
        }
        subdivision.setValue(subDivisionId);
    }

    public LiveData<List<Category>> getCategories(){
        return categories;
    }

    public void updateCategories(int subDivisionId) {
        categoryRepository.updateCategories(subDivisionId);
    }

    public LiveData<Status> getUpdateStatus() {
        return categoryRepository.getUpdateStatus();
    }

}
