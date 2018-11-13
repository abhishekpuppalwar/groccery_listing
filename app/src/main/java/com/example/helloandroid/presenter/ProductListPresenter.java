package com.example.helloandroid.presenter;

import com.example.helloandroid.model.Product;
import com.example.helloandroid.presenter.interfaces.IBasePresenter;
import com.example.helloandroid.view.interfaces.IBaseView;
import com.example.helloandroid.view.interfaces.IProductListView;

import java.util.ArrayList;
import java.util.List;

public class ProductListPresenter extends BasePresenter {
    private final IProductListView mBaseView;
    private List<Product> mProducts;

    public ProductListPresenter(IProductListView view) {
        mBaseView = view;
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
        return getDb().getProductDao().getAllProducts();
    }
}
