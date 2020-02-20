package com.vikrotvasylyshyn.purchaselist.di;

import com.vikrotvasylyshyn.purchaselist.data.model.Purchase;

import dagger.Module;
import dagger.Provides;

@Module
public class DataObjectModule {

    @Provides
    Purchase providesPurchase(){
        return new Purchase("", "", "", "");
    }
}
