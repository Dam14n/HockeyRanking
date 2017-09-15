package com.wip.hockey.dagger;

import com.wip.hockey.repository.FavoriteRepository;
import com.wip.hockey.viewModel.factory.FavoriteViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class ListFavoriteFragmentModule {

    @Provides
    FavoriteViewModelFactory provideFavoriteViewModelFactory(FavoriteRepository repository){
        return new FavoriteViewModelFactory(repository);
    }
}
