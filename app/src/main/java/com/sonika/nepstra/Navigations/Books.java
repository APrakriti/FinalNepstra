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
import android.widget.Toast;

import com.sonika.nepstra.OrderedProducts;
import com.sonika.nepstra.R;
import com.sonika.nepstra.adapters.ArtAndCraftAdapter;
import com.sonika.nepstra.adapters.BooksAdapter;
import com.sonika.nepstra.adapters.NewArrivalAdapter;
import com.sonika.nepstra.helpers.ArtAndCraftHelper;
import com.sonika.nepstra.helpers.BooksHelper;
import com.sonika.nepstra.helpers.NewArrivalsHelper;
import com.sonika.nepstra.helpers.OrderHelper;
import com.sonika.nepstra.pojo.ArtAndCraft_pojo;
import com.sonika.nepstra.pojo.Books_Pojo;
import com.sonika.nepstra.pojo.Newarrivals_pojo;
import com.sonika.nepstra.pojo.OrderedProducts_pojo;

import java.util.ArrayList;
import java.util.List;

public class Books extends AppCompatActivity {
    ListView lv;
    BooksHelper dbhelper;
    BooksAdapter booksAdapter;
    List<Books_Pojo> books_pojoList = new ArrayList<>();
    OrderHelper orderHelper;
    List<OrderedProducts_pojo> cartlist = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        Toast.makeText(this, "books activity here", Toast.LENGTH_SHORT).show();
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        dbhelper = new BooksHelper(this);
        orderHelper = new OrderHelper(this);
        lv = (ListView) findViewById(R.id.books_productlist);
        showartProducts();
    }

    private void showartProducts() {
        books_pojoList = dbhelper.getbooks();
        for (int i = 0; i < books_pojoList.size(); i++)

        {
            final Books_Pojo info = books_pojoList.get(i);
            booksAdapter = new BooksAdapter(this, books_pojoList,R.layout.books_list);
            lv.setAdapter(booksAdapter);
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
            Intent checkoutIntent = new Intent(Books.this, OrderedProducts.class);
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

