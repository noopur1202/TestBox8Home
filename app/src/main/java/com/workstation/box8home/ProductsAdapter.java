package com.workstation.box8home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.MyViewHolder> {

    private List<Modelproducts> productList;

    /**
     * View holder class
     * */
    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView productName;
        public ImageView itemImage;

        public MyViewHolder(View view) {
            super(view);
            productName = (TextView) view.findViewById(R.id.name);
        }
    }

    public ProductsAdapter(List<Modelproducts> countryList) {
        this.productList = countryList;
    }

    @Override
    public void onBindViewHolder(ProductsAdapter.MyViewHolder holder, int position) {
        Modelproducts c = productList.get(position);
        holder.productName.setText(c.name);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    @Override
    public ProductsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                               .inflate(R.layout.main_list_single,parent, false);
        return new ProductsAdapter.MyViewHolder(v);
    }
}
