package com.vikrotvasylyshyn.purchaselist.presentation.purchases;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

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
import butterknife.OnClick;

public class PurchasesFragment extends BaseFragment implements PurchasesListContract.View {

    @BindView(R.id.purchase_recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.progressbar)
    ProgressBar progressBar;
    @Inject
    PurchasesAdapter adapter;
    @Inject
    PurchasesPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_purchases, container, false);
        ButterKnife.bind(this, view);
        initRecyclerView();
        return view;
    }

    @OnClick(R.id.mark_all)
    void markAllPurchases(){
        //mark all
    }

    @Override
    public void showPurchases(List<Purchase> purchaseList) {
//        showToast(String.valueOf(purchaseList.size()));
        adapter.setData(purchaseList);
    }

    @Override
    public void showProgress(boolean showProgress) {
        progressBar.setVisibility(showProgress ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showError(String message) {
        showToast(message);
    }

    private void initRecyclerView() {
        adapter.setOnClickListener(this::markAsBought);
        recyclerView.setAdapter(adapter);
    }

    private void markAsBought(Purchase item) {
        presenter.markAsBought(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.takeView(this);
        presenter.fetchPurchasesList();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.dropView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onDetached();
    }
}
