package com.vikrotvasylyshyn.purchaselist.presentation.addpurchase.chooseimage;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;

import androidx.core.content.FileProvider;

import com.vikrotvasylyshyn.purchaselist.presentation.addpurchase.AddPurchaseFragment;
import com.vikrotvasylyshyn.purchaselist.utill.Constants;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

public class ChooseImageDelegate {

    private AddPurchaseFragment fragment;

    private Uri imageUri;

    @Inject
    public ChooseImageDelegate(AddPurchaseFragment fragment) {
        this.fragment = fragment;
    }

    public void chooseFromGallery() {
        if (Build.VERSION.SDK_INT < 23) {
            fragment.showToast("this feature will be able for sdk<23 soon");
            return;
        }
        if (checkPermissions(RequestType.GALLERY)) {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            fragment.startActivityForResult(intent, Constants.REQUEST_CODE_GET_IMAGE_FROM_GALLERY);
        } else {
            requestPermissions(RequestType.GALLERY);
        }
    }

    public void chooseFromCamera() {
        if (Build.VERSION.SDK_INT < 23) {
            fragment.showToast("this feature will be able for sdk<23 soon");
            return;
        }
        if (checkPermissions(RequestType.CAMERA)) {
            dispatchTakePictureIntent();
        } else {
            requestPermissions(RequestType.CAMERA);
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat(Constants.SDF_PATTERN, Locale.getDefault()).format(new Date());
        String imageFileName = Constants.TAKE_FROM_CAM_FILE_PREFIX + timeStamp + "_";
        File storageDir = fragment.requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return File.createTempFile(imageFileName, Constants.TAKE_FROM_CAM_FILE_SUFFIX, storageDir);
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(fragment.requireActivity().getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                fragment.showToast(ex.getMessage());
            }
            if (photoFile != null) {
                imageUri = FileProvider.getUriForFile(fragment.requireActivity(),
                        Constants.AUTHORITY,
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                fragment.startActivityForResult(takePictureIntent, Constants.REQUEST_CODE_GET_IMAGE_FROM_CAMERA);
            }
        }
    }

    private boolean checkPermissions(RequestType type) {
        if (Build.VERSION.SDK_INT < 23)
            return true;
        switch (type) {
            case GALLERY:
                return (fragment.requireActivity().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_GRANTED);
            case CAMERA:
                return (fragment.requireActivity().checkSelfPermission(Manifest.permission.CAMERA)
                        == PackageManager.PERMISSION_GRANTED);
            default:
                throw new IllegalArgumentException("unknown argument");
        }
    }

    private void requestPermissions(RequestType type) {
        switch (type) {
            case GALLERY:
                fragment.requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}
                        , Constants.PERMISSIONS_REQUEST_ENABLE_GALLERY);
                break;
            case CAMERA:
                fragment.requestPermissions(new String[]{Manifest.permission.CAMERA},
                        Constants.PERMISSIONS_REQUEST_ENABLE_CAMERA);
                break;
            default:
                throw new IllegalArgumentException("unknown argument");
        }
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public void detach() {
        fragment = null;
    }

    enum RequestType {
        GALLERY,
        CAMERA
    }
}
