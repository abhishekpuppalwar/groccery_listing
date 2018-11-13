package com.example.helloandroid.presenter;

import com.example.helloandroid.ShoppingList;
import com.example.helloandroid.databases.ProductsDatabase;
import com.example.helloandroid.presenter.interfaces.IBasePresenter;

import androidx.room.Room;

public abstract class BasePresenter implements IBasePresenter {
    public ProductsDatabase getDb() {
        return Room.databaseBuilder(ShoppingList.getAppContext(), ProductsDatabase.class, "db-products").allowMainThreadQueries().build();
    }
}
