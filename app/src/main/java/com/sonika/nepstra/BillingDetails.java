package com.sonika.nepstra;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;
import com.sonika.nepstra.Paypal.PaypalActivity;
import com.sonika.nepstra.helpers.OrderHelper;
import com.sonika.nepstra.pojo.OrderedProducts_pojo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BillingDetails extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton rb_paypal, rb_banktransfer, rb_cash, radioBtn;
    EditText fname,lname, cname, address_1, address_2,
            city, state,postcode,country,email,phone, password;
    SharedPreferences sm;
    String paymentAmount;
    String name;
    String price;
    Integer quantity;
    Button btnplaceorder;
    ProgressDialog mprogressDialog;
    String payment_method, requestString;
    String sname ;
    String slname ;
    String scname ;
    String saddress_1;
    String saddress_2;
    String scity;
    String sstate;
    String spostcode;
    String scountry ;
    String semail;
    String sphone;
    String spassword;
    EditText shipfname, shiplname, shipcompany, shipcountry, shipaddress_1, shipaddress_2, shipcity, shipstate,shippostcode, shiporder;
    String sshipfname, sshiplname, sshipcompany, sshipcountry, sshipaddress_1, sshipaddress_2, sshipcity, sshipstate,sshippostcode, sshiporder;
    int flag;

    TextView _name, _email, _response;
    android.support.v7.widget.AppCompatButton _sendRequest;
    ProgressBar _proProgressBar;
    CheckBox cbCreateAccount, cbShipDifferentAddress;
    EditText lblPassword;
    OrderHelper orderHelper;
    ScrollView scrollView;
    ConstraintLayout shipConstraintLayout;
    List<OrderedProducts_pojo> cartlist = new ArrayList<OrderedProducts_pojo>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing_details);
        sm = getSharedPreferences("USER_LOGIN", 0);
        paymentAmount = sm.getString("total_amount", null);

//        sm = getSharedPreferences("ORDERPREF", 0);
//        name = sm.getString("name", null);
//        price = sm.getString("price", null);
//        quantity = sm.getString("quantity", null);

        orderHelper = new OrderHelper(BillingDetails.this);
        cartlist = orderHelper.getOrderMessage();
        for (int position = 0; position < cartlist.size(); position++)
        {
//        "&line_items[1][name]="+ name +
            requestString +="&line_items["+position+"][name]="+cartlist.get(position).getOrderedname()+
                    "&line_items["+position+"][quantity]=" + cartlist.get(position).getCount() +
                    "&line_items["+position +"][price]=" + cartlist.get(position).getOrderedprice() +
                    "&line_items[" +position+"][total]=" +cartlist.get(position).getOrderedprice();


        }

        //   name.setText(orderInfo.getOrderedname());




        //Hooking the UI views for usage
        fname = (EditText) findViewById(R.id.lbl_first_name);
        lname = (EditText) findViewById(R.id.lbl_last_name);
        cname = (EditText) findViewById(R.id.lbl_company_name);
        address_1 = (EditText) findViewById(R.id.lbl_house_no);
        address_2 = (EditText) findViewById(R.id.lbl_apartment_suite);
        city = (EditText) findViewById(R.id.lbl_town_city);
        state = (EditText) findViewById(R.id.lbl_state_zone);
        postcode = (EditText) findViewById(R.id.lbl_post_code_zip);
        country = (EditText) findViewById(R.id.lbl_country);
        phone = (EditText) findViewById(R.id.lbl_phone);
        email = (EditText) findViewById(R.id.lbl_email_address);
        password = (EditText) findViewById(R.id.lbl_password);

        shipfname = (EditText) findViewById(R.id.lbl_first_name_ship);
        shiplname = (EditText) findViewById(R.id.lbl_last_name_ship);
        shipcompany = (EditText) findViewById(R.id.lbl_company_name_ship);
        shipaddress_1 = (EditText) findViewById(R.id.lbl_house_no_ship);
        shipaddress_2 = (EditText) findViewById(R.id.lbl_apartment_suite_ship);
        shipcity = (EditText) findViewById(R.id.lbl_town_city_ship);
        shipstate = (EditText) findViewById(R.id.lbl_state_zone_ship);
        shippostcode = (EditText) findViewById(R.id.lbl_postcode_zip_ship);
        shipcountry = (EditText) findViewById(R.id.lbl_country_ship);
        shiporder = (EditText) findViewById(R.id.lbl_order_notes_ship);

        cbCreateAccount =(CheckBox) findViewById(R.id.cb_create_account);
        cbShipDifferentAddress =(CheckBox) findViewById(R.id.cb_ship_to_different_address);


        radioGroup = findViewById(R.id.radioGroup);
        rb_paypal = findViewById(R.id.radioButtonPaypal);
        rb_banktransfer = findViewById(R.id.radioButtonDirectBank);
        rb_cash = findViewById(R.id.radioButtonCash);
//
//        if (rb_paypal.isChecked())
//        {
//            payment_method = "Paypal";
//
//        }
//        if (rb_cash.isChecked())
//        {
//            payment_method = "cash on deleivery";
//        }
//        if (rb_banktransfer.isChecked())
//        {
//            payment_method = "Direct bank transfer";
//        }

        lblPassword = (EditText) findViewById(R.id.lbl_password);
        btnplaceorder = (Button) findViewById(R.id.btn_place_order);
        scrollView = (ScrollView) findViewById(R.id.scroll_view);
        shipConstraintLayout = (ConstraintLayout)findViewById(R.id.constraint_layout_ship);

        shipConstraintLayout.setVisibility(View.GONE);
        lblPassword.setVisibility(View.GONE);
        cbCreateAccount.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked) {
                    if(cbCreateAccount.isChecked())
                    {
                        //cbShipDifferentAddress.setChecked(true);
                        lblPassword.setVisibility(View.VISIBLE);
                        //spassword = password.getText().toString();
                    }
                    else
                    {
                        lblPassword.setVisibility(View.GONE);
                        scrollView.fullScroll(View.FOCUS_DOWN);
                    }}
                else
                {
                    lblPassword.setVisibility(View.GONE);
                    //scrollView.fullScroll(View.FOCUS_DOWN);
                }
            }});

        cbShipDifferentAddress.setOnCheckedChangeListener
                (new CompoundButton.OnCheckedChangeListener(){
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                        if(isChecked)
                        {
//                            if(cbCreateAccount.isChecked()) {
//                                cbCreateAccount.setChecked(false);
//                                lblPassword.setVisibility(View.GONE);
                            //     }
                            shipConstraintLayout.setVisibility(View.VISIBLE);
                            scrollView.fullScroll(View.FOCUS_DOWN);

                            sshipfname = shipfname.getText().toString();
                            sshiplname = shiplname.getText().toString();
                            sshipcompany = shipcompany.getText().toString();
                            sshipaddress_1 =shipaddress_1.getText().toString();
                            sshipaddress_2 = shipaddress_2.getText().toString();
                            sshipcity = shipcity.getText().toString();
                            sshipstate = shipstate.getText().toString();
                            sshippostcode = shippostcode.getText().toString();
                            sshipcountry = shipcountry.getText().toString();
                            sshiporder = shiporder.getText().toString();
                        }
                        else
                        {
                            shipConstraintLayout.setVisibility(View.GONE);

                        }




                        btnplaceorder.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                sname = fname.getText().toString();
                                slname = lname.getText().toString();
                                if(cbCreateAccount.isChecked()){
                                    spassword = password.getText().toString();
                                }

                                scname = cname.getText().toString();
                                saddress_1 = address_1.getText().toString();
                                saddress_2 = address_2.getText().toString();
                                scity = city.getText().toString();
                                sstate = state.getText().toString();
                                spostcode = postcode.getText().toString();
                                scountry = country.getText().toString();
                                sphone = phone.getText().toString();
                                semail = email.getText().toString();
                                int selectedId = radioGroup.getCheckedRadioButtonId();

                                // find the radiobutton by returned id
                                radioBtn = (RadioButton) findViewById(selectedId);
                                payment_method = radioBtn.getText().toString();
                                if(cbShipDifferentAddress.isChecked()){
                                    sshipfname = shipfname.getText().toString();
                                    sshiplname = shiplname.getText().toString();
                                    sshipcompany = shipcompany.getText().toString();
                                    sshipaddress_1 =shipaddress_1.getText().toString();
                                    sshipaddress_2 = shipaddress_2.getText().toString();
                                    sshipcity = shipcity.getText().toString();
                                    sshipstate = shipstate.getText().toString();
                                    sshippostcode = shippostcode.getText().toString();
                                    sshipcountry = shipcountry.getText().toString();
                                    sshiporder = shiporder.getText().toString();

                                }
//        SharedPreferences sm = getSharedPreferences("USER_LOGIN", 0);
//        SharedPreferences.Editor editor = sm.edit();
//        editor.putString("name", sname);
//        editor.apply();
//        editor.commit();
//        SharedPreferences sm11 = getSharedPreferences("USER_LOGIN", 0);
//        SharedPreferences.Editor editor11 = sm11.edit();
//        editor11.putString("name", scity);
//        editor11.apply();
//        editor11.commit();
//        SharedPreferences sm112 = getSharedPreferences("USER_LOGIN", 0);
//        SharedPreferences.Editor editor112 = sm11.edit();
//        editor112.putString("name", sstate);
//        editor112.apply();
//        editor11.commit();
//        SharedPreferences sm1 = getSharedPreferences("USER_LOGIN", 0);
//        SharedPreferences.Editor editor1 = sm1.edit();
//        editor1.putString("country", scountry);
//        editor.apply();
//        editor.commit();
//        SharedPreferences sm2 = getSharedPreferences("USER_LOGIN", 0);
//        SharedPreferences.Editor editor2 = sm2.edit();
//        editor2.putString("phone", sphone);
//        editor.apply();
//        editor.commit();
//        SharedPreferences sm3 = getSharedPreferences("USER_LOGIN", 0);
//        SharedPreferences.Editor editor3 = sm3.edit();
//        editor3.putString("email", semail);
//        editor.apply();
//        editor.commit();

                                Log.e("Tag", "signupPrakriti");
                                mprogressDialog= new ProgressDialog(BillingDetails.this);
                                mprogressDialog.setMessage("Loading...");
                                mprogressDialog.show();
                                //http://nepstra.com/api/android/finalorder.php?is_new_customer=1
                                // &email=ramhari@ramhari.com&first_name=fn&last_name=ln
                                // &username=ramhari@ramhari.com&password=prakash&b[first_name]=bfn
                                // &b[last_name]=bln&b[company]=bc&b[address_1]=ba1&b[address_2]=ba2&b[city]=bc
                                // &b[state]=bs&b[postcode]=bpc&b[country]=bc&b[email]=ramhari@ramhari.com&b[phone]=bp

                                // &s[first_name]=sfn&s[last_name]=sln&s[company]=sc&s[address_1]=sa1&s[address_2]=sa2
                                // &s[city]=sc&s[state]=ss&s[postcode]=spc&s[country]=sc&s[email]=ramhari@ramhari.com
                                // &s[phone]=sp&payment_method=cod&payment_method_title=cash_on_delivery&set_paid=true
                                // &s_lines[method_id]=1&s_lines[method_title]=shipping_title&s_lines[total]=50&line_items[101-1]
                                // &line_items[80-1]http://nepstra.com/api/android/finalorder.php?is_new_customer=1&email=ramhari@ramhari.com
                                // &first_name=fn&last_name=ln&username=ramhari@ramhari.com&password=prakash&b[first_name]=bfn&b[last_name]=bln
                                // &b[company]=bc&b[address_1]=ba1&b[address_2]=ba2&b[city]=bc&b[state]=bs&b[postcode]=bpc&b[country]=bc
                                // &b[email]=ramhari@ramhari.com&b[phone]=bp&s[first_name]=sfn&s[last_name]=sln&s[company]=sc&s[address_1]=sa1
                                // &s[address_2]=sa2&s[city]=sc&s[state]=ss&s[postcode]=spc&s[country]=sc&s[email]=ramhari@ramhari.com&s[phone]=sp

                                // &payment_method=cod&payment_method_title=cash_on_delivery&set_paid=true&s_lines[method_id]=1
                                // &s_lines[method_title]=shipping_title&s_lines[total]=50&line_items[101-1]&line_items[80-1]
                                //http://nepstra.com/api/android/xyz.php?b[first_name]=bfn&b[last_name]=bln&b[company]=bc&b[address_1]=ba1
                                // &b[address_2]=ba2&b[city]=bc&b[state]=bs&b[postcode]=bpc&b[country]=bc&b[email]=ramhari@ramhari.com
                                // &b[phone]=bp&s[first_name]=sfn&s[last_name]=sln&s[company]=sc&s[address_1]=sa1&s[address_2]=sa2
                                // &s[city]=sc&s[state]=ss&s[postcode]=spc&s[country]=sc&s[email]=ramhari@ramhari.com&s[phone]=sp
                                // &payment_method=cod&payment_method_title=cash_on_delivery&set_paid=true&s_lines[method_id]=1
                                // &s_lines[method_title]=shipping_title&s_lines[total]=50
                                // &line_items[id]=1&line_items[product_id]=1
                                // &line_items[quantity]=5&&line_items[subtotal]=420&line_items[total]=420&line_items[price]=420

                                RequestQueue queue = Volley.newRequestQueue(BillingDetails.this);
                                StringRequest sr = new StringRequest
                                        (Request.Method.POST,
                                                "http://nepstra.com/api/android/xyz.php?" +
                                                        "is_new_customer=1"+
                                                        "&email="+semail +
                                                        "&first_name="+sname +
                                                        "&last_name="+slname +
                                                        "&username="+sname +
                                                        "&password="+spassword +
                                                        "&b[first_name]="+sname +
                                                        "&b[last_name]="+slname +
                                                        "&b[company]="+sname +
                                                        "&b[address_1]="+saddress_1 +
                                                        "&b[address_2]="+saddress_2+
                                                        "&b[city]="+scity +
                                                        "&b[state]="+sstate +
                                                        "&b[postcode]="+spostcode +
                                                        "&b[country]="+scountry +
                                                        "&b[email]="+semail +
                                                        "&b[phone]="+sphone +
                                                        "&s[first_name]="+sshipfname +
                                                        "&s[last_name]="+sshiplname +
                                                        "&s[company]="+sshipcompany +
                                                        "&s[address_1]="+sshipaddress_1 +
                                                        "&s[address_2]="+sshipaddress_2 +
                                                        "&s[city]="+sshipcity +
                                                        "&s[state]="+sshipstate +
                                                        "&s[postcode]="+sshippostcode +
                                                        "&s[country]="+sshipcountry +
                                                        "&s[email]="+semail +
                                                        "&s[phone]="+sphone +
                                                        "&payment_method="+ payment_method+
                                                        "&payment_method_title="+ payment_method +
                                                        "&set_paid="+"true"+
                                                        "&s_lines[method_id]="+ 1 +
                                                        "&s_lines[method_title]="+payment_method+
                                                        "&s_lines[total]="+"jsai"+
                                                        requestString,
//

                                                new Response.Listener<String>() {
                                                    @Override
                                                    public void onResponse(String response) {
                                                        Intent i = new Intent(BillingDetails.this, PaypalActivity.class);
                                                        startActivity(i);
                                                        mprogressDialog.hide();
                                                        Log.e("HttpClient", "success! response: " + response.toString());
                                                    }


                                                },

                                                new Response.ErrorListener() {
                                                    @Override
                                                    public void onErrorResponse(VolleyError error) {
                                                        Log.e("HttpClient", "error: " + error.toString());
                                                        mprogressDialog.hide();
                                                    }
                                                }) {
                                    @Override
                                    protected Map<String, String> getParams() {
                                        Map<String, String> params = new HashMap<String, String>();
//                    params.put("email", semail);
//                    params.put("first_name", sname);
//                    params.put("last_name", slname);
//                    params.put("username", sname);
//                    params.put("password", "password");
//                    params.put("b[first_name]", sname);
//                    params.put("b[last_name]", slname);
//                    params.put("b[company]", scname);
//                    params.put("b[address_1]", saddress_1);
//                    params.put("b[address_2]", saddress_2);
//                    params.put("b[city]", scity);
//                    params.put("b[state]", sstate);
//                    params.put("b[postcode]", spostcode);
//                    params.put("b[country]", scountry);
//                    params.put("b[email]", semail);
//                    params.put("b[phone]", sphone);
//                    params.put("s[first_name]", sname);
//                    params.put("s[last_name]", slname);
//                    params.put("s[company]", scname);
//                    params.put("s[address_1]", saddress_1);
//                    params.put("s[address_2]", saddress_2);
//                    params.put("s[city]", scity);
//                    params.put("s[state]", sstate);
//                    params.put("s[postcode]", spostcode);
//                    params.put("s[country]", scountry);
//                    params.put("s[email]", semail);
//                    params.put("s[phone]", sphone);
//                    params.put("payment_method", "cod");
//                    params.put("payment_method_title", "cash_on_delivery");
//                    params.put("s_lines[method_id]", "1");
//                    params.put("s_lines[method_title]","shipping_title" );
//                    params.put("s_lines[total]", "50");
//                    params.put("line_items[101-1]", "static");
//                    params.put("line_items[80-1]","static");
                                        return params;
                                    }

                                    @Override
                                    public Map<String, String> getHeaders() throws AuthFailureError {
                                        Map<String, String> params = new HashMap<String, String>();
                                        params.put("Content-Type", "application/x-www-form-urlencoded");
                                        return params;
                                    }
                                };
                                sr.setRetryPolicy(new RetryPolicy() {
                                    @Override
                                    public int getCurrentTimeout() {
                                        return 50000;
                                    }

                                    @Override
                                    public int getCurrentRetryCount() {
                                        return 50000;
                                    }

                                    @Override
                                    public void retry(VolleyError error) throws VolleyError {

                                    }
                                });
                                queue.add(sr);


                            }});}});}}





