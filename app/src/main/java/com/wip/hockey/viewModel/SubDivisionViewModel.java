package com.wip.hockey.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.wip.hockey.model.Division;
import com.wip.hockey.model.SubDivision;
import com.wip.hockey.repository.Repository;

import java.util.List;

public class SubDivisionViewModel extends ViewModel {

    private LiveData<List<SubDivision>> subDivisionLiveData;
    private MutableLiveData<Division> division = new MutableLiveData<Division>();

    public SubDivisionViewModel() {
        Division division = this.division.getValue() == null ? new Division() : this.division.getValue();
        subDivisionLiveData = Repository.getInstance().getSubDivisions(division);
    }

    public void setDivision(Division division) {
        this.division.setValue(division);
        subDivisionLiveData = Repository.getInstance().getSubDivisions(this.division.getValue());
    }

    public LiveData<List<SubDivision>> getSubDivisionListObservable(){
        return subDivisionLiveData;
    }
}
