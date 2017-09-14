package com.wip.hockey.dagger;

import com.wip.hockey.repository.CategoryRepository;
import com.wip.hockey.viewModel.factory.CategoryViewModelFactory;

import dagger.Module;
import dagger.Provides;

/**
 * Created by djorda on 11/09/2017.
 */

@Module
public class ListCategoryFragmentModule {

    @Provides
    CategoryViewModelFactory provideCategoryViewModelFactory(CategoryRepository repository){
        return new CategoryViewModelFactory(repository);
    }


}
