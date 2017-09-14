package com.wip.hockey.dagger;

import com.wip.hockey.repository.SubDivisionRepository;
import com.wip.hockey.viewModel.factory.SubDivisionViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class ListSubDivisionFragmentModule {

    @Provides
    SubDivisionViewModelFactory provideSubDivisionViewModelFactory(SubDivisionRepository repository){
        return new SubDivisionViewModelFactory(repository);
    }
}
