package com.wip.hockey.viewModel.factory;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.wip.hockey.repository.DivisionRepository;
import com.wip.hockey.viewModel.DivisionViewModel;

public class DivisionViewModelFactory implements ViewModelProvider.Factory {

    private final DivisionRepository divisionRepository;

    public DivisionViewModelFactory(DivisionRepository divisionRepository){
        this.divisionRepository = divisionRepository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(DivisionViewModel.class)) {
            return (T) new DivisionViewModel(divisionRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
