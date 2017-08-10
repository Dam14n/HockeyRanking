package com.wip.hockey.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.wip.hockey.model.Category;
import com.wip.hockey.model.SubDivision;
import com.wip.hockey.repository.Repository;

import java.util.List;

public class CategoryViewModel extends ViewModel {

    private LiveData<List<Category>> subDivisionLiveData;
    private MutableLiveData<SubDivision> subDivision = new MutableLiveData<SubDivision>();

    public CategoryViewModel() {
        SubDivision subDivision = this.subDivision.getValue() == null ? new SubDivision() : this.subDivision.getValue();
        subDivisionLiveData = Repository.getInstance().getCategories(subDivision);
    }

    public void setSubDivision(SubDivision subDivision) {
        this.subDivision.setValue(subDivision);
        subDivisionLiveData = Repository.getInstance().getCategories(this.subDivision.getValue());
    }

    public LiveData<List<Category>> getCategoryListObservable(){
        return subDivisionLiveData;
    }
}
