package com.wip.hockey.viewModel.factory;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.wip.hockey.repository.CategoryRepository;
import com.wip.hockey.repository.PositionRepository;
import com.wip.hockey.viewModel.CategoryViewModel;
import com.wip.hockey.viewModel.PositionViewModel;

public class PositionViewModelFactory implements ViewModelProvider.Factory {

    private final PositionRepository positionRepository;

    public PositionViewModelFactory(PositionRepository positionRepository){
        this.positionRepository = positionRepository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(PositionViewModel.class)) {
            return (T) new PositionViewModel(positionRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
