package com.vikrotvasylyshyn.purchaselist.presentation.bought;

import com.vikrotvasylyshyn.purchaselist.data.PurchasesRepository;
import com.vikrotvasylyshyn.purchaselist.data.PurchasesRepositoryImpl;
import com.vikrotvasylyshyn.purchaselist.data.model.Purchase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class BoughtPurchasesPresenter implements BoughtPurchasesContract.Presenter {

    private BoughtPurchasesContract.View view;
    private PurchasesRepository repository;

    private final CompositeDisposable disposable = new CompositeDisposable();

    @Inject
    BoughtPurchasesPresenter(PurchasesRepositoryImpl repository) {
        this.repository = repository;
    }

    @Override
    public void fetchBoughtPurchasesList() {
        view.showProgress(true);
        disposable.add(repository.fetchBoughtPurchasesList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::updateList,
                        this::showError
                ));
    }


    @Override
    public void deletePurchase(Purchase purchase) {
        disposable.add(repository.deletePurchase(purchase)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::showDeleted,
                        this::showError
                ));
    }

    private void updateList(List<Purchase> list) {
        if (view == null)
            return;
        view.showBoughtPurchases(list);
        view.showProgress(false);
    }

    private void showError(Throwable e) {
        if (view == null)
            return;
        view.showProgress(false);
        view.showError(e.getMessage());
    }

    private void showDeleted() {
        if (view == null)
            return;
        fetchBoughtPurchasesList();
        view.showDeleted();
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
        disposable.dispose();
        view = null;
    }
}
