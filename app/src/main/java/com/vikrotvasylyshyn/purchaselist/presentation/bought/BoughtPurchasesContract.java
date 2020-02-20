package com.vikrotvasylyshyn.purchaselist.presentation.bought;

import androidx.lifecycle.LiveData;

import com.vikrotvasylyshyn.purchaselist.data.model.Purchase;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface BoughtPurchasesContract {

    interface View{
        void showBoughtPurchasesList(LiveData<List<Purchase>> purchases);
    }

    interface Presenter{
        void takeView(View view);
        void dropView();
        void onDetached();
    }

    interface Repository{
        Single<List<Purchase>> fetchBoughtPurchasesList();

        Completable deletePurchase(Purchase purchase);
    }
}
