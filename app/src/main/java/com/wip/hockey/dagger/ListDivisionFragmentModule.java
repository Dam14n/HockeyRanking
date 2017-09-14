package com.wip.hockey.dagger;

import com.wip.hockey.repository.DivisionRepository;
import com.wip.hockey.viewModel.factory.DivisionViewModelFactory;

import dagger.Module;
import dagger.Provides;

/**
 * Created by djorda on 11/09/2017.
 */

@Module
public class ListDivisionFragmentModule {

    @Provides
    DivisionViewModelFactory provideDivisionViewModelFactory(DivisionRepository repository){
        return new DivisionViewModelFactory(repository);
    }


}
