package com.example.helloandroid.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helloandroid.R;
import com.example.helloandroid.model.Product;
import com.example.helloandroid.presenter.AddProductPresenter;
import com.example.helloandroid.utils.BundleKeys;
import com.example.helloandroid.view.fragments.interfaces.IAddProductView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddProductActivity extends AppCompatActivity implements IAddProductView {
    @BindView(R.id.product_title)
    protected AppCompatEditText mProductName;
    @BindView(R.id.quantity)
    protected TextView mQuantityText;
    @BindView(R.id.unit)
    protected AppCompatEditText mUnit;

    private AddProductPresenter mAddProductPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        ButterKnife.bind(this);

        if (getIntent().getExtras() != null) {
            mProductName.setEnabled(!getIntent().getExtras().containsKey(BundleKeys.PRODUCT_NAME));
            mUnit.setEnabled(!getIntent().getExtras().containsKey(BundleKeys.PRODUCT_UNIT));
        }
        mAddProductPresenter = new AddProductPresenter(this);
        mAddProductPresenter.onCreate();
        updateUi();
    }

    private void updateUi() {
        Product product = mAddProductPresenter.getProduct();
        mQuantityText.setText(String.valueOf(product.getQuantity()));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAddProductPresenter.onDestroy();
    }

    @OnClick({R.id.btn_plus, R.id.btn_minus, R.id.add_button, R.id.close_btn, R.id.quantity})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_plus:
                mAddProductPresenter.plusQuantity();
                break;

            case R.id.btn_minus:
                mAddProductPresenter.minusQuantity();
                break;

            case R.id.add_button:
                Product product = mAddProductPresenter.getProduct();
                product.setProductName(mProductName.getText().toString());
                product.setUnit(mUnit.getText().toString());
                mAddProductPresenter.addProduct();
                break;

            case R.id.close_btn:
                break;

            case R.id.quantity:
                break;
        }
    }

    @Override
    public void onQunatityUpdate(long quantity) {
        mQuantityText.setText(String.valueOf(quantity));
    }

    @Override
    public void onProductAdded() {
        Toast.makeText(this, "Product added successfully", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, ProductMainActivity.class);
        startActivity(intent);
        finish();
    }
}
