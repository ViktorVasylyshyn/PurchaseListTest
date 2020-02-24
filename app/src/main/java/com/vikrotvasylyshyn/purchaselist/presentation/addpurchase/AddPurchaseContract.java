package com.vikrotvasylyshyn.purchaselist.presentation.addpurchase;

import android.net.Uri;
import android.widget.ImageView;

import com.vikrotvasylyshyn.purchaselist.presentation.base.BasePresenter;
import com.vikrotvasylyshyn.purchaselist.presentation.base.BaseView;

public interface AddPurchaseContract {
    interface View extends BaseView {
        void showProgress(boolean showProgress);

        void showSuccess();

        void showError(String message);
    }

    interface Presenter extends BasePresenter<View> {
        void onAddPurchaseClicked(String title, String num, String imageUrl);

        void showChosenImage(ImageView imageView, Uri uri);
    }
}
