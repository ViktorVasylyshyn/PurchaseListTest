package com.vikrotvasylyshyn.purchaselist.data;

import com.vikrotvasylyshyn.purchaselist.data.model.Purchase;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface PurchasesRepository {
    Single<List<Purchase>> fetchBoughtPurchasesList();

    Completable deletePurchase(Purchase purchase);

    Completable addPurchase(Purchase purchase);

    Single<List<Purchase>> fetchPurchaseList();

}
