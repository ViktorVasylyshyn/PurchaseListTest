package com.vikrotvasylyshyn.purchaselist.presentation;

import android.os.Bundle;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.vikrotvasylyshyn.purchaselist.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

public class PurchaseActivity extends DaggerAppCompatActivity {

    @Inject
    BottomNavigationView.OnNavigationItemReselectedListener mOnNavigationItemReselectedListener;
    @BindView(R.id.bottom_navigation_view)
    BottomNavigationView bottomNavigationView;

    NavController navController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
        ButterKnife.bind(this);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        initBottomNavigationView();
    }

    private void initBottomNavigationView() {
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
        bottomNavigationView.setOnNavigationItemReselectedListener(mOnNavigationItemReselectedListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bottomNavigationView.setOnNavigationItemReselectedListener(null);
    }
}
