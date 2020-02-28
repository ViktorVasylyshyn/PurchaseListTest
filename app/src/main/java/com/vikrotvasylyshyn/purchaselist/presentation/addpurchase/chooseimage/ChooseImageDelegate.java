package com.vikrotvasylyshyn.purchaselist.presentation.addpurchase.chooseimage;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;

import androidx.core.content.FileProvider;

import com.vikrotvasylyshyn.purchaselist.presentation.addpurchase.AddPurchaseFragment;
import com.vikrotvasylyshyn.purchaselist.utill.PermissionsManager;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

public class ChooseImageDelegate {

    public static final int REQUEST_CODE_GET_IMAGE_FROM_GALLERY = 1001;
    private static final int REQUEST_CODE_GET_IMAGE_FROM_CAMERA = 1002;

    private static final String SDF_PATTERN = "yyyyMMdd_HHmmss";
    private static final String TAKE_FROM_CAM_FILE_SUFFIX = ".jpg";
    private static final String TAKE_FROM_CAM_FILE_PREFIX = "JPEG_";
    private static final String AUTHORITY = "com.vikrotvasylyshyn.purchaselist";

    private WeakReference<AddPurchaseFragment> fragment;

    private PermissionsManager permissionsManager;

    @Inject
    public ChooseImageDelegate(PermissionsManager permissionsManager) {
        this.permissionsManager = permissionsManager;
    }

    public void chooseFromGallery() {
        if (Build.VERSION.SDK_INT < 23) {
            fragment.get().showToast("this feature will be able for sdk<23 soon");
            return;
        }
        if (permissionsManager.checkExternalStoragePermission(fragment)) {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            fragment.get().startActivityForResult(intent, REQUEST_CODE_GET_IMAGE_FROM_GALLERY);
        } else {
            permissionsManager.requestExternalStoragePermission(fragment);
        }
    }

    public void chooseFromCamera() {
        if (Build.VERSION.SDK_INT < 23) {
            fragment.get().showToast("this feature will be able for sdk<23 soon");
            return;
        }
        if (permissionsManager.checkCameraPermission(fragment)) {
            dispatchTakePictureIntent();
        } else {
            permissionsManager.requestCameraPermission(fragment);
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat(SDF_PATTERN, Locale.getDefault()).format(new Date());
        String imageFileName = TAKE_FROM_CAM_FILE_PREFIX + timeStamp + "_";
        File storageDir = fragment.get().requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return File.createTempFile(imageFileName, TAKE_FROM_CAM_FILE_SUFFIX, storageDir);
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(fragment.get().requireActivity().getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                fragment.get().showToast(ex.getMessage());
            }
            if (photoFile != null) {
                Uri imageUri = FileProvider.getUriForFile(fragment.get().requireActivity(),
                        AUTHORITY,
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                fragment.get().setImageUri(imageUri);
                fragment.get().startActivityForResult(takePictureIntent, REQUEST_CODE_GET_IMAGE_FROM_CAMERA);
            }
        }
    }

    public void setFragment(AddPurchaseFragment fragment) {
        if (this.fragment == null)
            this.fragment = new WeakReference<>(fragment);
    }
}
