package com.wip.hockey.dagger;

import com.wip.hockey.app.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by djorda on 11/09/2017.
 */

@Module
public abstract class BuildersModule {
    @ContributesAndroidInjector(modules = FragmentBuildersProvider.class)
    abstract MainActivity contributeMainActivity();
}
