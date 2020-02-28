package com.vikrotvasylyshyn.purchaselist.di;

import android.content.Context;

import androidx.room.Room;

import com.vikrotvasylyshyn.purchaselist.data.local.PurchasesDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
class DataBaseModule {

    private static final String DATABASE_NAME = "purchases";

    @Provides
    @Singleton
    PurchasesDatabase providesDatabase(Context context){
        return Room.databaseBuilder(context.getApplicationContext(),
                PurchasesDatabase.class, DATABASE_NAME)
                .build();
    }
}
