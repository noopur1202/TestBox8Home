package com.workstation.box8home;

import com.squareup.picasso.Picasso;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.MyViewHolder> {

    private List<Modelproducts> productList;
    private Context context;


    /**
     * View holder class
     * */
    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView productName;
        public ImageView itemImage;

        public MyViewHolder(View view) {
            super(view);
            productName = (TextView) view.findViewById(R.id.sub_product_name);
            itemImage = (ImageView)view.findViewById(R.id.sub_product_image);
        }
    }

    public ProductsAdapter(List<Modelproducts> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(ProductsAdapter.MyViewHolder holder, int position) {
        Modelproducts c = productList.get(position);
        holder.productName.setText(c.name);
        Picasso.with(context)
               .load(c.url)
               .fit()
               .into(holder.itemImage);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    @Override
    public ProductsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                               .inflate(R.layout.sub_list_single,parent, false);
        return new ProductsAdapter.MyViewHolder(v);
    }
}
