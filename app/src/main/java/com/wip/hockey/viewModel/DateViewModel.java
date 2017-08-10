package com.wip.hockey.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.wip.hockey.model.Category;
import com.wip.hockey.model.Date;
import com.wip.hockey.repository.Repository;

import java.util.List;

public class DateViewModel extends ViewModel {

    private LiveData<List<Date>> dateLiveData;
    private MutableLiveData<Category> category = new MutableLiveData<Category>();

    public DateViewModel() {
        Category category = this.category.getValue() == null ? new Category() : this.category.getValue();
        dateLiveData = Repository.getInstance().getDates(category);
    }

    public void setCategory(Category category) {
        this.category.setValue(category);
        dateLiveData = Repository.getInstance().getDates(this.category.getValue());
    }

    public LiveData<List<Date>> getDateListObservable(){
        return dateLiveData;
    }
}
