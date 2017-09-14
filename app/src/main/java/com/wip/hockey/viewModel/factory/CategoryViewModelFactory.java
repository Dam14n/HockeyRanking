package com.wip.hockey.viewModel.factory;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.wip.hockey.repository.CategoryRepository;
import com.wip.hockey.viewModel.CategoryViewModel;

public class CategoryViewModelFactory implements ViewModelProvider.Factory {

    private final CategoryRepository categoryRepository;

    public CategoryViewModelFactory(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(CategoryViewModel.class)) {
            return (T) new CategoryViewModel(categoryRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
