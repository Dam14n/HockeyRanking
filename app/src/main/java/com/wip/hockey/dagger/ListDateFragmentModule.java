package com.wip.hockey.dagger;

import com.wip.hockey.repository.DateRepository;
import com.wip.hockey.viewModel.factory.DateViewModelFactory;

import dagger.Module;
import dagger.Provides;

/**
 * Created by djorda on 11/09/2017.
 */

@Module
public class ListDateFragmentModule {

    @Provides
    DateViewModelFactory provideDateViewModelFactory(DateRepository repository){
        return new DateViewModelFactory(repository);
    }


}
