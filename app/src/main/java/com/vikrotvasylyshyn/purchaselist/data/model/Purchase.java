package com.vikrotvasylyshyn.purchaselist.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "purchase")
public class Purchase {
    @PrimaryKey(autoGenerate = true)
    public long roomId;

    private String title;
    private String count;
    private String imageUri;
    private String status; // bought/active

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

    public void setCount(String count) {
        this.count = count;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
