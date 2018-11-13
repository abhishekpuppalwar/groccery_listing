package com.example.helloandroid.view.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.helloandroid.R;
import com.example.helloandroid.model.Product;
import com.example.helloandroid.utils.BundleKeys;
import com.example.helloandroid.view.activities.ProductMainActivity;
import com.example.helloandroid.view.fragments.interfaces.IProductDetailsFragmentListener;
import com.example.helloandroid.view.fragments.interfaces.IProductDetailsView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductDetailsFragment extends Fragment implements IProductDetailsView {

    @BindView(R.id.product_title)
    protected TextView mProductTitle;

    private IProductDetailsFragmentListener iListener;
    private Product mProduct;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_add_product, container, false);
        ButterKnife.bind(this, view);

        view.findViewById(R.id.add_button).setVisibility(View.GONE);

        if (getArguments().containsKey(BundleKeys.PRODUCT_DETAILS)) {
            mProduct = (Product) getArguments().getSerializable(BundleKeys.PRODUCT_DETAILS);
        }

        updateUi();
        return view;
    }

    private void updateUi() {
        if (mProduct != null) {
            mProductTitle.setText(mProduct.getProductName());
        }
    }

    @OnClick({R.id.close_btn})
    protected void onClick(View view) {
        if (view.getId() == R.id.close_btn) {
            iListener.closeDetailedView();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ProductMainActivity) {
            iListener = (IProductDetailsFragmentListener) activity;
        }
    }

    @Override
    public void showProgressbar() {

    }

    @Override
    public void hideProgressbar() {

    }
}
