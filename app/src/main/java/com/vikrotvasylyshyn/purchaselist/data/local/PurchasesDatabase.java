package com.vikrotvasylyshyn.purchaselist.data.local;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.vikrotvasylyshyn.purchaselist.data.model.Purchase;

@Database(entities = {Purchase.class}, version = PurchasesDatabase.VERSION, exportSchema = false)
public abstract class PurchasesDatabase extends RoomDatabase {

    static final int VERSION = 1;

    public abstract PurchasesDao getPurchasesDao();
}
