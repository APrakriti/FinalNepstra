package com.sonika.nepstra.Navigations;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.sonika.nepstra.OrderedProducts;
import com.sonika.nepstra.R;
import com.sonika.nepstra.adapters.ArtAndCraftAdapter;
import com.sonika.nepstra.adapters.NewArrivalAdapter;
import com.sonika.nepstra.helpers.ArtAndCraftHelper;
import com.sonika.nepstra.helpers.NewArrivalsHelper;
import com.sonika.nepstra.helpers.OrderHelper;
import com.sonika.nepstra.pojo.ArtAndCraft_pojo;
import com.sonika.nepstra.pojo.Newarrivals_pojo;
import com.sonika.nepstra.pojo.OrderedProducts_pojo;

import java.util.ArrayList;
import java.util.List;

public class ArtAndCraft extends AppCompatActivity {
    ListView lv;
    ArtAndCraftHelper dbhelper;
    ArtAndCraftAdapter artandcraftadapter;
    List<ArtAndCraft_pojo> artPoductsList = new ArrayList<>();
    OrderHelper orderHelper;
    List<OrderedProducts_pojo> cartlist = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_art_and_craft);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        dbhelper = new ArtAndCraftHelper(this);
        orderHelper = new OrderHelper(this);
        lv = (ListView) findViewById(R.id.artandcraft_productlist);
        showartProducts();
    }

    private void showartProducts() {
        artPoductsList = dbhelper.getartandcraft();
        for (int i = 0; i < artPoductsList.size(); i++)

        {
            final ArtAndCraft_pojo info = artPoductsList.get(i);
            artandcraftadapter = new ArtAndCraftAdapter(this, artPoductsList,R.layout.artandcraft_list);
            lv.setAdapter(artandcraftadapter);
            lv.deferNotifyDataSetChanged();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem menuItem = menu.findItem(R.id.action_shop);
        cartlist = orderHelper.getOrderMessage();

        int mCount = cartlist.size();
        Log.e("totalitems", String.valueOf(mCount));
        invalidateOptionsMenu();
        //menu.setIcon(buildCounterDrawable(mCount, R.drawable.cart));
        menuItem.setIcon(buildCounterDrawable(mCount, R.drawable.cart));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int id = item.getItemId();
        if (id == R.id.action_shop)
        {
            Intent checkoutIntent = new Intent(ArtAndCraft.this, OrderedProducts.class);
            startActivity(checkoutIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);}

    private Drawable buildCounterDrawable(int count, int backgroundImageId) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.cart, null);
        view.setBackgroundResource(backgroundImageId);


        if (count == 0) {
            View counterTextPanel = view.findViewById(R.id.counterValuePanel);
            counterTextPanel.setVisibility(View.VISIBLE);


        } else {

            TextView counttv = view.findViewById(R.id.count);

            counttv.setText(" " + count);
        }

        view.measure(
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());

        view.setDrawingCacheEnabled(true);
        view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);

        return new BitmapDrawable(getResources(), bitmap);
    }


}

