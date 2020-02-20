package com.vikrotvasylyshyn.purchaselist.presentation.purchases;

import android.util.Log;

import com.vikrotvasylyshyn.purchaselist.data.PurchasesRepository;
import com.vikrotvasylyshyn.purchaselist.data.model.Purchase;
import com.vikrotvasylyshyn.purchaselist.utill.Constants;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableSingleObserver;

public class PurchasesPresenter implements PurchasesListContract.Presenter {
    private PurchasesRepository repository;
    private PurchasesListContract.View view;
    private DisposableSingleObserver disposable;

    @Inject
    public PurchasesPresenter(PurchasesRepository repository) {
        this.repository = repository;
    }

    @Override
    public void fetchPurchasesList() {
        view.showProgress(true);
        disposable = repository.fetchPurchaseList().subscribeWith(new DisposableSingleObserver<List<Purchase>>() {

            @Override
            public void onSuccess(List<Purchase> list) {
                view.showPurchases(list);
                view.showProgress(false);
//                Log.d(Constants.TAG,"list size is - " + list.size());
            }

            @Override
            public void onError(Throwable e) {
                view.showProgress(false);
                view.showError(e.getMessage());
            }
        });
    }

    @Override
    public void markAsBought(Purchase purchase) {
        repository.addPurchase(purchase);
    }

    @Override
    public void takeView(PurchasesListContract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {
        view = null;
    }

    @Override
    public void onDetached() {
        disposable.dispose();
        view = null;
    }
}
