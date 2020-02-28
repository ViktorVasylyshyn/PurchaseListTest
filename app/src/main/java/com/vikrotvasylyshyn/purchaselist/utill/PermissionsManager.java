package com.vikrotvasylyshyn.purchaselist.utill;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;

import com.vikrotvasylyshyn.purchaselist.presentation.addpurchase.AddPurchaseFragment;

import java.lang.ref.WeakReference;

import javax.inject.Inject;

public class PermissionsManager {

    public static final int PERMISSIONS_REQUEST_ENABLE_GALLERY = 1003;
    public static final int PERMISSIONS_REQUEST_ENABLE_CAMERA = 1004;

    @Inject
    public PermissionsManager() {
    }

    public boolean checkExternalStoragePermission(WeakReference<AddPurchaseFragment> fragment) {
        if (Build.VERSION.SDK_INT < 23) {
            return true;
        } else {
            return fragment.get().requireActivity().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED;
        }
    }

    public boolean checkCameraPermission(WeakReference<AddPurchaseFragment> fragment) {
        if (Build.VERSION.SDK_INT < 23) {
            return true;
        } else {
            return (fragment.get().requireActivity().checkSelfPermission(Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_GRANTED);
        }
    }

    public void requestExternalStoragePermission(WeakReference<AddPurchaseFragment> fragment) {
        fragment.get().requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                PERMISSIONS_REQUEST_ENABLE_GALLERY);
    }

    public void requestCameraPermission(WeakReference<AddPurchaseFragment> fragment) {
        fragment.get().requestPermissions(new String[]{Manifest.permission.CAMERA},
                PERMISSIONS_REQUEST_ENABLE_CAMERA);
    }
}
