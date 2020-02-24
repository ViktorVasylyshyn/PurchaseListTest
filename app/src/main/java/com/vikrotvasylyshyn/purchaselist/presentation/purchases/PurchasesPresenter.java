package com.vikrotvasylyshyn.purchaselist.presentation.purchases;

import com.vikrotvasylyshyn.purchaselist.data.PurchasesRepositoryImpl;
import com.vikrotvasylyshyn.purchaselist.data.model.Purchase;
import com.vikrotvasylyshyn.purchaselist.data.model.PurchaseStatus;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class PurchasesPresenter implements PurchasesListContract.Presenter {
    private PurchasesRepositoryImpl repository;
    private PurchasesListContract.View view;

    private final CompositeDisposable disposable = new CompositeDisposable();

    private List<Purchase> purchasesList;

    @Inject
    PurchasesPresenter(PurchasesRepositoryImpl repository) {
        this.repository = repository;
    }

    private void fetchPurchasesList() {
        view.showProgress(true);
        disposable.add(
                repository.fetchPurchaseList()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                this::updatePurchasesList,
                                this::showError
                        )
        );
    }

    @Override
    public void updatePurchaseStatusAsBought(Purchase purchase) {
        view.showProgress(true);
        disposable.add(
                Completable.fromAction(()-> purchase.setStatus(PurchaseStatus.BOUGHT))
                .andThen(repository.addPurchase(purchase))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::refreshStatus,
                        this::showError)
        );
    }

    private void updatePurchasesList(List<Purchase> list) {
        this.purchasesList = list;
        if (view == null)
            return;
        view.showPurchases(list);
        view.showProgress(false);
    }

    private void showError(Throwable err) {
        if (view == null)
            return;
        view.showProgress(false);
        view.showError(err.getMessage());
    }

    private void refreshStatus(){
        view.updateBoughtStatus();
        fetchPurchasesList();
        view.showProgress(false);
    }

    @Override
    public void markAllAsBought() {
        if (purchasesList != null) {
            for (Purchase item : purchasesList) {
                updatePurchaseStatusAsBought(item);
            }
        }
    }

    @Override
    public void takeView(PurchasesListContract.View view) {
        this.view = view;
        fetchPurchasesList();
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
