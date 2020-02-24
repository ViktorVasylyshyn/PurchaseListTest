package com.vikrotvasylyshyn.purchaselist.presentation.addpurchase;

import android.net.Uri;
import android.widget.ImageView;

import com.vikrotvasylyshyn.purchaselist.data.PurchasesRepositoryImpl;
import com.vikrotvasylyshyn.purchaselist.data.model.Purchase;
import com.vikrotvasylyshyn.purchaselist.data.model.PurchaseStatus;
import com.vikrotvasylyshyn.purchaselist.utill.ImageLoader;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AddPurchasePresenter implements AddPurchaseContract.Presenter {

    private AddPurchaseContract.View view;
    private PurchasesRepositoryImpl repository;
    private Disposable disposable;

    @Inject
    AddPurchasePresenter(PurchasesRepositoryImpl repository) {
        this.repository = repository;
    }

    @Override
    public void onAddPurchaseClicked(String title, String num, String imageUri) {
        view.showProgress(true);
        Purchase newPurchase = new Purchase(title, num, imageUri, PurchaseStatus.ACTIVE.getValue());

        disposable = repository.addPurchase(newPurchase)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::showSuccessUpdate,
                        this::showError
                );
    }

    private void showSuccessUpdate() {
        if (view == null)
            return;
        view.showProgress(false);
        view.showSuccess();
    }

    private void showError(Throwable e) {
        if (view == null)
            return;
        view.showProgress(false);
        view.showError(e.getMessage());
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
        if (disposable != null) {
            disposable.dispose();
        }
        view = null;
    }

    @Override
    public void showChosenImage(ImageView imageView, Uri uri) {
        ImageLoader.loadPurchaseImage(imageView, uri.toString());
    }
}
