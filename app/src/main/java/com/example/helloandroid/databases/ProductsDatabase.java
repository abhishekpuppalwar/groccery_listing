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

@Database(entities = {Product.class}, version = 2)
public abstract class ProductsDatabase extends RoomDatabase {

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE products "
                    + " ADD COLUMN qunatity INTEGER" + " ADD COLUMN unit TEXT");
        }
    };
    private static ProductsDatabase INSTANCE;
    private ProductsDatabase database;

    public abstract ProductDao getProductDao();
}
