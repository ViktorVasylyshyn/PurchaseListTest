package com.vikrotvasylyshyn.purchaselist.presentation.base;

public interface BasePresenter<T> {

    void takeView(T view);
    void dropView();
    void onDetached();
}
