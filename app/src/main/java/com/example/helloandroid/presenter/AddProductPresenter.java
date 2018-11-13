package com.example.helloandroid.presenter;

import com.example.helloandroid.databases.ProductsDatabase;
import com.example.helloandroid.model.Product;
import com.example.helloandroid.view.fragments.interfaces.IAddProductView;

public class AddProductPresenter extends BasePresenter {
    private final IAddProductView iAddProductView;
    private Product mNewProduct;
    private ProductsDatabase mDb;

    public AddProductPresenter(IAddProductView addProductView) {
        iAddProductView = addProductView;
    }

    @Override
    public void onCreate() {
        mNewProduct = new Product();
        mDb = getDb();
    }

    @Override
    public void onDestroy() {
        mNewProduct = null;
    }

    public void plusQuantity() {
        mNewProduct.setQuantity(mNewProduct.getQuantity() + 1);
        iAddProductView.onQunatityUpdate(mNewProduct.getQuantity());
    }

    public void minusQuantity() {
        mNewProduct.setQuantity(mNewProduct.getQuantity() <= 0 ? 0 : mNewProduct.getQuantity() - 1);
        iAddProductView.onQunatityUpdate(mNewProduct.getQuantity());
    }

    public void addProduct() {
        getDb().getProductDao().insertProduct(mNewProduct);
        iAddProductView.onProductAdded();
    }

    public Product getProduct() {
        return mNewProduct;
    }
}
