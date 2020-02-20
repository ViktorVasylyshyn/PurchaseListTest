package com.vikrotvasylyshyn.purchaselist.presentation.bought;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.vikrotvasylyshyn.purchaselist.R;
import com.vikrotvasylyshyn.purchaselist.data.model.Purchase;
import com.vikrotvasylyshyn.purchaselist.presentation.adapter.PurchasesAdapter;
import com.vikrotvasylyshyn.purchaselist.presentation.base.BaseFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BoughtPurchasesFragment extends BaseFragment implements BoughtPurchasesContract.View {

    @Inject
    PurchasesAdapter adapter;
    @Inject
    BoughtPurchasesPresenter presenter;
    @BindView(R.id.bought_purchase_recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.bought_purchase_progress_bar)
    ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bought_purchases, container, false);
        ButterKnife.bind(this, view);
        initRecyclerView();
        return view;
    }

    private void initRecyclerView() {
        adapter.setOnClickListener(this::deletePurchase);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onResume() {
        super.onResume();
        presenter.takeView(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.dropView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDetached();
    }

    @Override
    public void showBoughtPurchases(List<Purchase> purchases) {
        adapter.setData(purchases);
    }

    private void deletePurchase(Purchase purchase) {
        presenter.deletePurchase(purchase);
    }

    @Override
    public void showError(String string) {
        showToast(string);
    }

    @Override
    public void showProgress(boolean progress) {
        progressBar.setVisibility(progress ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showDeleted() {
        showToast(R.string.toast_purchase_deleted);
    }
}
