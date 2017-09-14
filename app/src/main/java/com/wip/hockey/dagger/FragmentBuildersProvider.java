package com.wip.hockey.dagger;

import com.wip.hockey.fragment.ListCategoryFragment;
import com.wip.hockey.fragment.ListDateFragment;
import com.wip.hockey.fragment.ListDivisionFragment;
import com.wip.hockey.fragment.ListMatchFragment;
import com.wip.hockey.fragment.ListSubDivisionFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuildersProvider {
    @ContributesAndroidInjector(modules = ListDivisionFragmentModule.class)
    abstract ListDivisionFragment provideListDivisionFragment();

    @ContributesAndroidInjector(modules = ListSubDivisionFragmentModule.class)
    abstract ListSubDivisionFragment provideListSubDivisionFragment();

    @ContributesAndroidInjector(modules = ListCategoryFragmentModule.class)
    abstract ListCategoryFragment provideCategoryFragment();

    @ContributesAndroidInjector(modules = ListDateFragmentModule.class)
    abstract ListDateFragment provideDateFragment();

    @ContributesAndroidInjector(modules = ListMatchFragmentModule.class)
    abstract ListMatchFragment provideMatchFragment();
}
