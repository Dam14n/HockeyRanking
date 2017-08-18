package com.wip.hockey.viewModel;

import android.arch.lifecycle.ViewModel;

import com.wip.hockey.model.SubDivision;
import com.wip.hockey.repository.Repository;

import java.util.List;

import io.reactivex.ObservableSource;

public class SubDivisionViewModel extends ViewModel{

    private Repository repository;
    private int divisionId;

    public SubDivisionViewModel() {
        repository = Repository.getInstance();
    }

    public ObservableSource<List<SubDivision>> getSubDivisions() {
      return repository.getSubDivisionsByDivision(this.divisionId);
    }

    public void setDivisionId(int divisionId)
    {
        this.divisionId = divisionId;
    }
}
