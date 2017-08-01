package com.wip.hockey.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.wip.hockey.model.Division;
import com.wip.hockey.model.SubDivision;
import com.wip.hockey.repository.Repository;

import java.util.List;

/**
 * Created by djorda on 27/07/2017.
 */

public class SubDivisionViewModel extends ViewModel {

    private final LiveData<List<SubDivision>> subDivisionLiveData;
    private Division division;

    public SubDivisionViewModel() {
        subDivisionLiveData = Repository.getInstance().getSubDivisions(division);
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public LiveData<List<SubDivision>> getSubDivisionListObservable(){
        return subDivisionLiveData;
    }
}
