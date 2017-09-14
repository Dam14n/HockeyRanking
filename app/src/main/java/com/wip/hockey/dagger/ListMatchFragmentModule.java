package com.wip.hockey.dagger;

import com.wip.hockey.repository.MatchRepository;
import com.wip.hockey.viewModel.factory.MatchViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class ListMatchFragmentModule {

    @Provides
    MatchViewModelFactory provideMatchViewModelFactory(MatchRepository repository){
        return new MatchViewModelFactory(repository);
    }
}
