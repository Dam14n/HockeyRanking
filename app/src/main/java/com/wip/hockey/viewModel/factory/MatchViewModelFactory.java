package com.wip.hockey.viewModel.factory;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.wip.hockey.repository.MatchRepository;
import com.wip.hockey.viewModel.MatchViewModel;

public class MatchViewModelFactory implements ViewModelProvider.Factory {

    private final MatchRepository matchRepository;

    public MatchViewModelFactory(MatchRepository matchRepository){
        this.matchRepository = matchRepository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MatchViewModel.class)) {
            return (T) new MatchViewModel(matchRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
