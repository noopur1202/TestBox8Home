package com.workstation.box8home;

import com.squareup.picasso.Picasso;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>{

    private List<ModelCategoriesMain> categoryList;
    private List<String> categoryImageList;
    private Context context;


    /**
     * View holder class
     * */
    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView categoryname;
        public ImageView itemImage;

        public MyViewHolder(View view) {
            super(view);
            categoryname = (TextView) view.findViewById(R.id.name);
            itemImage = (ImageView)view.findViewById(R.id.product_image);

            itemImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position  =   getAdapterPosition();
                    Intent productList = new Intent(context,ProductListActivity.class);
                    productList.putExtra("category",position);
                    productList.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(productList);
                }
            });
        }
    }

    public HomeAdapter(List<ModelCategoriesMain> categoryList,List<String> categoryImageList,Context context) {
        this.categoryList = categoryList;
        this.categoryImageList = categoryImageList;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ModelCategoriesMain c = categoryList.get(position);
        holder.categoryname.setText(c.name);

        Picasso.with(context)
               .load(categoryImageList.get(position))
               .fit()
               .into(holder.itemImage);
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                               .inflate(R.layout.main_list_single,parent, false);
        return new MyViewHolder(v);
    }
}
