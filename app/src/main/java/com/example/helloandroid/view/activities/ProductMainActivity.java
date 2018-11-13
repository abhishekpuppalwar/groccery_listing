package com.example.helloandroid.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.helloandroid.R;
import com.example.helloandroid.model.Product;
import com.example.helloandroid.utils.BundleKeys;
import com.example.helloandroid.utils.Constants;
import com.example.helloandroid.view.fragments.ProductDetailsFragment;
import com.example.helloandroid.view.fragments.ProductListFragment;
import com.example.helloandroid.view.fragments.interfaces.IProductDetailsFragmentListener;
import com.example.helloandroid.view.fragments.interfaces.IProductListFragmentActionListener;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductMainActivity extends AppCompatActivity implements IProductListFragmentActionListener
        , IProductDetailsFragmentListener {
    private static final String TAG_PROD_LIST_FRAGMENT = "TAG_PROD_LIST_FRAGMENT";
    private static final String TAG_PROD_DETAILS_FRAGMENT = "TAG_PROD_DETAILS_FRAGMENT";
    private static final String TAG_PROD_ADD_FRAGMENT = "TAG_PROD_ADD_FRAGMENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_main);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();
    }

    private void init() {
        launchFragment(new ProductListFragment());
    }

    @OnClick({R.id.scan_product})
    protected void onClick(View view) {
        Intent intent = new Intent(this, BarcodeScannerActivity.class);
        startActivityForResult(intent, Constants.SCAN_PRODUCT);
    }

    @Override
    public void onProductSelected(Product product) {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(TAG_PROD_DETAILS_FRAGMENT);
        if (fragment == null) {
            fragment = new ProductDetailsFragment();
        }
        if (product != null) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(BundleKeys.PRODUCT_DETAILS, product);
            fragment.setArguments(bundle);
        }
        launchFragment(fragment);
    }

    private void launchFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.content_product_main, fragment, TAG_PROD_LIST_FRAGMENT).commit();
    }

    @Override
    public void closeDetailedView() {
        launchFragment(new ProductListFragment());
    }

    @Override
    public void addProduct() {
        launchFragment(new ProductListFragment());
    }
}