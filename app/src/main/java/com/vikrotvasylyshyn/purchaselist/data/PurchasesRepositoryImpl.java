package com.vikrotvasylyshyn.purchaselist.data;

import com.vikrotvasylyshyn.purchaselist.data.local.PurchasesDao;
import com.vikrotvasylyshyn.purchaselist.data.local.PurchasesDatabase;
import com.vikrotvasylyshyn.purchaselist.data.model.Purchase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;

public class PurchasesRepositoryImpl implements PurchasesRepository {

    private final PurchasesDao purchasesDao;

    @Inject
    public PurchasesRepositoryImpl(PurchasesDatabase purchasesDatabase) {
        purchasesDao = purchasesDatabase.getPurchasesDao();
    }

    @Override
    public Single<List<Purchase>> fetchPurchaseList() {
        return purchasesDao.getActivePurchase();
    }

    @Override
    public Single<List<Purchase>> fetchBoughtPurchasesList() {
        return purchasesDao.getBoughtPurchase();
    }

    @Override
    public Completable addPurchase(Purchase purchase) {
        return purchasesDao.insert(purchase);
    }

    @Override
    public Completable deletePurchase(Purchase purchase) {
        return purchasesDao.delete(purchase);
    }
}
