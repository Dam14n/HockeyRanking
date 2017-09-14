package com.wip.hockey.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.wip.hockey.model.Division;
import com.wip.hockey.networking.Status;
import com.wip.hockey.repository.DivisionRepository;

import java.util.List;

import javax.inject.Inject;


public class DivisionViewModel extends ViewModel{

    DivisionRepository divisionRepository;

    private LiveData<List<Division>> divisions;
    @Inject
    public DivisionViewModel(DivisionRepository repository) {
        this.divisionRepository = repository;
    }

    public void init(){
        if (this.divisions != null){
            return;
        }
        divisions = divisionRepository.getDivisions();
    }

    public LiveData<List<Division>> getDivisions(){
        return divisions;
    }

    public void updateDivisions() {
        divisionRepository.updateDivisions();
    }

    public LiveData<Status> getUpdateStatus() {
        return divisionRepository.getUpdateStatus();
    }
}
