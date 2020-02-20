package com.vikrotvasylyshyn.purchaselist.presentation.purchases;

import com.vikrotvasylyshyn.purchaselist.data.PurchasesRepository;
import com.vikrotvasylyshyn.purchaselist.data.model.Purchase;
import com.vikrotvasylyshyn.purchaselist.utill.Constants;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;

public class PurchasesPresenter implements PurchasesListContract.Presenter {
    private PurchasesRepository repository;
    private PurchasesListContract.View view;

    @Inject
    CompositeDisposable disposable;

    private List<Purchase> purchasesList; // для того, чтобы была возможность удалить одним махом все записи, оставляю сдесь экземпляр данных пока не придумаю лучше способа

    @Inject
    public PurchasesPresenter(PurchasesRepository repository) {
        this.repository = repository;
    }

    @Override
    public void fetchPurchasesList() {
        view.showProgress(true);
        disposable.add(repository.fetchPurchaseList().subscribeWith(new DisposableSingleObserver<List<Purchase>>() {

            @Override
            public void onSuccess(List<Purchase> list) {
                purchasesList = list;
                view.showPurchases(list);
                view.showProgress(false);
            }

            @Override
            public void onError(Throwable e) {
                view.showProgress(false);
                view.showError(e.getMessage());
            }
        }));
    }

    @Override
    public void markAsBought(Purchase purchase) {
        view.showProgress(true);
        purchase.setStatus(Constants.BOUGHT);
        disposable.add(repository.addPurchase(purchase).subscribeWith(new DisposableCompletableObserver() {
            @Override
            public void onComplete() {
                view.markedAsBought();
                fetchPurchasesList();
                view.showProgress(false);
            }

            @Override
            public void onError(Throwable e) {
                view.showError(e.getMessage());
                view.showProgress(false);
            }
        }));
    }

    @Override
    public void markAllAsBought() {
        if(purchasesList!=null){
            for(Purchase item : purchasesList){
                markAsBought(item);
            }
        }
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
