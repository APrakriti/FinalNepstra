package com.sonika.nepstra.fragments;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.sonika.nepstra.OrderedProducts;

import com.sonika.nepstra.R;
import com.sonika.nepstra.adapters.AllProductAdapter;
import com.sonika.nepstra.helpers.OrderHelper;
import com.sonika.nepstra.parser.JsonParserA;
import com.sonika.nepstra.pojo.AllProducts;
import com.sonika.nepstra.pojo.OrderedProducts_pojo;
import com.synnapps.carouselview.CarouselView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class All_products_fragment extends Fragment
        implements SearchView.OnQueryTextListener,SearchView.OnCloseListener {
    int flag = 0;
    RecyclerView mRecyclerView;
    List<AllProducts> allProductList = new ArrayList<AllProducts>();

    OrderHelper orderHelper;
    List<OrderedProducts_pojo> cartlist = new ArrayList<>();
    AllProductAdapter allProductAdapter = null;
    SearchView search;
    CarouselView carouselView;
    ScrollView scrollViewa;
    List<AllProducts> savedList=new ArrayList<>();
    InputMethodManager imm;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_all_products, container, false);
        imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

        orderHelper = new OrderHelper(getContext());
        allProductAdapter = new AllProductAdapter(getContext(), allProductList );
        search= v.findViewById(R.id.search_it);
        carouselView = v.findViewById(R.id.carouselview);
        scrollViewa = v.findViewById(R.id.scroll_viewa);
        search.setQueryHint("Search");
        setHasOptionsMenu(true);
        search.setOnQueryTextListener(this);
        search.setOnCloseListener(this);
        search.setInputType(InputType.TYPE_CLASS_TEXT);

//        v.findViewById(R.id.carouselview).requestFocus();


        perform(v);
        return v;
    }


    private void perform(View v) {
        new AllProductsAsyncTask().execute();
    }

    @Override
    public boolean onClose() {

        carouselView.setVisibility(View.VISIBLE);
        filterData("");
        // expandAll();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {

        if (query.isEmpty() == false)
        {
            carouselView.setVisibility(View.GONE);
        }
        else {
            carouselView.setVisibility(View.VISIBLE);
      }
          filterData(query);

        // alladapter.filter(query);
        //displayList();
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String newText) {

        carouselView.setVisibility(View.VISIBLE);
        filterData(newText);
        // displayList();
        return false;
    }
    public void filterData(String charText){
        charText = charText.toLowerCase(Locale.getDefault());

        allProductList.clear();

        if (charText.length() == 0) {

            allProductList.addAll(savedList);
        } else {

                for (AllProducts wp : savedList) {
                if (wp.getName().toLowerCase(Locale.getDefault()).contains(charText)) {

                    allProductList.add(wp);
                }
            }
             }

        allProductAdapter.notifyDataSetChanged();
    }



    class AllProductsAsyncTask extends AsyncTask<String, String, String> {
        ProgressDialog mprogressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mprogressDialog = new ProgressDialog(getContext());
            mprogressDialog.setMessage("Please wait");
            mprogressDialog.setCancelable(false);
            mprogressDialog.show();

        }

        @Override
        protected String doInBackground(String... params) {
            HashMap<String, String> loginHashMap = new HashMap<>();
            JsonParserA jsonParser = new JsonParserA();
            JSONObject jsonObject = jsonParser.performPostCI
                    ("https://nepstra.com/api/android/products.php", loginHashMap);
            try {
                if (jsonObject == null) {
                    flag = 1;
                } else {

                    if (jsonObject.getString("status").equals("success")) {

                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject dataObject = jsonArray.getJSONObject(i);

                            Integer id = dataObject.getInt("id");
                            String name = dataObject.getString("name");
                            String slug = dataObject.getString("slug");
                            String permalink = dataObject.getString("permalink");
                            String date_created = dataObject.getString("date_created");
                            String date_created_gmt = dataObject.getString("date_created_gmt");
                            String date_modified = dataObject.getString("date_modified");
                            String date_modified_gmt = dataObject.getString("date_modified_gmt");

                            String type = dataObject.getString("type");
                            String status = dataObject.getString("status");
                            Boolean featured = dataObject.getBoolean("featured");
                            String catalog_visibility = dataObject.getString("catalog_visibility");
                            String description = dataObject.getString("description");
                            String short_description = dataObject.getString("short_description");
                            String sku = dataObject.getString("sku");
                            String price = dataObject.getString("price");
                            String regular_price = dataObject.getString("regular_price");

                            String sale_price = dataObject.getString("sale_price");

                            Boolean date_on_sale_from = dataObject.isNull("date_on_sale_from");
                            Boolean date_on_sale_from_gmt = dataObject.isNull("date_on_sale_from_gmt");
                            Boolean date_on_sale_to = dataObject.isNull("date_on_sale_to");
                            Boolean date_on_sale_to_gmt = dataObject.isNull("date_on_sale_to_gmt");

                            String price_html = dataObject.getString("price_html");

                            Boolean on_sale = new Integer(1).equals(dataObject.getBoolean("on_sale"));
                            Boolean purchasable = new Integer(1).equals(dataObject.getBoolean("purchasable"));

                            Integer total_sales = dataObject.getInt("total_sales");
                            Boolean virtual = dataObject.getBoolean("virtual");
                            Boolean downloadable = dataObject.getBoolean("downloadable");


                            JSONArray downloads = dataObject.getJSONArray("downloads");
                            Integer download_limit = dataObject.getInt("download_limit");
                            Integer download_expiry = dataObject.getInt("download_expiry");
                            String external_url = dataObject.getString("external_url");
                            String button_text = dataObject.getString("button_text");
                            String tax_status = dataObject.getString("tax_status");
                            String tax_class = dataObject.getString("tax_class");

                            Boolean manage_stock = dataObject.getBoolean("manage_stock");
                            Boolean stock_quantity = dataObject.isNull("stock_quantity");
                            Boolean in_stock = dataObject.getBoolean("in_stock");

                            String backorders = dataObject.getString("backorders");
                            Boolean backorders_allowed = dataObject.getBoolean("backorders_allowed");
                            Boolean backordered = dataObject.getBoolean("backordered");
                            Boolean sold_individually = dataObject.getBoolean("sold_individually");

                            String weight = dataObject.getString("weight");

                            JSONObject dimensionObject = dataObject.getJSONObject("dimensions");
                            String length = dimensionObject.getString("length");
                            String width = dimensionObject.getString("width");
                            String height = dimensionObject.getString("height");


                            Boolean shipping_required = dataObject.getBoolean("shipping_required");
                            Boolean shipping_taxable = dataObject.getBoolean("shipping_taxable");

                            String shipping_class = dataObject.getString("shipping_class");
                            Integer shipping_class_id = dataObject.getInt("shipping_class_id");
                            Boolean reviews_allowed = dataObject.getBoolean("reviews_allowed");
                            String average_rating = dataObject.getString("average_rating");
                            Integer rating_count = dataObject.getInt("rating_count");
                            JSONArray related_ids = dataObject.getJSONArray("related_ids");

                            JSONArray upsell_ids = dataObject.getJSONArray("upsell_ids");
                            JSONArray cross_sell_ids = dataObject.getJSONArray("cross_sell_ids");
                            Integer parent_id = dataObject.getInt("parent_id");
                            String purchase_note = dataObject.getString("purchase_note");

                            JSONArray categories_array = dataObject.getJSONArray("categories");
                            Integer c_id = null;
                            String c_name = null;
                            String c_slug = null;
                            for (int j = 0; j < categories_array.length(); j++) {
                                c_id = categories_array.getJSONObject(j).getInt("id");
                                c_name = categories_array.getJSONObject(j).getString("name");
                                c_slug = categories_array.getJSONObject(j).getString("slug");

                            }

                            JSONArray tags = dataObject.getJSONArray("tags");
                            Integer tag_id = null;
                            String tag_name = null;
                            String tag_slug = null;
                            for (int o =0; o < tags.length(); o++) {
                                tag_id = tags.getJSONObject(o).getInt("id");
                                tag_name = tags.getJSONObject(o).getString("name");
                                tag_slug = tags.getJSONObject(o).getString("slug");
                            }

                            JSONArray image_array = dataObject.getJSONArray("images");
                            Integer i_id = null;
                            Integer i_position = null;
                            String i_date_created = null;
                            String i_date_created_gmt = null;
                            String i_date_modified = null;
                            String i_date_modified_gmt = null;
                            String i_src = null;
                            String i_name = null;
                            String i_alt = null;
                            for (int k = 0; k < image_array.length(); k++) {
                                i_id = image_array.getJSONObject(k).getInt("id");
                                i_date_created = image_array.getJSONObject(k).getString("date_created");
                                i_date_created_gmt = image_array.getJSONObject(k).getString("date_created_gmt");
                                i_date_modified = image_array.getJSONObject(k).getString("date_modified");
                                i_date_modified_gmt = image_array.getJSONObject(k).getString("date_modified_gmt");
                                i_src = image_array.getJSONObject(k).getString("src");
                                i_name = image_array.getJSONObject(k).getString("name");
                                i_alt = image_array.getJSONObject(k).getString("alt");
                                i_position = image_array.getJSONObject(k).getInt("position");
                                Log.e("imagevitra", "imagevitra");
                            }
                            JSONArray attributes = dataObject.getJSONArray("attributes");
                            JSONArray default_attributes = dataObject.getJSONArray("default_attributes");
                            JSONArray variations = dataObject.getJSONArray("variations");
                            JSONArray grouped_products = dataObject.getJSONArray("grouped_products");

                            Integer menu_order = dataObject.getInt("menu_order");

                            JSONArray meta_data_array= dataObject.getJSONArray("meta_data");
                            Integer m_id = null;
                            String m_key = null;
                            String m_value = null;
                            for (int l = 0; l < meta_data_array.length(); l++) {
                                m_id = meta_data_array.getJSONObject(l).getInt("id");
                                m_key = meta_data_array.getJSONObject(l).getString("key");
                                m_value = meta_data_array.getJSONObject(l).getString("value");
                                Log.e("metadata", "metadata");
                            }

                            JSONObject _links = dataObject.getJSONObject("_links");

                            JSONArray self_array = _links.getJSONArray("self");
                            String self_href = null;
                            for (int m = i; m < self_array.length(); m++) {
                                self_href = self_array.getJSONObject(0).getString("href");
                            }
                            JSONArray collection_array = _links.getJSONArray("collection");
                            String collection_href = null;
                            for (int n = i; n < collection_array.length(); n++) {
                                collection_href = collection_array.getJSONObject(0).getString("href");
                            }

                            AllProducts allProducts =
                                    new AllProducts(id,total_sales,download_limit,download_expiry, shipping_class_id,rating_count, parent_id,c_id,i_id,i_position,menu_order,m_id,tag_id,m_key,m_value,price,name,slug,permalink,date_created,date_created_gmt,date_modified,date_modified_gmt,type,status,weight,catalog_visibility,description,short_description,sku,regular_price,sale_price,price_html,external_url,button_text,tag_id,tax_status,tax_class,backorders,length,width,height,shipping_class,purchase_note,average_rating,c_name,c_slug,i_date_created,i_date_created_gmt,i_date_modified,i_date_modified_gmt,i_src,i_name,i_alt,self_href,collection_href,tag_name,tag_slug,downloads,related_ids,upsell_ids,cross_sell_ids,tags,attributes,default_attributes,variations,grouped_products,featured,date_on_sale_from,date_on_sale_from_gmt,date_on_sale_to,date_on_sale_to_gmt,on_sale,purchasable,virtual,downloadable,manage_stock,stock_quantity,in_stock,backorders_allowed,backordered,sold_individually,shipping_required,shipping_taxable,reviews_allowed);
                            allProductList.add(allProducts);
                            savedList.add(allProducts);
                            flag = 2;
                        }

                    }
                    else {
                        flag = 3;
                    }
                }
            } catch (JSONException e) {

            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            mprogressDialog.dismiss();
            if (flag == 1) {
                Toast.makeText(getContext(), "Server/Network issue", Toast.LENGTH_SHORT).show();

            } else if (flag == 2) {
                Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                mRecyclerView = getView().findViewById(R.id.recycler_view2);
                GridLayoutManager mGrid = new GridLayoutManager(getContext(), 2);
                mRecyclerView.setLayoutManager(mGrid);
                mRecyclerView.setHasFixedSize(true);
                mRecyclerView.setNestedScrollingEnabled(false);


                allProductAdapter = new AllProductAdapter(getContext(), allProductList);
                mRecyclerView.setAdapter(allProductAdapter);


            } else {
                Toast.makeText(getContext(), "Invalid credentials", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Log.e("menuvitra", "cart");
        inflater.inflate(R.menu.menu_main, menu);
        MenuItem menuItem = menu.findItem(R.id.action_shop);
        //int mCount = sharedPreference.retrieveProductCount();
        //cartListener.showNumberOfRecords();
        //int mCount = sharedPreference.retrieveProductCount();
        cartlist = orderHelper.getOrderMessage();

        int mCount = cartlist.size();
        Log.e("totalitems", String.valueOf(mCount));
        getActivity().invalidateOptionsMenu();
        menuItem.setIcon(buildCounterDrawable(mCount, R.drawable.cart));
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int id = item.getItemId();
        if (id == R.id.action_shop)
        {
            Intent checkoutIntent = new Intent(getContext(), OrderedProducts.class);
            startActivity(checkoutIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private Drawable buildCounterDrawable(int count, int backgroundImageId) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
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
