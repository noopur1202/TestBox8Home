package com.workstation.box8home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class ProductListActivity extends AppCompatActivity {

    RecyclerView rvp;
    int categoryPosition;
    List<HashMap<String, String>> subList = new ArrayList<HashMap<String, String>>();
    List<Modelproducts> productList = new ArrayList<Modelproducts>();
    ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_product_list);

        rvp = (RecyclerView) findViewById(R.id.recycler_view_products);
        backButton = (ImageView) findViewById(R.id.product_list_back);

        categoryPosition = Objects.requireNonNull(getIntent().getExtras()).getInt("category");

        subList.addAll(MainActivity.productListAll.get(categoryPosition));

        for (int i=0;i<subList.size();i++){

            String productname = subList.get(i).get("name");
            String productImageUrl = subList.get(i).get("url");
            productList.add(new Modelproducts(productname,productImageUrl));
        }

        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvp.setLayoutManager(llm);

        ProductsAdapter productsAdapter = new ProductsAdapter(productList,getApplicationContext());
        rvp.setAdapter(productsAdapter);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getApplicationContext().startActivity(new Intent(ProductListActivity.this,MainActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
    }
}
