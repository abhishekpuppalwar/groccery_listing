package com.example.helloandroid.databases;

import com.example.helloandroid.model.Product;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface ProductDao {
    @Query("select * from products")
    public List<Product> getAllProducts();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProduct(Product product);

    @Query("DELETE FROM products")
    void deleteAllProducts();
}
