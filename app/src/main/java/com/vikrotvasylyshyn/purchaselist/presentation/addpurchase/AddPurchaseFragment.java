package com.vikrotvasylyshyn.purchaselist.presentation.addpurchase;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.vikrotvasylyshyn.purchaselist.R;
import com.vikrotvasylyshyn.purchaselist.presentation.base.BaseFragment;
import com.vikrotvasylyshyn.purchaselist.utill.Constants;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class AddPurchaseFragment extends BaseFragment implements AddPurchaseContract.View {

    @Inject
    AddPurchasePresenter presenter;
    @BindView(R.id.add_progressbar)
    ProgressBar progressBar;
    @BindView(R.id.purchase_title)
    EditText title;
    @BindView(R.id.purchase_num)
    EditText num;
    @BindView(R.id.purchase_image)
    ImageView imageView;

    private Uri imageUri;

    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_add_purchase;
    }

    @Override
    protected void init() {
        //nothing to implement for now
    }

    @OnClick(R.id.confirm_button)
    void addPurchase() {
        String uri = "";
        if (title.getText().toString().equals("")) {
            showToast(R.string.toast_enter_title);
            return;
        }
        if (num.getText().toString().equals("")) {
            showToast(R.string.toast_enter_num);
            return;
        }
        if (imageUri != null)
            uri = imageUri.toString();
        presenter.onAddPurchaseClicked(title.getText().toString(), num.getText().toString(), uri);
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
        imageView.setImageResource(R.drawable.ic_launcher_foreground);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == Constants.REQUEST_CODE_PICK_IMAGE) {
            try {
                imageUri = data.getData();
            } catch (NullPointerException e) {
                showToast(R.string.toast_image_upload_error);
            }
            if (imageUri != null)
                presenter.showChosenImage(imageView, imageUri);
        }
    }

    @Override
    public void showError(String message) {
        showToast(message);
    }

    @OnClick(R.id.purchase_image)
    void pickupImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, Constants.REQUEST_CODE_PICK_IMAGE);
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
