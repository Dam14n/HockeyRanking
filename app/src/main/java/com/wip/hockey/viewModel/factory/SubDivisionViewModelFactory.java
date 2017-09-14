package com.wip.hockey.viewModel.factory;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.wip.hockey.repository.SubDivisionRepository;
import com.wip.hockey.viewModel.SubDivisionViewModel;

public class SubDivisionViewModelFactory implements ViewModelProvider.Factory {

    private final SubDivisionRepository subDivisionRepository;

    public SubDivisionViewModelFactory(SubDivisionRepository subDivisionRepository){
        this.subDivisionRepository = subDivisionRepository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(SubDivisionViewModel.class)) {
            return (T) new SubDivisionViewModel(subDivisionRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
