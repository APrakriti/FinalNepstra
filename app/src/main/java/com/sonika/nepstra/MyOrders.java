//package com.sonika.nepstra;
//
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.ListView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.android.volley.AuthFailureError;
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.RetryPolicy;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//
//////import org.json.JSONException;
//////import org.json.JSONObject;
//////
//////import java.util.HashMap;
//////import java.util.List;
//////import java.util.Map;
//////
//public class MyOrders extends AppCompatActivity {
//    ////    ListView listView_myorders;
//////    MyOrdersAdapter myOrdersAdapter;
//    TextView myorders_tv;
//
//    ////
//////
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_my_orders);
//        myorders_tv = (TextView) findViewById(R.id.tv_myorders);
//    }
//}
////        SharedPreferences sp = getSharedPreferences("LOGINPREF", MODE_PRIVATE);
////        String semail = sp.getString("email", "");
////        Log.e("ullu", semail);
////
////        RequestQueue queue = Volley.newRequestQueue(MyOrders.this);
////
////        StringRequest sr = new StringRequest(Request.Method.POST,
////                "https://nepstra.com/api/android/verifyuser.php?email="+semail,
////                //  https://nepstra.com/api/android/verifyuser.php/?email=send_correct_user&pass=send_correct_password
////                //  http://nepstra.com/api/android/verifyuser.php?email=email&pass=password
////                // https://nepstra.com/api/android/verifyuser.php/?user=send_correct_user&pass=send_correct_password
////                new Response.Listener<String>() {
////                    @Override
////                    public void onResponse(String response) {
////                        Log.e("response", response);
////                        try {
////                            JSONObject jsonObject = new JSONObject(response);
////                            Log.e("simi", "monkey");
////                            String status = jsonObject.getString("status");
//////                                        String message = jsonObject.getString("message");
////////                                      Integer data = jsonObject.getInt(String.valueOf(1)); //yo k gareko?? data =
////                            Log.e("status",status);
////
//////                            if(status.equals("success")){
//////                                Intent i = new Intent(MyOrders.this, MainActivity.class);
//////                                startActivity(i);
//////                                }
//////
//////                            else if (status.equals("error")) {
//////
//////                                Toast.makeText(MyOrders.this, "Wrong email adddress", Toast.LENGTH_SHORT).show();
//////                            }
////
////                        } catch (JSONException e) {
////                            e.printStackTrace();
////                        }
////
////
////
////                        //  msgResponse.setText(response.toString());
////
////                        Log.e("HttpClientlogin", "success! response: " + response.toString());
////                    }},
////                new Response.ErrorListener() {
////                    @Override
////                    public void onErrorResponse(VolleyError error) {
////                        Log.e("HttpClientlogin", "error: " + error.toString());
////                    }
////                }) {
////            @Override
////            protected Map<String, String> getParams() {
////                Map<String, String> params = new HashMap<String, String>();
//////                            params.put("email", semail);
//////                            params.put("pass", spassword);
////                return params;
////            }
////
////            @Override
////            public Map<String, String> getHeaders() throws AuthFailureError {
////                Map<String, String> params = new HashMap<String, String>();
////                params.put("Content-Type", "application/x-www-form-urlencoded");
////                return params;
////            }
////        };
////        sr.setRetryPolicy(new RetryPolicy() {
////            @Override
////            public int getCurrentTimeout() {
////                return 50000;
////            }
////
////            @Override
////            public int getCurrentRetryCount() {
////                return 50000;
////            }
////
////            @Override
////            public void retry(VolleyError error) throws VolleyError {
////
////            }
////        });
////        queue.add(sr);
////
////
////}}
package com.sonika.nepstra;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

//import com.sonika.nepstra.adapters.MyOrderAdapter;
import com.sonika.nepstra.adapters.MyOrderAdapter;
import com.sonika.nepstra.adapters.Mydetailsorder;
import com.sonika.nepstra.parser.JsonParserA;
import com.sonika.nepstra.pojo.Myorder;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyOrders extends AppCompatActivity {

    RecyclerView mRecyclerView;
    List<Myorder>allmyorderlist = new ArrayList<Myorder>();
    MyOrderAdapter myOrderAdpater = null;
   // MyOrderAdapter myOrderAdpater = null;

    int flag;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//        getSupportActionBar().setCustomView(R.layout.center_action_bar_text);

        new MyOrderAsyncTask().execute();
   }

    class MyOrderAsyncTask extends AsyncTask<String, String, String> {
        SharedPreferences sp = getSharedPreferences("LOGINPREF", MODE_PRIVATE);
        String ssemail = sp.getString("email", null);

        ProgressDialog mprogressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mprogressDialog = new ProgressDialog(MyOrders.this);
            mprogressDialog.setMessage("Please wait");
            mprogressDialog.setCancelable(false);
            mprogressDialog.show();

        }

        @Override
        protected String doInBackground(String... params) {
            HashMap<String, String> loginHashMap = new HashMap<>();
            JsonParserA jsonParser = new JsonParserA();
            Log.e("uemail", ssemail);
            JSONObject jsonObject = jsonParser.performPostCI
                    ("https://nepstra.com/api/android/customerorder.php?email="+ssemail, loginHashMap);
            try {
                if (jsonObject == null) {
                    flag = 1;
                } else {

                    if (jsonObject.getString("status").equals("success")) {

                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        JSONArray fee_lines = null;
                        JSONArray line_item_taxes = null;
                        JSONArray line_item_meta_data = null;
                        JSONArray tax_lines = null;
                        JSONArray tax_lines_taxes = null;
                        JSONArray coupon_lines = null;
                        JSONArray refunds = null;
                        Boolean prices_include_tax = null;
                        Boolean date_paid = null;
                        Boolean date_paid_gmt = null;
                        Boolean auffix = null;
                        Boolean padding = null;
                        String number = null;
                        String order_key = null;
                        String created_via = null;
                        String version = null;
                        String status = null;
                        String currence = null;
                        String date_modified = null;
                        String date_modified_gmt = null;
                        String discount_total = null;
                        String discount_tax = null;
                        String shipping_tax = null;
                        String customer_user_agent = null;
                        String customer_note = null;
                        Boolean date_completed = null;
                        String date_created = null;
                        String shipping_total = null;
                        String billing_adddress_2 = null;
                        String cart_tax = null;
                        String total = null;
                        String total_tax = null;
                        String customer_ip_address = null;
                        String billing_company = null;
                        String billing_address_1 = null;
                        String billing_last_name = null;
                        String billing_fname = null;
                        String billing_state = null;
                        String billing_postcode = null;
                        String billing_country = null;
                        String billing_email = null;
                        String billing_phone = null;
                        String shipping_fname = null;
                        String billing_city = null;
                        String date_created_gmt = null;
                        Boolean date_completed_gmt = null;
                        String shipping_city = null;
                        String shipping_adddress_2 = null;
                        String shippingbilling_address_1 = null;
                        String shipping_company = null;
                        String shipping_last_name = null;
                        String shipping_state = null;
                        String shipping_postcode = null;
                        String shipping_country = null;
                        String payment_method = null;
                        String payment_method_title = null;
                        String transaction_id = null;
                        String cart_hash = null;
                        String meta_data_key = null;
                        String meta_data_value = null;
                        String meta_data_value_formatted_number = null;
                        String meta_data_value_document_type = null;
                        String line_item_name = null;
                        String line_item_tax_class = null;
                        Boolean prefix = null;
                        String sku = null;
                        String line_item_total_tax = null;
                        String line_item_total = null;
                        String line_item_subtotal_tax = null;
                        String line_item_subtotal = null;
                        String shipping_method_title = null;
                        String shipping_method_id = null;
                        String shipping_line_total = null;
                        String shipping_total_taxes = null;
                        String shipping_meta_data_key = null;
                        String shipping_meta_data_value = null;
                        String links_self_href = null;
                        String links_collection_href = null;
                        String links_customer_href = null;
                        Integer id = null;
                        Integer parent_id = null;
                        Integer customer_id = null;
                        Integer meta_id = null;
                        Integer line_id = null;
                        Integer line_quantity = null;
                        Integer line_product_id = null;
                        Integer line_variation_id = null;
                        Integer line_price = null;
                        Integer shipping_lines_id = null;
                        Integer shipping_meta_data_id = null;
                        Integer meta_data_value_number = null;
                        Integer meta_data_value_order_id = null;
                        Log.e("pppp", "qqqq");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject dataObject = jsonArray.getJSONObject(i);
                            id = dataObject.getInt("id");
                            parent_id = dataObject.getInt("parent_id");
                            number = dataObject.getString("number");
                            order_key = dataObject.getString("order_key");
                            created_via = dataObject.getString("created_via");
                            version = dataObject.getString("version");
                            status = dataObject.getString("status");
                            currence = dataObject.getString("currency");
                            Log.e("pppp", "qqqq11");

                            date_created = dataObject.getString("date_created");
                            date_created_gmt = dataObject.getString("date_created_gmt");
                            date_modified = dataObject.getString("date_modified");
                            date_modified_gmt = dataObject.getString("date_modified_gmt");
                            discount_total = dataObject.getString("discount_total");
                            discount_tax = dataObject.getString("discount_tax");
                            shipping_total = dataObject.getString("shipping_total");
                            shipping_tax = dataObject.getString("shipping_tax");
                            cart_tax = dataObject.getString("cart_tax");
                            total = dataObject.getString("total");
                            total_tax = dataObject.getString("total_tax");
                            prices_include_tax = dataObject.getBoolean("prices_include_tax");
                            customer_id = dataObject.getInt("customer_id");
                            customer_ip_address = dataObject.getString("customer_ip_address");
                            customer_user_agent = dataObject.getString("customer_user_agent");
                            customer_note = dataObject.getString("customer_note");


                            JSONObject billing = dataObject.getJSONObject("billing");
                            billing_fname = billing.getString("first_name");
                            billing_last_name = billing.getString("last_name");
                            billing_company = billing.getString("company");
                            billing_address_1 = billing.getString("address_1");
                            billing_adddress_2 = billing.getString("address_2");
                            billing_city = billing.getString("city");
                            billing_state = billing.getString("state");
                            billing_postcode = billing.getString("postcode");
                            billing_country = billing.getString("country");
                            billing_email = billing.getString("email");
                            billing_phone = billing.getString("phone");


                            JSONObject shipping = dataObject.getJSONObject("shipping");
                            shipping_fname = shipping.getString("first_name");
                            shipping_last_name = shipping.getString("last_name");
                            shipping_company = shipping.getString("company");
                            shippingbilling_address_1 = shipping.getString("address_1");
                            shipping_adddress_2 = shipping.getString("address_2");
                            shipping_city = shipping.getString("city");
                            shipping_state = shipping.getString("state");
                            shipping_postcode = shipping.getString("postcode");
                            shipping_country = shipping.getString("country");


                            payment_method = dataObject.getString("payment_method");
                            payment_method_title = dataObject.getString("payment_method_title");
                            transaction_id = dataObject.getString("transaction_id");


                            date_paid = dataObject.isNull("date_paid");

                            date_paid_gmt = dataObject.isNull("date_paid_gmt");
                            date_completed = dataObject.isNull("date_completed");
                            date_completed_gmt = dataObject.isNull("date_completed_gmt");

                            cart_hash = dataObject.getString("cart_hash");
                            Log.e("pppp", "qqqq");


                            JSONArray meta_data_array = dataObject.getJSONArray("meta_data");

                            meta_id = null;
                            meta_data_key = null;
                            meta_data_value = null;
                            meta_data_value_number = null;
                            meta_data_value_formatted_number = null;
                            prefix = null;
                            auffix = null;
                            padding = null;
                            meta_data_value_document_type = null;
                            meta_data_value_order_id = null;
                            for (int j = 0; j < meta_data_array.length(); j++) {
                                JSONObject metaobject = meta_data_array.getJSONObject(j);
                                Log.e("yesudid", "pada");
                                meta_id = metaobject.getInt("id");
                                Log.e("yesudid1", "pada");
                                meta_data_key = metaobject.getString("key");
                                Log.e("yesudid2", "pada");

                                meta_data_value = metaobject.getString("value");
                                Log.e("yesudid3", "pada");

//                                meta_data_value = meta_data_value1.getString("value");
//                                meta_data_value_number = meta_data_value1.getInt("number");
//                                meta_data_value_formatted_number  = meta_data_value1.getString("formatted_number");
//                                prefix = meta_data_value1.isNull("prefix");
//                                auffix  = meta_data_value1.isNull("suffix");
//                                meta_data_value_document_type   = meta_data_value1.getString("document_type");
//                                meta_data_value_order_id   = meta_data_value1.getInt("order_id");
//                                padding= meta_data_value1.isNull("padding");

                            }


                            JSONArray line_item_array = dataObject.getJSONArray("line_items");

                            line_id = null;
                            line_item_name = null;
                            line_product_id = null;
                            line_variation_id = null;
                            line_quantity = null;
                            line_item_tax_class = null;
                            line_item_subtotal = null;
                            line_item_subtotal_tax = null;
                            line_item_total = null;
                            line_item_total_tax = null;
                            line_item_taxes = null;
                            line_item_meta_data = null;
                            sku = null;
                            line_price = null;


                            for (int j = 0; j < line_item_array.length(); j++) {
                                JSONObject lineItem_object = line_item_array.getJSONObject(j);
                                line_id = lineItem_object.getInt("id");
                                line_item_name = lineItem_object.getString("name");
                                line_product_id = lineItem_object.getInt("product_id");
                                line_variation_id = lineItem_object.getInt("variation_id");
                                line_quantity = lineItem_object.getInt("quantity");
                                line_item_tax_class = lineItem_object.getString("tax_class");
                                line_item_subtotal = lineItem_object.getString("subtotal");
                                line_item_subtotal_tax = lineItem_object.getString("subtotal_tax");
                                line_item_total = lineItem_object.getString("total");
                                line_item_total_tax = lineItem_object.getString("total_tax");
                                line_item_taxes = lineItem_object.getJSONArray("taxes");
                                line_item_meta_data = lineItem_object.getJSONArray("meta_data");
                                sku = lineItem_object.getString("sku");
                                line_price = lineItem_object.getInt("price");
                                Log.e("lune1", line_item_name);
                                Myorder myorder = new Myorder(line_item_taxes, line_item_meta_data, tax_lines, tax_lines_taxes,
                                        fee_lines, coupon_lines, refunds, prices_include_tax, date_paid, date_paid_gmt, date_completed, date_completed_gmt, prefix,
                                        auffix, padding, number, order_key, created_via, version, status, currence, date_created, date_created_gmt,
                                        date_modified, date_modified_gmt, discount_total, discount_tax, shipping_tax, shipping_total,
                                        cart_tax, total, total_tax, customer_ip_address, customer_user_agent, customer_note,
                                        billing_fname, billing_last_name, billing_company, billing_address_1, billing_adddress_2, billing_city,
                                        billing_state, billing_postcode, billing_country, billing_email, billing_phone, shipping_fname,
                                        shipping_last_name, shipping_company, shippingbilling_address_1, shipping_adddress_2, shipping_city,
                                        shipping_state, shipping_postcode, shipping_country, payment_method, payment_method_title, transaction_id,
                                        cart_hash,
                                        meta_data_key, meta_data_value, meta_data_value_formatted_number, meta_data_value_document_type, line_item_name, line_item_tax_class,
                                        line_item_subtotal, line_item_subtotal_tax, line_item_total, line_item_total_tax, sku,
                                        shipping_method_title,
                                        shipping_method_id,
                                        shipping_line_total, shipping_total_taxes, shipping_meta_data_key, shipping_meta_data_value, links_self_href,
                                        links_collection_href,
                                        links_customer_href, id, parent_id, customer_id, meta_id,
                                        line_id, line_quantity, line_product_id, line_variation_id,
                                        line_price, shipping_lines_id, shipping_meta_data_id, meta_data_value_number, meta_data_value_order_id);


                                allmyorderlist.add(myorder);
                            }
                            Log.e("june", line_item_name);

                            tax_lines = dataObject.getJSONArray("tax_lines");

                            JSONArray shipping_lines = dataObject.getJSONArray("shipping_lines");
                            shipping_lines_id = null;
                            shipping_method_title = null;
                            shipping_method_id = null;
                            shipping_line_total = null;
                            shipping_total_taxes = null;
                            tax_lines_taxes = null;
                            JSONArray shipping_meta_data = null;
                            shipping_meta_data_id = null;
                            shipping_meta_data_key = null;
                            shipping_meta_data_value = null;
                            for (int j = 0; j < shipping_lines.length(); j++) {
                                JSONObject shipping_lines_object = shipping_lines.getJSONObject(j);
                                shipping_lines_id = shipping_lines_object.getInt("id");
                                shipping_method_title = shipping_lines_object.getString("method_title");
                                shipping_method_id = shipping_lines_object.getString("method_id");
                                shipping_line_total = shipping_lines_object.getString("total");
                                shipping_total_taxes = shipping_lines_object.getString("total_tax");
                                tax_lines_taxes = shipping_lines_object.getJSONArray("taxes");
                                shipping_meta_data = shipping_lines_object.getJSONArray("meta_data");
                                Log.e("catogory", "myorder2");

                                for (int k = 0; k < shipping_meta_data.length(); k++) {
                                    shipping_meta_data_id = shipping_meta_data.getJSONObject(k).getInt("id");
                                    shipping_meta_data_key = shipping_meta_data.getJSONObject(k).getString("key");
                                    shipping_meta_data_value = shipping_meta_data.getJSONObject(k).getString("value");
                                    Log.e("catogory", "myorder2");

                                }
                            }

                            fee_lines = dataObject.getJSONArray("fee_lines");
                            coupon_lines = dataObject.getJSONArray("coupon_lines");
                            refunds = dataObject.getJSONArray("refunds");
                            JSONObject _links = dataObject.getJSONObject("_links");

                            JSONArray self_array = _links.getJSONArray("self");
                            links_self_href = null;
                            for (int m = i; m < self_array.length(); m++) {
                                links_self_href = self_array.getJSONObject(0).getString("href");
                            }
                            JSONArray collection_array = _links.getJSONArray("collection");
                            links_collection_href = null;
                            for (int n = i; n < collection_array.length(); n++) {
                                links_collection_href = collection_array.getJSONObject(0).getString("href");
                            }
                            JSONArray customer_array = _links.getJSONArray("collection");
                            links_customer_href = null;
                            for (int n = i; n < customer_array.length(); n++) {
                                links_customer_href = collection_array.getJSONObject(0).getString("href");
                            }


//                            Myorder myorder = new Myorder(line_item_taxes, line_item_meta_data, tax_lines, tax_lines_taxes,
//                                        fee_lines, coupon_lines, refunds, prices_include_tax, date_paid, date_paid_gmt, date_completed, date_completed_gmt, prefix,
//                                        auffix, padding, number, order_key, created_via, version, status, currence, date_created, date_created_gmt,
//                                        date_modified, date_modified_gmt, discount_total, discount_tax, shipping_tax, shipping_total,
//                                        cart_tax, total, total_tax, customer_ip_address, customer_user_agent, customer_note,
//                                        billing_fname, billing_last_name, billing_company, billing_address_1, billing_adddress_2, billing_city,
//                                        billing_state, billing_postcode, billing_country, billing_email, billing_phone, shipping_fname,
//                                        shipping_last_name, shipping_company, shippingbilling_address_1, shipping_adddress_2, shipping_city,
//                                        shipping_state, shipping_postcode, shipping_country, payment_method, payment_method_title, transaction_id,
//                                        cart_hash,
//                                        meta_data_key, meta_data_value, meta_data_value_formatted_number, meta_data_value_document_type, line_item_name, line_item_tax_class,
//                                        line_item_subtotal, line_item_subtotal_tax, line_item_total, line_item_total_tax, sku,
//                                        shipping_method_title,
//                                        shipping_method_id,
//                                        shipping_line_total, shipping_total_taxes, shipping_meta_data_key, shipping_meta_data_value, links_self_href,
//                                        links_collection_href,
//                                        links_customer_href, id, parent_id, customer_id, meta_id,
//                                        line_id, line_quantity, line_product_id, line_variation_id,
//                                        line_price, shipping_lines_id, shipping_meta_data_id, meta_data_value_number, meta_data_value_order_id);


//                            allmyorderlist.add(myorder);
                            flag = 2;
                        }

                    }
                    else
                        {
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
                Toast.makeText(MyOrders.this, "Server/Network issue", Toast.LENGTH_SHORT).show();

            } else if (flag == 2) {
                Toast.makeText(MyOrders.this, "Success", Toast.LENGTH_SHORT).show();
                mRecyclerView = (RecyclerView) findViewById(R.id.recycle);
                GridLayoutManager mGrid = new GridLayoutManager(MyOrders.this, 1);
                mRecyclerView.setLayoutManager(mGrid);
                mRecyclerView.setHasFixedSize(true);
                mRecyclerView.setNestedScrollingEnabled(false);
                myOrderAdpater = new MyOrderAdapter(MyOrders.this, allmyorderlist);
                mRecyclerView.setAdapter(myOrderAdpater);
            } else {
                Toast.makeText(MyOrders.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
            }
        }
    }
}