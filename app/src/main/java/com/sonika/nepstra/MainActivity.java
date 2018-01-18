package com.sonika.nepstra;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.sonika.nepstra.Navigations.ArtAndCraft;
import com.sonika.nepstra.Navigations.Jwellery;
import com.sonika.nepstra.Navigations.Kids;
import com.sonika.nepstra.Navigations.Mens;
import com.sonika.nepstra.Navigations.NewArrival;
import com.sonika.nepstra.Navigations.Sports;
import com.sonika.nepstra.Navigations.Womens;
import com.sonika.nepstra.adapters.MyOrderAdpater;

import com.sonika.nepstra.helpers.MySharedPreference;
import com.sonika.nepstra.listener.ListViewListener;
import com.sonika.nepstra.pojo.AllProducts;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    CarouselView carouselview;
    MySharedPreference sharedPreference;
    ArrayAdapter<CharSequence> adapter;
    BottomBar bottomBar;
    ViewPager viewPager;
    int[] images = {R.drawable.nepstrab, R.drawable.nepstraa, R.drawable.nepstrac};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);

        carouselview = (CarouselView) findViewById(R.id.carouselview);
        carouselview.setPageCount(images.length);
        carouselview.setImageListener(imagelistener);
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

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        MenuItem menuItem = menu.findItem(R.id.action_shop);
//        int mCount = sharedPreference.retrieveProductCount();
//        menu.clear();
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        final int id = item.getItemId();
//
//        if (id == R.id.action_shop)
//        {
//            Intent checkoutIntent = new Intent(getContext(), OrderedProducts.class);
//            startActivity(checkoutIntent);
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    ImageListener imagelistener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(images[position]);
        }
    };

//        @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//            //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//            return super.onOptionsItemSelected(item);
//    }

//    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_new_arrival) {
            Intent i = new Intent(MainActivity.this, NewArrival.class);
            startActivity(i);
        } else if (id == R.id.nav_mens) {
            Intent i = new Intent(MainActivity.this, Mens.class);
            startActivity(i);

        } else if (id == R.id.nav_womens)
        {
            Intent i = new Intent(MainActivity.this, Womens.class);
            startActivity(i);

        } else if (id == R.id.nav_contact) {

        } else if (id == R.id.nav_category) {

        } else if (id == R.id.nav_arts_and_craft) {

            Intent i = new Intent(MainActivity.this, ArtAndCraft.class);
            startActivity(i);

        }
        else if (id == R.id.nav_my_orders) {
            SharedPreferences loginpref = getSharedPreferences("LOGINPREF", MODE_PRIVATE);
            Intent i = null;


            if (loginpref.getBoolean("login", true))
            { Log.e("login", "myorder");
                i = new Intent(MainActivity.this, MyOrders.class);
                startActivity(i);
            }
            else
            {
                Toast.makeText(this, "you must login first", Toast.LENGTH_SHORT).show();
            }
        }


        else if (id == R.id.nav_about_us) {
            Intent intentAboutUs = new Intent(this, LoginVolley.class);
            startActivity(intentAboutUs);

        }
        else if (id == R.id.nav_books) {

            Intent i = new Intent(MainActivity.this, Kids.class);
            startActivity(i);

        }
        else if (id == R.id.nav_jewellry) {
            Intent i = new Intent(this, Jwellery.class);
            startActivity(i);
        }
        else if (id == R.id.nav_sports) {
            Intent i = new Intent(this, Sports.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
