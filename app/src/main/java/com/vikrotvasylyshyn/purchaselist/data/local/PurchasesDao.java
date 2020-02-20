package com.vikrotvasylyshyn.purchaselist.data.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.vikrotvasylyshyn.purchaselist.data.model.Purchase;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface PurchasesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(Purchase model);

    @Delete
    Completable delete(Purchase model);

    @Query("SELECT * FROM purchases WHERE status = 'bought'")

    Single<List<Purchase>> getBoughtPurchase();

    @Query("SELECT * FROM purchases WHERE status = 'active'")

    Single<List<Purchase>> getActivePurchase();
}
