package com.vikrotvasylyshyn.purchaselist.presentation.bought;

import com.vikrotvasylyshyn.purchaselist.data.model.Purchase;
import com.vikrotvasylyshyn.purchaselist.presentation.base.BasePresenter;
import com.vikrotvasylyshyn.purchaselist.presentation.base.BaseView;

import java.util.List;

public interface BoughtPurchasesContract {

    interface View extends BaseView {
        void showBoughtPurchases(List<Purchase> purchases);

        void showDeleted();
    }

    interface Presenter extends BasePresenter<View> {
        void fetchBoughtPurchasesList();

        void deletePurchase(Purchase purchase);
    }
}
