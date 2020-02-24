package com.vikrotvasylyshyn.purchaselist.presentation.addpurchase;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class AddPurchaseModule {

    @ContributesAndroidInjector
    abstract AddPurchaseFragment contributeAddPurchasesFragment();

    @Binds
    abstract AddPurchaseContract.Presenter providesAddPurchasePresenter(AddPurchasePresenter presenter);
}
