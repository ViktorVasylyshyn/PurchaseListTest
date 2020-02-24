package com.vikrotvasylyshyn.purchaselist.di;

import com.vikrotvasylyshyn.purchaselist.presentation.PurchaseActivity;
import com.vikrotvasylyshyn.purchaselist.presentation.addpurchase.AddPurchaseModule;
import com.vikrotvasylyshyn.purchaselist.presentation.bought.BoughtPurchaseModule;
import com.vikrotvasylyshyn.purchaselist.presentation.purchases.PurchaseModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class ContributeActivityModule {

    @ContributesAndroidInjector(modules = {PurchaseModule.class, BoughtPurchaseModule.class, AddPurchaseModule.class})
    abstract PurchaseActivity contributeMainActivity();

}
