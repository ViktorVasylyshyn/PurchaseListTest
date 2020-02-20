package com.vikrotvasylyshyn.purchaselist.presentation.add;

import com.vikrotvasylyshyn.purchaselist.data.PurchasesRepository;
import com.vikrotvasylyshyn.purchaselist.data.model.Purchase;
import com.vikrotvasylyshyn.purchaselist.utill.Constants;

import javax.inject.Inject;

import io.reactivex.observers.DisposableCompletableObserver;

public class AddPurchasePresenter implements AddPurchaseContract.Presenter {

    private AddPurchaseContract.View view;
    private AddPurchaseContract.Repository repository;
    private DisposableCompletableObserver disposable;
    @Inject
    Purchase emptyPurchase;

    @Inject
    public AddPurchasePresenter(PurchasesRepository repository) {
        this.repository = repository;
    }

    @Override
    public void onAddPurchaseClicked(String title, String num, String imageUri) {
        view.showProgress(true);
        emptyPurchase.setTitle(title);
        emptyPurchase.setCount(num);
        emptyPurchase.setImageUri(imageUri);
        emptyPurchase.setStatus(Constants.ACTIVE);
        disposable = repository.addPurchase(emptyPurchase).subscribeWith(new DisposableCompletableObserver() {
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
        if (disposable != null)
            disposable.dispose();
    }
}
