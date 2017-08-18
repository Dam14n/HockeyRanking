package com.wip.hockey.viewModel;

import android.arch.lifecycle.ViewModel;

import com.wip.hockey.model.Division;
import com.wip.hockey.repository.Repository;

import java.util.List;

import io.reactivex.Observable;


public class DivisionViewModel extends ViewModel{

    private Repository repository;

    public DivisionViewModel() {
        repository = Repository.getInstance();
    }

    public Observable<List<Division>> getDivisions(){
        return repository.getDivisions();
    }

}
