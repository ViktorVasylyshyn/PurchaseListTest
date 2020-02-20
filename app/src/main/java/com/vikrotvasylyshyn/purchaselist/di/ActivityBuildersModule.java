package com.vikrotvasylyshyn.purchaselist.di;

import com.vikrotvasylyshyn.purchaselist.presentation.PurchaseActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = {FragmentsBuilderModule.class, MainModule.class})
    abstract PurchaseActivity contributeMainActivity();

}
