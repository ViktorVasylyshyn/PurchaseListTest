package com.vikrotvasylyshyn.purchaselist.di;

import android.app.Application;
import android.content.Context;

import com.vikrotvasylyshyn.purchaselist.presentation.adapter.PurchasesAdapter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

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
}
