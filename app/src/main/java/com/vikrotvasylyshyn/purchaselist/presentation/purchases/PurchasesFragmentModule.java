package com.vikrotvasylyshyn.purchaselist.presentation.purchases;

import com.vikrotvasylyshyn.purchaselist.data.PurchasesRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class PurchasesFragmentModule {

    @Binds
    abstract PurchasesListContract.Repository bindsPurchasesListRepository(PurchasesRepository repository);

}
