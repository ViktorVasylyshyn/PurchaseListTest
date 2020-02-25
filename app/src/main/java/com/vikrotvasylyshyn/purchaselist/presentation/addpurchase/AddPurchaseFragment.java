package com.vikrotvasylyshyn.purchaselist.presentation.addpurchase;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.vikrotvasylyshyn.purchaselist.R;
import com.vikrotvasylyshyn.purchaselist.presentation.addpurchase.chooseimage.ChooseImageDelegate;
import com.vikrotvasylyshyn.purchaselist.presentation.base.BaseFragment;
import com.vikrotvasylyshyn.purchaselist.utill.Constants;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class AddPurchaseFragment extends BaseFragment implements AddPurchaseContract.View {

    @Inject
    AddPurchasePresenter presenter;
    @Inject
    ChooseImageDelegate chooseImageDelegate;
    @BindView(R.id.add_progressbar)
    ProgressBar progressBar;
    @BindView(R.id.purchase_title)
    EditText title;
    @BindView(R.id.purchase_num)
    EditText num;
    @BindView(R.id.purchase_image)
    ImageView imageView;
    @BindView(R.id.fab_add_image)
    FloatingActionButton addImage;

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
        imageView.setImageResource(R.drawable.ic_image);
        imageUri = null;
    }

    @Override
    public void showError(String message) {
        showToast(message);
    }

    @OnClick(R.id.fab_add_image)
    void showMenu() {
        PopupMenu popupMenu = new PopupMenu(addImage.getContext(), addImage);
        popupMenu.getMenuInflater().inflate(R.menu.menu_add_image, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.menu_gallery:
                    chooseImageDelegate.chooseFromGallery();
                    break;
                case R.id.menu_camera:
                    chooseImageDelegate.chooseFromCamera();
                    break;
                default:
                    throw new IllegalArgumentException("unknown argument");
            }
            return true;
        });
        popupMenu.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            switch (requestCode) {
                case Constants.PERMISSIONS_REQUEST_ENABLE_GALLERY:
                    chooseImageDelegate.chooseFromGallery();
                    break;
                case Constants.PERMISSIONS_REQUEST_ENABLE_CAMERA:
                    chooseImageDelegate.chooseFromCamera();
                    break;
                default:
                    throw new IllegalArgumentException("unknown argument");
            }
        } else {
            showToast(R.string.toast_need_permissions);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != Activity.RESULT_OK) {
            imageUri = null;
            showToast(R.string.toast_image_upload_error);
            return;
        }
        switch (requestCode) {
            case Constants.REQUEST_CODE_GET_IMAGE_FROM_GALLERY:
                imageUri = data.getData();
                if (imageUri != null)
                    presenter.showChosenImage(imageView, imageUri);
                break;
            case Constants.REQUEST_CODE_GET_IMAGE_FROM_CAMERA:
                imageUri = chooseImageDelegate.getImageUri();
                presenter.showChosenImage(imageView, imageUri);
                break;
            default:
                throw new IllegalArgumentException("unknown argument");
        }
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
        chooseImageDelegate.detach();
    }
}
