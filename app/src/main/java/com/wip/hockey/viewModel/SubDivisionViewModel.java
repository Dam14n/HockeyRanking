package com.wip.hockey.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.wip.hockey.model.SubDivision;
import com.wip.hockey.networking.Status;
import com.wip.hockey.repository.SubDivisionRepository;

import java.util.List;

public class SubDivisionViewModel extends ViewModel{


    private SubDivisionRepository subDivisionRepository;
    private MutableLiveData<Integer> division = new MutableLiveData<>();
    private LiveData<List<SubDivision>> subDivisions =
            Transformations.switchMap( division , (divisionId) ->
                    subDivisionRepository.getSubDivisions(divisionId));

    public SubDivisionViewModel(SubDivisionRepository subDivisionRepository) {
       this.subDivisionRepository = subDivisionRepository;
    }

    public void init(int divisionId){
        if (this.division.getValue() != null){
            return;
        }
        division.setValue(divisionId);
    }

    public LiveData<List<SubDivision>> getSubDivisions(){
        return subDivisions;
    }

    public void updateSubDivisions(int divisionId) {
        subDivisionRepository.updateSubDivisions(divisionId);
    }

    public LiveData<Status> getUpdateStatus() {
        return subDivisionRepository.getUpdateStatus();
    }

}
