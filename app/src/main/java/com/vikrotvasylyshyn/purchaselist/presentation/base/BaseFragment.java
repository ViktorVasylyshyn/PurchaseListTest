package com.vikrotvasylyshyn.purchaselist.presentation.base;

import android.widget.Toast;

import dagger.android.support.DaggerFragment;

public class BaseFragment extends DaggerFragment {

    protected void showToast(String message){
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }

    protected void showToast(int messageId){
        Toast.makeText(requireContext(), messageId, Toast.LENGTH_SHORT).show();
    }
}
