package com.vikrotvasylyshyn.purchaselist.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "purchases")
public class Purchase {
    @PrimaryKey(autoGenerate = true)
    public long roomId;

    private String title;
    private String count;
    private String imageUri;
    private String status;

    public Purchase(String title, String count, String imageUri, String status) {
        this.title = title;
        this.count = count;
        this.imageUri = imageUri;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCount() {
        return count;
    }

    public String getImageUri() {
        return imageUri;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(PurchaseStatus status) {
        this.status = status.getValue();
    }
}
