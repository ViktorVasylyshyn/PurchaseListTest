package com.vikrotvasylyshyn.purchaselist.data;

import android.app.Application;

import com.vikrotvasylyshyn.purchaselist.data.local.PurchasesDao;
import com.vikrotvasylyshyn.purchaselist.data.local.PurchasesDatabase;
import com.vikrotvasylyshyn.purchaselist.data.model.Purchase;
import com.vikrotvasylyshyn.purchaselist.presentation.add.AddPurchaseContract;
import com.vikrotvasylyshyn.purchaselist.presentation.bought.BoughtPurchasesContract;
import com.vikrotvasylyshyn.purchaselist.presentation.purchases.PurchasesListContract;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PurchasesRepository implements PurchasesListContract.Repository, AddPurchaseContract.Repository, BoughtPurchasesContract.Repository {

    private final PurchasesDao purchasesDao;

    @Inject
    public PurchasesRepository(Application application) {
        PurchasesDatabase database = PurchasesDatabase.getInstance(application);
        purchasesDao = database.purchaseDao();
    }

    @Override
    public Single<List<Purchase>> fetchPurchaseList() {
        return purchasesDao.getActivePurchase()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<List<Purchase>> fetchBoughtPurchasesList() {
        return purchasesDao.getBoughtPurchase()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Completable addPurchase(Purchase purchase) {
        return Completable.fromCallable(() -> purchasesDao.insert(purchase))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Completable deletePurchase(Purchase purchase) {
        return Completable.fromCallable(() -> purchasesDao.delete(purchase))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
