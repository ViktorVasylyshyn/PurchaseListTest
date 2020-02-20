package com.vikrotvasylyshyn.purchaselist.di;

import android.app.Application;
import android.content.Context;

import com.vikrotvasylyshyn.purchaselist.presentation.adapter.PurchasesAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    Context provideContext(Application application) {
        return application.getApplicationContext();
    }

    @Provides
    PurchasesAdapter providesPurchasesAdapter() {
        return new PurchasesAdapter();
    }
}
