package com.vikrotvasylyshyn.purchaselist.presentation.bought;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.vikrotvasylyshyn.purchaselist.R;
import com.vikrotvasylyshyn.purchaselist.data.model.Purchase;
import com.vikrotvasylyshyn.purchaselist.presentation.adapter.PurchasesAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;

public class BoughtPurchasesFragment extends DaggerFragment implements BoughtPurchasesContract.View {

    @Inject
    PurchasesAdapter adapter;
    @Inject
    BoughtPurchasesPresenter presenter;
    @BindView(R.id.bought_purchase_recycler_view)
    RecyclerView recyclerView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bought_purchases, container, false);
        ButterKnife.bind(this, view);
        initRecyclerView();
        return view;
    }

    @Override
    public void showBoughtPurchasesList(LiveData<List<Purchase>> purchases) {
        //show data
    }

    private void initRecyclerView() {
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
}
