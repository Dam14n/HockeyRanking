package com.wip.hockey.viewModel.factory;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.wip.hockey.repository.DateRepository;
import com.wip.hockey.viewModel.DateViewModel;

public class DateViewModelFactory implements ViewModelProvider.Factory {

    private final DateRepository dateRepository;

    public DateViewModelFactory(DateRepository dateRepository){
        this.dateRepository = dateRepository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(DateViewModel.class)) {
            return (T) new DateViewModel(dateRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
