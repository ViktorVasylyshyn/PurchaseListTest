package com.vikrotvasylyshyn.purchaselist.presentation.add;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.vikrotvasylyshyn.purchaselist.R;
import com.vikrotvasylyshyn.purchaselist.data.model.Purchase;
import com.vikrotvasylyshyn.purchaselist.presentation.base.BaseFragment;
import com.vikrotvasylyshyn.purchaselist.utill.Constants;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddPurchaseFragment extends BaseFragment implements AddPurchaseContract.View {

    @Inject
    AddPurchaseContract.Presenter presenter;
    @BindView(R.id.add_progressbar)
    ProgressBar progressBar;
    @BindView(R.id.purchase_title)
    EditText title;
    @BindView(R.id.purchase_num)
    EditText num;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_purchase, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.confirm_button)
    void addPurchase() {
        //TODO check empty params
        presenter.onAddPurchaseClicked(new Purchase(title.getText().toString(), num.getText().toString(), "",Constants.ACTIVE));
    }

    @Override
    public void showProgress(boolean showProgress) {
        progressBar.setVisibility(showProgress ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showSuccess() {
        showToast(R.string.toast_purchase_added);
        title.setText("");
        num.setText("");
        //drop image
    }

    @Override
    public void showError() {
        Toast.makeText(requireContext(), R.string.toast_purchase_error, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.purchase_image)
    void addImage() {

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
