package com.vikrotvasylyshyn.purchaselist.di;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.vikrotvasylyshyn.purchaselist.presentation.PurchaseActivity;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = {})
    abstract PurchaseActivity contributeMainActivity();

    @Provides
    static BottomNavigationView.OnNavigationItemReselectedListener provideBottomNavigationViewReselectedListener() {
        return item -> {
            /*multiple touches the same icon of bnb must do nothing*/
        };
    }
}
