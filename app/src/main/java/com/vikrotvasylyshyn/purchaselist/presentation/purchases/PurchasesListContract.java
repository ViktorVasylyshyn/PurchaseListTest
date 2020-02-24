package com.vikrotvasylyshyn.purchaselist.presentation.purchases;

import com.vikrotvasylyshyn.purchaselist.data.model.Purchase;
import com.vikrotvasylyshyn.purchaselist.presentation.base.BasePresenter;
import com.vikrotvasylyshyn.purchaselist.presentation.base.BaseView;

import java.util.List;

public interface PurchasesListContract {

    interface View extends BaseView {

        void updateBoughtStatus();

        void showPurchases(List<Purchase> purchaseList);
    }

    interface Presenter extends BasePresenter<View> {

        void updatePurchaseStatusAsBought(Purchase purchase);

        void markAllAsBought();
    }
}
