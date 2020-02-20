package com.vikrotvasylyshyn.purchaselist.presentation.add;

import com.vikrotvasylyshyn.purchaselist.data.model.Purchase;

import io.reactivex.Completable;

public interface AddPurchaseContract {
    interface View {
        void showProgress(boolean showProgress);
        void showSuccess();
        void showError();
    }

    interface Presenter {
        void onAddPurchaseClicked(String title, String num, String imageUrl);
        void takeView(AddPurchaseContract.View view);
        void dropView();
        void onDetached();
    }

    interface Repository {
        Completable addPurchase(Purchase purchase);
    }
}
