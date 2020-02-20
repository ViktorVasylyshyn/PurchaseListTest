package com.vikrotvasylyshyn.purchaselist.di;

import com.vikrotvasylyshyn.purchaselist.data.model.Purchase;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class MainModule {

    @Provides
    Purchase providesPurchase(){
        return new Purchase("", "", "", "");
    }

    @Provides
    CompositeDisposable provideCompositeDisposable(){
        return new CompositeDisposable();
    }
}
