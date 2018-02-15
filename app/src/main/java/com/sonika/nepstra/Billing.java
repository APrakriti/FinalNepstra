package com.sonika.nepstra;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;
import com.paypal.android.sdk.payments.ShippingAddress;
import com.sonika.nepstra.Configuration.PayPalConfig;
import com.sonika.nepstra.Paypal.ConfirmationActivity;
import com.sonika.nepstra.Paypal.PaypalActivity;
import com.sonika.nepstra.helpers.OrderHelper;
import com.sonika.nepstra.pojo.OrderedProducts_pojo;

import org.json.JSONArray;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.android.gms.common.api.Status.sr;
import static com.paypal.android.sdk.cx.f;
import static com.paypal.android.sdk.cx.i;

public class Billing extends AppCompatActivity {
    EditText fname, lname, cname, address_1, address_2,
            city, state, postcode, country, email, phone, password;
    Button btnplaceorder;
    RadioButton radioButton, rb_cash, rb_bank, rb_paypal;
    RadioGroup rg_paymentmethod;
    ProgressDialog mprogressDialog;
    String sname;
    String slname;
    String scname;
    String saddress_1;
    String saddress_2;
    String scity;
    String sstate;
    String spostcode;
    String scountry;
    String semail;
    String sphone;
    String spassword;
    String paymentAmount;
    String paymentMethod;
    String name;
    String price;
    Integer quantity;
    SharedPreferences sm;

    EditText shipfname, shiplname, shipcompany, shipcountry, shipaddress_1, shipaddress_2, shipcity, shipstate, shippostcode, shiporder;
    String sshipfname, sshiplname, sshipcompany, sshipcountry, sshipaddress_1, sshipaddress_2, sshipcity, sshipstate, sshippostcode, sshiporder;

    List<OrderedProducts_pojo> cartlist;
    CheckBox cbCreateAccount, cbShipDifferentAddress;
    EditText lblPassword;
    ScrollView scrollView;
    ConstraintLayout shipConstraintLayout;
    OrderHelper orderHelper;
    String requestString;
    public static final int PAYPAL_REQUEST_CODE = 123;
    public static PayPalConfiguration config = new PayPalConfiguration()
            // Start with mock environment.  When ready, switch to sandbox (ENVIRONMENT_SANDBOX)
            // or live (ENVIRONMENT_PRODUCTION)
            .environment(PayPalConfiguration.ENVIRONMENT_PRODUCTION)
                    //ENVIRONMENT_SANDBOX)
            .clientId(PayPalConfig.PAYPAL_CLIENT_ID);
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

        orderHelper = new OrderHelper(Billing.this);
        cartlist = orderHelper.getOrderMessage();
        for (int position = 0; position < cartlist.size(); position++)
        {
//        "&line_items[1][name]="+ name +
            requestString +="&line_items["+position+"][name]="+cartlist.get(position).getOrderedname()+
                    "&line_items["+position+"][quantity]=" + cartlist.get(position).getCount() +
                    "&line_items["+position +"][price]=" + cartlist.get(position).getOrderedprice() +
                    "&line_items[" +position+"][total]="+ String.valueOf(Integer.valueOf(cartlist.get(position).getCount()) * (Integer.valueOf(cartlist.get(position).getOrderedprice())));


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


        rg_paymentmethod = findViewById(R.id.radioGroup);
        rb_paypal = findViewById(R.id.radioButtonPaypal);
        rb_bank = findViewById(R.id.radioButtonDirectBank);
        rb_cash = findViewById(R.id.radioButtonCash);

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
                                final int selectedId = rg_paymentmethod.getCheckedRadioButtonId();

                                // find the radiobutton by returned id
                                radioButton = (RadioButton) findViewById(selectedId);
                                paymentMethod = radioButton.getText().toString();
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
                                mprogressDialog= new ProgressDialog(Billing.this);
                                mprogressDialog.setMessage("Loading...");
                                mprogressDialog.show();

                                RequestQueue queue = Volley.newRequestQueue(Billing.this);
                                StringRequest sr = new StringRequest
                                        (Request.Method.POST, "http://nepstra.com/api/android/xyz.php?" +
                                                "is_new_customer=" + 1 +
                                                "&email=" + semail +
                                                "&first_name=" + sname +
                                                "&last_name=" + slname +
                                                "&username=" + sname +
                                                "&password=" + spassword +
                                                "&b[first_name]=" + sname +
                                                "&b[last_name]=" + slname +
                                                "&b[company]=" + sname +
                                                "&b[address_1]=" + saddress_1 +
                                                "&b[address_2]=" + saddress_2 +
                                                "&b[city]=" + scity +
                                                "&b[state]=" + sstate +
                                                "&b[postcode]=" + spostcode +
                                                "&b[country]=" + scountry +
                                                "&b[email]=" + semail +
                                                "&b[phone]=" + sphone +
                                                "&s[first_name]=" + sshipfname +
                                                "&s[last_name]=" + sshiplname +
                                                "&s[company]=" + sshipcompany +
                                                "&s[address_1]=" + sshipaddress_1 +
                                                "&s[address_2]=" + sshipaddress_2 +
                                                "&s[city]=" + sshipcity +
                                                "&s[state]=" + sshipstate +
                                                "&s[postcode]=" + sshippostcode +
                                                "&s[country]=" + sshipcountry +
                                                "&s[email]=" + semail +
                                                "&s[phone]=" + sphone +
                                                "&payment_method=" + "productName" +
                                                "&payment_method_title=" + paymentMethod +
                                                "&set_paid=" + "true" +
                                                "&s_lines[method_id]=" + 1 +
                                                "&s_lines[method_title]=" + paymentMethod +
                                                "&s_lines[total]=" + "payment for shipping" + requestString,


                                                new Response.Listener<String>() {
                                                    @Override
                                                    public void onResponse(String response) {

                                                        try {
                                                            JSONObject jsonObject = new JSONObject(response);
                                                            Log.e("simi", "monkey");
                                                            String status = jsonObject.getString("checkstatus");
//                                        String message = jsonObject.getString("message");
////                                      Integer data = jsonObject.getInt(String.valueOf(1)); //yo k gareko?? data =
                                                            Log.e("status", status);

//                                                            loginpref = getSharedPreferences("LOGINPREF", MODE_PRIVATE);
//                                                            SharedPreferences.Editor loginedit = loginpref.edit();

                                                            if (status.equals("sucess")) {
                                                                if (radioButton == rb_paypal) {
                                                                    Intent i = new Intent(Billing.this, PaypalActivity.class);
                                                                    startActivity(i);

                                                                } else if (radioButton == rb_bank) {
                                                                Intent i = new Intent(Billing.this, BankActivity.class);
                                                                startActivity(i);
                                                            } else if (radioButton == rb_cash) {
                                                            Intent i = new Intent(Billing.this, CashActivity.class);
                                                            startActivity(i);}}


//                                                                Intent i = new Intent(Billing.this, PaypalActivity.class);
//                                                                startActivity(i);
////                                                                loginedit.putBoolean("login", true);
////                                                                loginedit.putString("email", semail);
////                                                                loginedit.commit();
                                                         else if (status.equals("failed")) {
                                                                Toast.makeText(Billing.this, "Email already exist", Toast.LENGTH_SHORT).show();
                                                            }

                                                        } catch (JSONException e) {
                                                            e.printStackTrace();
                                                        }


                                                        //  msgResponse.setText(response.toString());

                                                        mprogressDialog.hide();

                                                        Log.e("HttpClientlogin", "success! response: " + response.toString());
                                                    }

//                                                        Intent i = new Intent(Billing.this, PaypalActivity.class);
//                                                        startActivity(i);
//                                                        mprogressDialog.hide();
//                                                        Log.e("HttpClient", "success! response: " + response.toString());
//                                                    }


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


                            }
                        });
                    }


                });}}
