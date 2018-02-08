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

public class Billing extends AppCompatActivity implements View.OnClickListener {
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
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(PayPalConfig.PAYPAL_CLIENT_ID);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing_details);

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


        cbCreateAccount = (CheckBox) findViewById(R.id.cb_create_account);

        cbShipDifferentAddress = (CheckBox) findViewById(R.id.cb_ship_to_different_address);

        lblPassword = (EditText) findViewById(R.id.lbl_password);
        btnplaceorder = (Button) findViewById(R.id.btn_place_order);
        scrollView = (ScrollView) findViewById(R.id.scroll_view);
        shipConstraintLayout = (ConstraintLayout) findViewById(R.id.constraint_layout_ship);

        shipConstraintLayout.setVisibility(View.GONE);
        lblPassword.setVisibility(View.GONE);

        rg_paymentmethod = (RadioGroup) findViewById(R.id.radioGroup);
        rb_paypal = (RadioButton) findViewById(R.id.radioButtonPaypal);
        rb_bank = (RadioButton) findViewById(R.id.radioButtonDirectBank);
        rb_cash = (RadioButton) findViewById(R.id.radioButtonCash);

        sm = getSharedPreferences("USER_LOGIN", 0);
        paymentAmount = sm.getString("total_amount", null);


        orderHelper = new OrderHelper(Billing.this);
        cartlist = orderHelper.getOrderMessage();
        for (int position = 0; position < cartlist.size(); position++)
        {

            requestString += "&line_items["+position+"][name]="+cartlist.get(position).getOrderedname()+
                    "&line_items["+position+"][quantity]=" + cartlist.get(position).getCount() +
                    "&line_items["+position +"][price]=" + cartlist.get(position).getOrderedprice() +
                    "&line_items[" +position+"][total]=" +cartlist.get(position).getOrderedprice();


        }
        Log.e("requestString", requestString.toString());




        cbCreateAccount.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    if (cbCreateAccount.isChecked()) {
                        //cbShipDifferentAddress.setChecked(true);
                        lblPassword.setVisibility(View.VISIBLE);
                        //spassword = password.getText().toString();
                    } else {
                        lblPassword.setVisibility(View.GONE);

                        scrollView.fullScroll(View.FOCUS_DOWN);
                    }
                } else {
                    lblPassword.setVisibility(View.GONE);
                    //scrollView.fullScroll(View.FOCUS_DOWN);

                }
            }
        });

        cbShipDifferentAddress.setOnCheckedChangeListener
                (new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, final boolean isChecked) {
                        if (isChecked) {
//                            if(cbCreateAccount.isChecked()) {
//                                cbCreateAccount.setChecked(false);
//                                lblPassword.setVisibility(View.GONE);
//                            }
                            shipConstraintLayout.setVisibility(View.VISIBLE);
                            scrollView.fullScroll(View.FOCUS_DOWN);

                            sshipfname = shipfname.getText().toString();
                            sshiplname = shiplname.getText().toString();
                            sshipcompany = shipcompany.getText().toString();
                            sshipaddress_1 = shipaddress_1.getText().toString();
                            sshipaddress_2 = shipaddress_2.getText().toString();
                            sshipcity = shipcity.getText().toString();
                            sshipstate = shipstate.getText().toString();
                            sshippostcode = shippostcode.getText().toString();
                            sshipcountry = shipcountry.getText().toString();
                            sshiporder = shiporder.getText().toString();
                        } else {
                            shipConstraintLayout.setVisibility(View.GONE);

                        }


                        btnplaceorder.setOnClickListener(new View.OnClickListener() {
                                                             @Override
                                                             public void onClick(View view) {

                                                                 sname = fname.getText().toString();
                                                                 slname = lname.getText().toString();

                                                                 if (cbCreateAccount.isChecked()) {
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

                                                                 if (cbShipDifferentAddress.isChecked()) {
                                                                     sshipfname = shipfname.getText().toString();
                                                                     sshiplname = shiplname.getText().toString();
                                                                     sshipcompany = shipcompany.getText().toString();
                                                                     sshipaddress_1 = shipaddress_1.getText().toString();
                                                                     sshipaddress_2 = shipaddress_2.getText().toString();
                                                                     sshipcity = shipcity.getText().toString();
                                                                     sshipstate = shipstate.getText().toString();
                                                                     sshippostcode = shippostcode.getText().toString();
                                                                     sshipcountry = shipcountry.getText().toString();
                                                                     sshiporder = shiporder.getText().toString();
                                                                 }
//
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

                                                                 mprogressDialog = new ProgressDialog(Billing.this);
                                                                 mprogressDialog.setMessage("Loading...");
                                                                 mprogressDialog.show();



//                                                "&line_items[0][name]="+ "sonika" +
//                                                "&line_items[0][quantity]=" +1+
//                                                "&line_items[0][price]=" +400 +
//
//
//                                                "&line_items[1][name]="+"prakriti" +
//                                                "&line_items[1][quantity]=" + 2 +
//                                                "&line_items[1][price]=" +400+
//
//                                                "&line_items[0][total]=" +800+
//                                                "&line_items[1][total]=" +400,
                                                                                 //requestString,




                                                                                 getPayment();

                                                                 final int selected_paymentmethod_id = rg_paymentmethod.getCheckedRadioButtonId();
                                                                 radioButton = (RadioButton) findViewById(selected_paymentmethod_id);
                                                                 paymentMethod = radioButton.getText().toString();
                                                                 //  if( radioButton == rb_paypal)
                                                                 btnplaceorder.setOnClickListener(this);
                                                                 Intent intent = new Intent(Billing.this, PayPalService.class);

                                                                 intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);

                                                                 startService(intent);
                                                             } //Paypal Configuration Object




                                                             private void getPayment() {

//                                                                 sm = getSharedPreferences("USER_LOGIN", 0);
//                                                                 paymentAmount = sm.getString("total_amount", null);
//                                                                 sm = getSharedPreferences("USER_LOGIN", 0);
//                                                                 country = sm.getString("country", null);
                                                                 PayPalPayment payment = new PayPalPayment(new BigDecimal(paymentAmount), "AUD", "Total cost:", PayPalPayment.PAYMENT_INTENT_SALE);
                                                                 enableShippingAddressRetrieval(payment, true);
                                                                 addAppProvidedShippingAddress(payment);
                                                                 Intent intent = new Intent(Billing.this, PaymentActivity.class);

                                                                 //putting the paypal configuration to the intent
                                                                 intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);

                                                                 //Puting paypal payment to the intent
                                                                 intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);

                                                                 //Starting the intent activity for result
                                                                 //the request code will be used on the method onActivityResult
                                                                 startActivityForResult(intent, PAYPAL_REQUEST_CODE);
                                                             }

                                                             private void addAppProvidedShippingAddress(PayPalPayment paypalPayment) {

//                                                                 sm = getSharedPreferences("USER_LOGIN", 0);
//                                                                 name = sm.getString("total_amount", null);
//                                                                 sm = getSharedPreferences("USER_LOGIN", 0);
//                                                                 city = sm.getString("total_amount", null);
//                                                                 sm = getSharedPreferences("USER_LOGIN", 0);
//                                                                 state = sm.getString("total_amount", null);
//                                                                 sm = getSharedPreferences("USER_LOGIN", 0);
//                                                                 phoneno = sm.getString("phone", null);
//                                                                 sm = getSharedPreferences("USER_LOGIN", 0);
//                                                                 mail = sm.getString("email", null);
//                                                                 sm = getSharedPreferences("USER_LOGIN", 0);
//                                                                 country = sm.getString("country", null);

                                                                 ShippingAddress shippingAddress = new ShippingAddress().recipientName("name").line1("52 North Main St.")
                                                                         .city("sdjfh").state("jhsadf").postalCode("78729").countryCode("US");
                                                                 paypalPayment.providedShippingAddress(shippingAddress);
//        ShippingAddress shippingAddress =  new ShippingAddress().recipientName(name).line1("52 North Main St.")
//                .city("Austin").state("TX").postalCode("78729").countryCode("US");
//        paypalPayment.providedShippingAddress(shippingAddress);


                                                             }

                                                             /*
                                                              * Enable retrieval of shipping addresses from buyer's PayPal account
                                                              */
                                                             private void enableShippingAddressRetrieval(PayPalPayment paypalPayment, boolean enable) {
                                                                 paypalPayment.enablePayPalShippingAddressesRetrieval(enable);
                                                             }



                                                             protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                                                                 //If the result is from paypal
                                                                 if (requestCode == PAYPAL_REQUEST_CODE) {

                                                                     //If the result is OK i.e. user has not canceled the payment
                                                                     if (resultCode == Activity.RESULT_OK) {
                                                                         //Getting the payment confirmation
                                                                         PaymentConfirmation confirm = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);

                                                                         //if confirmation is not null
                                                                         if (confirm != null) {
                                                                             try {
                                                                                 //Getting the payment details
                                                                                 String paymentDetails = confirm.toJSONObject().toString(4);
                                                                                 Log.i("paymentExample", paymentDetails);

                                                                                 //Starting a new activity for the payment details and also putting the payment details with intent
                                                                                 startActivity(new Intent(Billing.this, ConfirmationActivity.class)
                                                                                         .putExtra("PaymentDetails", paymentDetails)
                                                                                         .putExtra("PaymentAmount", paymentAmount));

                                                                             } catch (JSONException e) {
                                                                                 Log.e("paymentExample", "an extremely unlikely failure occurred: ", e);
                                                                             }
                                                                         }
                                                                     } else if (resultCode == Activity.RESULT_CANCELED) {
                                                                         Log.i("paymentExample", "The user canceled.");
                                                                     } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
                                                                         Log.i("paymentExample", "An invalid Payment or PayPalConfiguration was submitted. Please see the docs.");
                                                                     }
                                                                 }
                                                             }


                                                         });

                        RequestQueue queue = Volley.newRequestQueue(Billing.this);
                        StringRequest sr = new StringRequest
                                (Request.Method.POST, "http://nepstra.com/api/android/xyz.php?" +
                                        "is_new_customer="+1 +
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
                                        "&payment_method="+ "productName" +
                                        "&payment_method_title="+ paymentMethod+
                                        "&set_paid="+"true"+
                                        "&s_lines[method_id]=" + 1 +
                                        "&s_lines[method_title]=" +  paymentMethod +
                                        "&s_lines[total]=" +"payment for shipping"+
                                        requestString,





                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Intent i = new Intent(Billing.this, PaypalActivity.class);
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


                    }});}







@Override
    public void onClick(View view) {
      ;

    }
}
