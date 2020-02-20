package com.vikrotvasylyshyn.purchaselist.presentation.add;

import com.vikrotvasylyshyn.purchaselist.data.PurchasesRepository;
import com.vikrotvasylyshyn.purchaselist.data.model.Purchase;

import javax.inject.Inject;

import io.reactivex.observers.DisposableCompletableObserver;

public class AddPurchasePresenter implements AddPurchaseContract.Presenter {

    private AddPurchaseContract.View view;
    private AddPurchaseContract.Repository repository;
    private DisposableCompletableObserver disposable;

    @Inject
    public AddPurchasePresenter(PurchasesRepository repository) {
        this.repository = repository;
    }

    @Override
    public void onAddPurchaseClicked(Purchase purchase) {
        view.showProgress(true);
//        Log.d(Constants.TAG, purchase.getTitle() +" "+ purchase.getCount()+ " "+ purchase.getImageUri() +  " " + purchase.getStatus());
        disposable = repository.addPurchase(purchase).subscribeWith(new DisposableCompletableObserver() {
            @Override
            public void onComplete() {
                view.showProgress(false);
                view.showSuccess();
            }

            @Override
            public void onError(Throwable e) {
                view.showProgress(false);
                view.showError();
            }
        });
    }

    @Override
    public void takeView(AddPurchaseContract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {
        view = null;
    }

    @Override
    public void onDetached() {
        view = null;
        disposable.dispose();
        //unsubscribe rx
    }
}
