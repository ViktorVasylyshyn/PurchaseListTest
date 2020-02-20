package com.vikrotvasylyshyn.purchaselist.di;

import android.app.Application;
import android.content.Context;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.vikrotvasylyshyn.purchaselist.presentation.adapter.PurchasesAdapter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class AppModule {

    @Singleton
    @Provides
    Context provideContext(Application application) {
        return application.getApplicationContext();
    }

    @Singleton
    @Provides
    PurchasesAdapter providesPurchasesAdapter() {
        return new PurchasesAdapter();
    }

@Singleton
    @Provides
    BottomNavigationView.OnNavigationItemReselectedListener provideBottomNavigationViewReselectedListener() {
        return item -> {
            /*multiple touches the same icon of bnb must do nothing*/
        };
    }
}
