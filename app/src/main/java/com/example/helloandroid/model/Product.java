package com.example.helloandroid.model;

import java.io.Serializable;
import java.util.Random;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "products")
public class Product implements Serializable {
    @PrimaryKey
    @ColumnInfo(name = "productId")
    private long mId;

    @ColumnInfo(name = "productName")
    private String mProductName;

    @ColumnInfo(name = "quantity")
    private long mQuantity;

    @ColumnInfo(name = "unit")
    private String mUnit;

    public Product() {
        mId = new Random().nextLong();
        mQuantity = 0;
    }

    public String getProductName() {
        return mProductName;
    }

    public void setProductName(final String productName) {
        this.mProductName = productName;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        this.mId = id;
    }

    public long getQuantity() {
        return mQuantity;
    }

    public void setQuantity(long qunatity) {
        mQuantity = qunatity;
    }

    public String getUnit() {
        return mUnit;
    }

    public void setUnit(final String unit) {
        mUnit = unit;
    }
}
