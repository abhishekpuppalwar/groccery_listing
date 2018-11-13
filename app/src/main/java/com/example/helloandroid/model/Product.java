package com.example.helloandroid.model;

import java.io.Serializable;

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
}
