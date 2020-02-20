package com.vikrotvasylyshyn.purchaselist.presentation.adapter;

import com.vikrotvasylyshyn.purchaselist.data.model.Purchase;

public interface AdapterCallback {
    interface OnPurchaseClickListener {
        void onPurchaseClicked(Purchase item);
    }
}
