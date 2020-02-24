package com.vikrotvasylyshyn.purchaselist.data.model;

public enum PurchaseStatus {
    ACTIVE("active"),
    BOUGHT("bought");

    private String value;

    PurchaseStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
