package com.wip.hockey.viewModel.factory;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.wip.hockey.repository.FavoriteRepository;
import com.wip.hockey.repository.PositionRepository;
import com.wip.hockey.viewModel.FavoriteViewModel;
import com.wip.hockey.viewModel.PositionViewModel;

public class FavoriteViewModelFactory implements ViewModelProvider.Factory {

    private final FavoriteRepository favoriteRepository;

    public FavoriteViewModelFactory(FavoriteRepository favoriteRepository){
        this.favoriteRepository = favoriteRepository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(FavoriteViewModel.class)) {
            return (T) new FavoriteViewModel(favoriteRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
