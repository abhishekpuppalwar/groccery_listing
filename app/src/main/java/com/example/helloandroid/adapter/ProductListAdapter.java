package com.example.helloandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.helloandroid.R;
import com.example.helloandroid.model.Product;

import org.w3c.dom.Text;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {
    private static final int DEFAULT_LIST_SIZE = 0;
    final List<Product> mProducts;
    final Context mContext;
    final IProductSelectionListener iListener;

    public ProductListAdapter(Context context, List<Product> products, IProductSelectionListener listener) {
        mProducts = products;
        mContext = context;
        iListener = listener;
    }

    @NonNull
    @Override
    public ProductListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_product_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListAdapter.ViewHolder holder, int position) {
        if (mProducts != null && mProducts.size() > 0) {
            Product product = mProducts.get(position);
            holder.mProductTitle.setText(product.getProductName());
            holder.mProdQuantity.setText(String.valueOf(product.getQuantity()));
            holder.mProdUnit.setText(product.getUnit());
        }
    }

    @Override
    public int getItemCount() {
        return mProducts != null && !mProducts.isEmpty() ? mProducts.size() : DEFAULT_LIST_SIZE;
    }

    public interface IProductSelectionListener {
        void onProductClick(Product product);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.product_title)
        protected TextView mProductTitle;
        @BindView(R.id.prod_list_quantity)
        protected TextView mProdQuantity;
        @BindView(R.id.prod_list_unit)
        protected TextView mProdUnit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iListener.onProductClick(mProducts.get(getAdapterPosition()));
                }
            });
        }
    }

}
