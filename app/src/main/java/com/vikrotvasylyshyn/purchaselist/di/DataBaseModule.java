package com.vikrotvasylyshyn.purchaselist.di;

import android.content.Context;

import androidx.room.Room;

import com.vikrotvasylyshyn.purchaselist.data.local.PurchasesDatabase;
import com.vikrotvasylyshyn.purchaselist.utill.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
class DataBaseModule {

    @Provides
    @Singleton
    PurchasesDatabase providesDatabase(Context context){
        return Room.databaseBuilder(context.getApplicationContext(),
                PurchasesDatabase.class, Constants.DATABASE_NAME)
                .build();
    }
}
