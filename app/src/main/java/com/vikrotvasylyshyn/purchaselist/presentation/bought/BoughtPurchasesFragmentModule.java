package com.vikrotvasylyshyn.purchaselist.presentation.bought;

import com.vikrotvasylyshyn.purchaselist.data.PurchasesRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class BoughtPurchasesFragmentModule {

    @Binds
    abstract BoughtPurchasesContract.Repository bindsBoughtPurchasesRepository(PurchasesRepository repository);
}
