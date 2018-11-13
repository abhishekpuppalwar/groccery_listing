package com.example.helloandroid.presenter;

import com.example.helloandroid.model.Product;
import com.example.helloandroid.presenter.interfaces.IBasePresenter;
import com.example.helloandroid.view.interfaces.IBaseView;

import java.util.ArrayList;
import java.util.List;

public class ProductListPresenter implements IBasePresenter {
    private final IBaseView mBaseView;
    private List<Product> mProducts;

    public ProductListPresenter(IBaseView baseView) {
        mBaseView = baseView;
        //TODO: init event bus
    }

    @Override
    public void onCreate() {
        //TODO: register the event bus here
    }

    @Override
    public void onDestroy() {
        //TODO: unregister the event bus here
    }

    public List<Product> getProducts() {
        if(mProducts != null) {
            return mProducts;
        }
        return getDummyProducts();
    }

    private List<Product> getDummyProducts() {
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Product product = new Product();
            product.setProductName("Product" + i);
            products.add(product);
        }
        return products;
    }
}
