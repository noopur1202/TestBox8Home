package com.workstation.box8home;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private List<ModelCategoriesMain> countryList= new ArrayList<>();
    public static List<List<HashMap<String, String>>> productListAll = new ArrayList<List<HashMap<String, String>>>();
    List<ModelCategoriesMain> categoryList = new ArrayList<ModelCategoriesMain>();
    RecyclerView rv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = (RecyclerView) findViewById(R.id.recycler_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        new getData().execute();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_profile) {

        } else if (id == R.id.nav_track_order) {

        } else if (id == R.id.nav_past_order) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @SuppressLint("StaticFieldLeak")
    private class getData extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            HttpHandler sh = new HttpHandler();

            String jsonStr = JsonData.productList;

            Log.e("LOG_TAG", "Response from json: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    JSONArray food = jsonObj.getJSONArray("food");

                    // looping through All Contacts
                    for (int i = 0; i < food.length(); i++) {
                        JSONObject c = food.getJSONObject(i);

                        String category = c.getString("Category");
                        categoryList.add(new ModelCategoriesMain(category));

                        // product node is JSON Object
                        JSONArray products = c.getJSONArray("products");
                        List<HashMap<String, String>> productList = new ArrayList<HashMap<String, String>>();

                        for (int j = 0; j < products.length(); j++) {
                        JSONObject p = products.getJSONObject(j);
                        String name = p.getString("name");
                        String url = p.getString("url");

                            // tmp hash map for single product
                            HashMap<String, String> product = new HashMap<>();

                            // adding each child node to HashMap key => value
                            product.put("name",name);
                            product.put("url", url);

                            productList.add(product);

                        }productListAll.add(productList);
                    }
                    Log.v("LOG_TAG","CATEGORIES : "+categoryList);
                    Log.v("LOG_TAG","PRODUCTS : "+productListAll);

                } catch (final JSONException e) {
                    Log.e("LOG_TAG", "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                           "Json parsing error: " + e.getMessage(),
                                           Toast.LENGTH_LONG)
                                 .show();
                        }
                    });
                }
            } else {
                Log.e("LOG_TAG", "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                       "Couldn't get json from server. Check LogCat for possible errors!",
                                       Toast.LENGTH_LONG)
                             .show();
                    }
                });
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            rv.setLayoutManager(llm);

            HomeAdapter ca = new HomeAdapter(categoryList,getApplicationContext());
            rv.setAdapter(ca);
        }
    }
}
