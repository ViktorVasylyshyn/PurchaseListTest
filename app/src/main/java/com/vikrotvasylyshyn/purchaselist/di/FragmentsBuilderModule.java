package com.vikrotvasylyshyn.purchaselist.di;

import com.vikrotvasylyshyn.purchaselist.presentation.add.AddPurchaseFragment;
import com.vikrotvasylyshyn.purchaselist.presentation.add.AddPurchaseFragmentModule;
import com.vikrotvasylyshyn.purchaselist.presentation.bought.BoughtPurchasesFragment;
import com.vikrotvasylyshyn.purchaselist.presentation.bought.BoughtPurchasesFragmentModule;
import com.vikrotvasylyshyn.purchaselist.presentation.purchases.PurchasesFragment;
import com.vikrotvasylyshyn.purchaselist.presentation.purchases.PurchasesFragmentModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentsBuilderModule {

    @ContributesAndroidInjector(modules = PurchasesFragmentModule.class)
    abstract PurchasesFragment contributePurchaseFragment();

    @ContributesAndroidInjector(modules = BoughtPurchasesFragmentModule.class)
    abstract BoughtPurchasesFragment contributeBoughtPurchasesFragment();

    @ContributesAndroidInjector(modules = {AddPurchaseFragmentModule.class})
    abstract AddPurchaseFragment contributeAddPurchasesFragment();
}
