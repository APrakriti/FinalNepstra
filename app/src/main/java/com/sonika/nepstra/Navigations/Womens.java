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
import com.sonika.nepstra.adapters.OrderAdapter;
import com.sonika.nepstra.adapters.WomenAdapter;
import com.sonika.nepstra.helpers.OrderHelper;
import com.sonika.nepstra.helpers.WomenHelper;
import com.sonika.nepstra.pojo.OrderedProducts_pojo;
import com.sonika.nepstra.pojo.WomenPoducts_pojo;

import java.util.ArrayList;
import java.util.List;

public class Womens extends AppCompatActivity {
    ListView lv;
    WomenHelper dbhelper;
    WomenAdapter womenAdapter;
    List<WomenPoducts_pojo> womenPoductsList = new ArrayList<>();
    OrderHelper orderHelper;
    List<OrderedProducts_pojo> cartlist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_womens);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        dbhelper = new WomenHelper(this);
        orderHelper = new OrderHelper(this);
        lv = (ListView) findViewById(R.id.women_productlist);
        showWomenProducts();
    }

    private void showWomenProducts() {

        womenPoductsList = dbhelper.getwomen();
        for (int i = 0; i < womenPoductsList.size(); i++) {
            final WomenPoducts_pojo info = womenPoductsList.get(i);
            womenAdapter= new WomenAdapter(this, womenPoductsList, R.layout.women_list);
            lv.setAdapter(womenAdapter);
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
            Intent checkoutIntent = new Intent(Womens.this, OrderedProducts.class);
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
