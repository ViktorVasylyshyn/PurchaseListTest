package com.vikrotvasylyshyn.purchaselist.presentation.purchases;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class PurchaseModule {

    @ContributesAndroidInjector
    abstract PurchasesFragment contributePurchaseFragment();

    @Binds
    abstract PurchasesListContract.Presenter providesPurchasesPresenter(PurchasesPresenter presenter);
}
