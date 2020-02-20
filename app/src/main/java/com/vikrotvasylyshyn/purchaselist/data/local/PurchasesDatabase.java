package com.vikrotvasylyshyn.purchaselist.data.local;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.vikrotvasylyshyn.purchaselist.data.model.Purchase;

@Database(entities = {Purchase.class}, version = 1)
public abstract class PurchasesDatabase extends RoomDatabase {

    private static PurchasesDatabase instance;

    public abstract PurchasesDao purchaseDao();

    public static synchronized PurchasesDatabase getInstance(Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    PurchasesDatabase.class, "purchases")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static final RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };
}
