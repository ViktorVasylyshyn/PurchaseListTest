package com.vikrotvasylyshyn.purchaselist.di;

import android.app.Application;
import android.content.Context;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import dagger.Module;
import dagger.Provides;

@Module
class AppModule {

    @Provides
    Context provideContext(Application application) {
        return application.getApplicationContext();
    }

    @Provides
    BottomNavigationView.OnNavigationItemReselectedListener provideBottomNavigationViewReselectedListener() {
        return item -> {
            /*multiple touches the same bnb icon must do nothing*/
        };
    }
}
