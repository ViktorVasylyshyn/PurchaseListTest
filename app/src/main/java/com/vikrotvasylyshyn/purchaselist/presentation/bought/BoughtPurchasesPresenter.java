package com.vikrotvasylyshyn.purchaselist.presentation.bought;

import com.vikrotvasylyshyn.purchaselist.data.PurchasesRepository;
import com.vikrotvasylyshyn.purchaselist.data.model.Purchase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;

public class BoughtPurchasesPresenter implements BoughtPurchasesContract.Presenter {

    private BoughtPurchasesContract.View view;
    private BoughtPurchasesContract.Repository repository;
    private CompositeDisposable disposable = new CompositeDisposable(); //TODO DAGGER

    @Inject
    public BoughtPurchasesPresenter(PurchasesRepository repository) {
        this.repository = repository;

    }

    @Override
    public void fetchBoughtPurchasesList() {
        view.showProgress(true);
        disposable.add(repository.fetchBoughtPurchasesList().subscribeWith(new DisposableSingleObserver<List<Purchase>>(){

            @Override
            public void onSuccess(List<Purchase> list) {
                view.showBoughtPurchases(list);
                view.showProgress(true);
            }

            @Override
            public void onError(Throwable e) {
                view.showProgress(true);
                view.showError(e.getMessage());
            }
        }));

    }

    @Override
    public void deletePurchase(Purchase purchase) {
        disposable.add(repository.deletePurchase(purchase).subscribeWith(new DisposableCompletableObserver() {
            @Override
            public void onComplete() {
                view.showDeleted();
            }

            @Override
            public void onError(Throwable e) {
                view.showError(e.getMessage());
            }
        }));
    }

    @Override
    public void takeView(BoughtPurchasesContract.View view) {
        this.view = view;
        fetchBoughtPurchasesList();
    }

    @Override
    public void dropView() {
        view = null;
    }

    @Override
    public void onDetached() {
        view = null;
        disposable.clear();
        //unsubscribe rx
    }
}
