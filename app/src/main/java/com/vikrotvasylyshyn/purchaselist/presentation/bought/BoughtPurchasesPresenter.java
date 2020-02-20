package com.vikrotvasylyshyn.purchaselist.presentation.bought;

import com.vikrotvasylyshyn.purchaselist.data.PurchasesRepository;

import javax.inject.Inject;

public class BoughtPurchasesPresenter implements BoughtPurchasesContract.Presenter {

    private BoughtPurchasesContract.View view;
    private BoughtPurchasesContract.Repository repository;

    @Inject
    public BoughtPurchasesPresenter(PurchasesRepository repository) {
        this.repository = repository;

    }

    @Override
    public void takeView(BoughtPurchasesContract.View view) {
        this.view = view;
//        view.showBoughtPurchasesList(repository.fetchBoughtPurchasesList());
    }

    @Override
    public void dropView() {
        view = null;
    }

    @Override
    public void onDetached() {
        view = null;
        //unsubscribe rx
    }
}
