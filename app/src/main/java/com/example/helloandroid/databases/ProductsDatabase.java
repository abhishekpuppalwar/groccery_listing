package com.example.helloandroid.databases;

import com.example.helloandroid.ShoppingList;
import com.example.helloandroid.model.Product;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Product.class}, version = 1)
public abstract class ProductsDatabase extends RoomDatabase {

    static final Migration MIGRATION_1_2 = new Migration(0, 1) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            // add code here if migration is there.
        }
    };
    private static ProductsDatabase INSTANCE;
    private ProductsDatabase database;

    public abstract ProductDao getProductDao();
}
