package com.example.helloandroid.view.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.helloandroid.R;
import com.example.helloandroid.ShoppingList;
import com.example.helloandroid.adapter.ProductListAdapter;
import com.example.helloandroid.databases.ProductsDatabase;
import com.example.helloandroid.model.Product;
import com.example.helloandroid.presenter.ProductListPresenter;
import com.example.helloandroid.view.activities.ProductMainActivity;
import com.example.helloandroid.view.fragments.interfaces.IProductListFragmentActionListener;
import com.example.helloandroid.view.interfaces.IProductListView;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class ProductListFragment extends Fragment implements IProductListView, ProductListAdapter.IProductSelectionListener {

    @BindView(R.id.product_list)
    protected RecyclerView mProductList;
    private List<Product> mProducts;
    private ProductListAdapter mProductListAdapter;
    private IProductListFragmentActionListener iActionListener;
    private ProductListPresenter mProductListPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View productListView = inflater.inflate(R.layout.fragment_product_main, container, false);
        ButterKnife.bind(this, productListView);
        mProductListPresenter = new ProductListPresenter(this);
        mProductListPresenter.onCreate();
        init();
        updateUi();
        return productListView;
    }

    private void init() {
        mProducts = new ArrayList<>();
//        mProducts.addAll(mProductListPresenter.getProducts());
        mProductList.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        mProductList.setItemAnimator(new DefaultItemAnimator());
        mProductListAdapter = new ProductListAdapter(getContext(), mProducts, this);
        mProductList.setAdapter(mProductListAdapter);

        Product product = new Product();
        product.setProductName("Product 1");
        product.setId(11001);
        getProductDb().getProductDao().insertProduct(product);
    }

    private ProductsDatabase getProductDb() {
        return Room.databaseBuilder(ShoppingList.getAppContext(), ProductsDatabase.class, "db-products").allowMainThreadQueries().build();
    }

    private void updateUi() {
        mProducts.addAll(getProductDb().getProductDao().getAllProducts());
        mProductListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ProductMainActivity) {
            iActionListener = (IProductListFragmentActionListener) activity;
        }
    }

    @Override
    public void showProgressbar() {
        // add code to display progress update for API calls or any other longer operation.
    }

    @Override
    public void hideProgressbar() {
        // add code to dismiss the progress
    }

    @Override
    public void onProductClick(Product product) {
        if (iActionListener != null) {
            iActionListener.onProductSelected(product);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mProductListPresenter.onDestroy();
    }
}
