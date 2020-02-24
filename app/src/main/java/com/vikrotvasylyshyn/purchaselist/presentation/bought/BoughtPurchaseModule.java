package com.vikrotvasylyshyn.purchaselist.presentation.bought;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BoughtPurchaseModule {

    @ContributesAndroidInjector
    abstract BoughtPurchasesFragment contributeBoughtPurchasesFragment();

    @Binds
    abstract BoughtPurchasesContract.Presenter providesBoughtPurchasesPresenter(BoughtPurchasesPresenter presenter);
}
