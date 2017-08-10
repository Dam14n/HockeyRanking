package com.wip.hockey.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.wip.hockey.model.Division;
import com.wip.hockey.repository.Repository;

import java.util.List;

/**
 * Created by djorda on 27/07/2017.
 */

public class DivisionViewModel extends ViewModel {

    private final LiveData<List<Division>> divisionLiveData;

    public DivisionViewModel() {
        divisionLiveData = Repository.getInstance().getDivisions();
    }

    public LiveData<List<Division>> getDivisionListObservable(){
        return divisionLiveData;
    }
}
