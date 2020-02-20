package com.vikrotvasylyshyn.purchaselist.presentation.bought;

import com.vikrotvasylyshyn.purchaselist.data.model.Purchase;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface BoughtPurchasesContract {

    interface View {
        void showBoughtPurchases(List<Purchase> purchases);

        void showError(String string);

        void showProgress(boolean progress);

        void showDeleted();
    }

    interface Presenter {
        void fetchBoughtPurchasesList();

        void deletePurchase(Purchase purchase);

        void takeView(View view);

        void dropView();

        void onDetached();
    }

    interface Repository {
        Single<List<Purchase>> fetchBoughtPurchasesList();

        Completable deletePurchase(Purchase purchase);
    }
}
