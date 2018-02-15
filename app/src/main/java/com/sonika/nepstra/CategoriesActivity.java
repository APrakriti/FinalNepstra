package com.sonika.nepstra;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.sonika.nepstra.Navigations.ArtAndCraft;
import com.sonika.nepstra.Navigations.Womens;
import com.sonika.nepstra.adapters.CategoryAdapter;
import com.sonika.nepstra.parser.JsonParserA;
import com.sonika.nepstra.pojo.MyData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class CategoriesActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        new ProductAsyncTask().execute();
    }



    class ProductAsyncTask extends AsyncTask<String, String, String> {

        ProgressDialog mprogressDialog;
        ListView listView_category;
        int flag;
        List<MyData> data_list = new ArrayList<>();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


            mprogressDialog = new ProgressDialog(CategoriesActivity.this);
            mprogressDialog.setMessage("Please wait");
            mprogressDialog.setCancelable(false);
            mprogressDialog.show();


        }

        @Override
        protected String doInBackground(String... params) {
            HashMap<String, String> loginHashMap = new HashMap<>();
            JsonParserA jsonParser = new JsonParserA();
            JSONObject jsonObject = jsonParser.performPostCI("https://nepstra.com/api/android/categories.php", loginHashMap);
            // Log.e("monkey", "sam");
            String request = "";

            try {
                if (jsonObject == null) {
                    flag = 1;

                } else if (jsonObject.getString("status").equals("success")) {
                    JSONArray jsonArray=jsonObject.getJSONArray("data");
                    System.out.println(jsonArray.length());


                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject object = jsonArray.getJSONObject(i);
                        Log.e("monkey", "prakriti");

                        Integer id = object.getInt("id");
                        String name = object.getString("name");
                        String slug = object.getString("slug");
                        Integer parent = object.getInt("parent");
                        Log.e("monkey", "prak");
                        String description = object.getString("description");
                        String display = object.getString("display");
                        Log.e("monkey", "sonika");


                        // Image node is JSON Object
                        JSONObject image = object.getJSONObject("image");
                        Integer image_id  = image.getInt("id");
                        String date_created = image.getString("date_created");
                        String date_created_gmt =image.getString("date_created_gmt");
                        String date_modified =image.getString("date_modified");
                        String date_modified_gmt =image.getString("date_modified_gmt");
                        String src =image.getString("src");
                        String title =image.getString("title");
                        String alt =image.getString("alt");
                        Log.e("monkey", "pratik");


                        //same as above
                        Integer menu_order = object.getInt("menu_order");
                        Integer count = object.getInt("count");


                        JSONObject _links = object.getJSONObject("_links");


                        JSONArray self = _links.getJSONArray("self");

                        String self_href = self.getJSONObject(0).getString("href");


                        JSONArray collection = _links.getJSONArray("collection");

                        String collection_href = collection.getJSONObject(0).getString("href");
                        //String character = FoodArray.getJSONObject(i).getString("char");
                        //   JSONArray Self = object.getJSONArray("self");
                        //  for (int i = 0; i < Self.length(); i++) {

                        //     String character = Self.getJSONObject(i).getString("href");

                        //   JSONObject status = response.getJSONObject("status");
                        MyData myData= new MyData(id, count, menu_order, parent,image_id,name, slug, description, display,date_created, date_created_gmt, date_modified,date_modified_gmt, src, title, alt, self_href, collection_href);
                        // Log.e("monkey", "prakriti");

                        data_list.add(myData);
                        flag = 2;
                    }

                }
                else {
                    flag = 3;
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
            } else if (flag == 2) {
                //mAdapter.notifyDataSetChanged();
                Toast.makeText(CategoriesActivity.this, "Success", Toast.LENGTH_SHORT).show();
                listView_category= (ListView) findViewById(R.id.list_category);
                Log.e("monkey" , String.valueOf(data_list.size()));
                CategoryAdapter mAdapter = new CategoryAdapter(CategoriesActivity.this, data_list, R.layout.category_list);
                listView_category.setAdapter(mAdapter);
                }

            else if(flag == 3) {
                Toast.makeText(CategoriesActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
            }


        }

    }


}




