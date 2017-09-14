package com.wip.hockey.dagger;

import com.wip.hockey.app.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BuildersModule {
    @ContributesAndroidInjector(modules = FragmentBuildersProvider.class)
    abstract MainActivity contributeMainActivity();
}
