package com.vikrotvasylyshyn.purchaselist.presentation.purchases;

import android.view.View;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.RecyclerView;

import com.vikrotvasylyshyn.purchaselist.R;
import com.vikrotvasylyshyn.purchaselist.data.model.Purchase;
import com.vikrotvasylyshyn.purchaselist.presentation.adapter.PurchasesAdapter;
import com.vikrotvasylyshyn.purchaselist.presentation.base.BaseFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
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

    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_purchases;
    }

    @Override
    protected void init() {
        initRecyclerView();
    }

    @OnClick(R.id.mark_all)
    void markAllPurchases() {
        presenter.markAllAsBought();
    }

    @Override
    public void showPurchases(List<Purchase> purchaseList) {
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

    @Override
    public void updateBoughtStatus() {
        showToast(R.string.toast_purchase_added_to_bought);
    }

    private void initRecyclerView() {
        adapter.setOnClickListener(this::markAsBought);
        recyclerView.setAdapter(adapter);
    }

    private void markAsBought(Purchase item) {
        presenter.updatePurchaseStatusAsBought(item);
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
}
