package com.wip.hockey.dagger;


import com.wip.hockey.repository.PositionRepository;
import com.wip.hockey.viewModel.factory.PositionViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
class TablePositionFragmentModule {

    @Provides
    PositionViewModelFactory providePositionViewModelFactory(PositionRepository repository){
        return new PositionViewModelFactory(repository);
    }
}
