package com.vikrotvasylyshyn.purchaselist.presentation.purchases;

import com.vikrotvasylyshyn.purchaselist.data.model.Purchase;

import java.util.List;

import io.reactivex.Single;

public interface PurchasesListContract {

    interface View {
        void showProgress(boolean showProgress);

        void showError(String message);

        void showPurchases(List<Purchase> purchaseList);
    }

    interface Presenter {
        void fetchPurchasesList();

        void markAsBought(Purchase purchase);

        void takeView(View view);

        void dropView();

        void onDetached();
    }

    interface Repository {
        Single<List<Purchase>> fetchPurchaseList();

    }
}
