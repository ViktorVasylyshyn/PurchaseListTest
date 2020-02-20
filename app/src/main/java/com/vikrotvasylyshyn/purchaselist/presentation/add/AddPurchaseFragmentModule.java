package com.vikrotvasylyshyn.purchaselist.presentation.add;

import com.vikrotvasylyshyn.purchaselist.data.PurchasesRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class AddPurchaseFragmentModule {

    @Binds
    abstract AddPurchaseContract.Repository providesAddPurchaseRepository(PurchasesRepository repository);

    @Binds
    abstract AddPurchaseContract.Presenter providesAddPurchasePresenter(AddPurchasePresenter presenter);
}
