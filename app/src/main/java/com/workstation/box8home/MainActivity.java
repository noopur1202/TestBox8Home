package com.workstation.box8home;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private List<ModelCategoriesMain> countryList= new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rv = (RecyclerView) findViewById(R.id.recycler_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        createList();

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);

        HomeAdapter ca = new HomeAdapter(countryList);
        rv.setAdapter(ca);
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

    private void createList() {
        countryList.add(new ModelCategoriesMain("Italy", 59.83));
        countryList.add(new ModelCategoriesMain("France", 66.03));
        countryList.add(new ModelCategoriesMain("Germany", 80.62));
        countryList.add(new ModelCategoriesMain("Spain", 46.77));
        countryList.add(new ModelCategoriesMain("Portugal", 10.46));
        countryList.add(new ModelCategoriesMain("Austria", 8.47));
        countryList.add(new ModelCategoriesMain("Netherlands", 16.8));
        countryList.add(new ModelCategoriesMain("Belgium", 11.2));
        countryList.add(new ModelCategoriesMain("Denmark", 5.67));
        countryList.add(new ModelCategoriesMain("Ireland", 4.63));
        countryList.add(new ModelCategoriesMain("Norway", 5.19));
        countryList.add(new ModelCategoriesMain("Sweden", 9.85));
        countryList.add(new ModelCategoriesMain("Finland", 5.47));
        countryList.add(new ModelCategoriesMain("Greece", 10.76));

    }
}
