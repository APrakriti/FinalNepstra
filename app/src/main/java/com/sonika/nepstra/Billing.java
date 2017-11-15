package com.sonika.nepstra;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sonika.nepstra.Paypal.PaypalActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Billing extends AppCompatActivity {
    EditText fname,lname, cname, address_1, address_2,
            city, state,postcode,country,email,phone;
    Button btnplaceorder;
    ProgressDialog mprogressDialog;
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
    int flag;

    TextView _name, _email, _response;
    android.support.v7.widget.AppCompatButton _sendRequest;
    ProgressBar _proProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing_details);

        //Hooking the UI views for usage
        fname = (EditText) findViewById(R.id.lbl_first_name);
        _response = (TextView) findViewById(R.id.response);
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
        btnplaceorder = (Button) findViewById(R.id.btn_place_order);
        btnplaceorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sname = fname.getText().toString();
                slname = lname.getText().toString();
                scname = cname.getText().toString();
                saddress_1 = address_1.getText().toString();
                saddress_2 = address_2.getText().toString();
                scity = city.getText().toString();
                sstate = state.getText().toString();
                spostcode = postcode.getText().toString();
                scountry = country.getText().toString();
                sphone = phone.getText().toString();
                semail = email.getText().toString();
                if (sname.length() <= 0 || slname.length() <= 0 || scname.length() <= 0 || scountry.length() <= 0 || saddress_2.length() <= 0 || saddress_1.length() <= 0
                        || scity.length() <= 0 || sstate.length() <= 0 || sphone.length() <= 0 || spostcode.length() <= 0 || semail.length() <= 0) {
                    Toast.makeText(Billing.this, "Please, fill all the fields! ", Toast.LENGTH_SHORT).show();
//                } else if (!isValidContact(sphone)) {
//                    phone.setError("Please enter your valid number");



                    SharedPreferences sm = getSharedPreferences("USER_LOGIN", 0);
                    SharedPreferences.Editor editor = sm.edit();
                    editor.putString("name", sname);
                    editor.apply();
                    editor.commit();
                    SharedPreferences sm11 = getSharedPreferences("USER_LOGIN", 0);
                    SharedPreferences.Editor editor11 = sm11.edit();
                    editor11.putString("name", scity);
                    editor11.apply();
                    editor11.commit();
                    SharedPreferences sm112 = getSharedPreferences("USER_LOGIN", 0);
                    SharedPreferences.Editor editor112 = sm11.edit();
                    editor112.putString("name", sstate);
                    editor112.apply();
                    editor11.commit();
                    SharedPreferences sm1 = getSharedPreferences("USER_LOGIN", 0);
                    SharedPreferences.Editor editor1 = sm1.edit();
                    editor1.putString("country", scountry);
                    editor.apply();
                    editor.commit();
                    SharedPreferences sm2 = getSharedPreferences("USER_LOGIN", 0);
                    SharedPreferences.Editor editor2 = sm2.edit();
                    editor2.putString("phone", sphone);
                    editor.apply();
                    editor.commit();
                    SharedPreferences sm3 = getSharedPreferences("USER_LOGIN", 0);
                    SharedPreferences.Editor editor3 = sm3.edit();
                    editor3.putString("email", semail);
                    editor.apply();
                    editor.commit();

                } else {
                    Log.e("Tag", "signupPrakriti");


                    // Instantiate the RequestQueue.
                    RequestQueue queue = Volley.newRequestQueue(Billing.this);
                    //this is the url where you want to send the request
                    //TODO: replace with your own url to send request, as I am using my own localhost for this tutorial
                    String url = "http://nepstra.com/api/android/newcustomer.php?email=prak@email.com&first_name=fn&last_name=ln&username=prak@email.com" +
                            "&password=pass&b[first_name]=bfn&b[last_name]=bln&b[company]=bc&b[address_1]=ba1&b[address_2]=ba2&b[city]=bc" +
                            "&b[state]=bs&b[postcode]=bpc&b[country]=bc&b[email]=abcdef@email.com&b[phone]=bp&s[first_name]=sfn&s[last_name]=sln" +
                            "&s[company]=sc&s[address_1]=sa1&s[address_2]=sa2&s[city]=sc&s[state]=ss&s[postcode]=spc&s[country]=sc&s[email]=prak@email.com&s[phone]=sp";

                    // Request a string response from the provided URL.
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    // Display the response string.
                                    _response.setText(response);
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            _response.setText("That didn't work!");
                        }
                    }) {
                        //adding parameters to the request
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            params.put("email", _name.getText().toString());
                            params.put("first_name", _email.getText().toString());
                            params.put("last_name", _email.getText().toString());
                            params.put("company", _email.getText().toString());
                            params.put("address_1", _email.getText().toString());
                            params.put("address_2", _email.getText().toString());
                            params.put("city", _email.getText().toString());
                            params.put("state", _email.getText().toString());
                            params.put("postcode", _email.getText().toString());
                            params.put("country", _email.getText().toString());
                            params.put("email", _email.getText().toString());
                            params.put("phone", _email.getText().toString());
                            return params;
                        }
                    };
                    // Add the request to the RequestQueue.
                    queue.add(stringRequest);
                }
                Intent i = new Intent( Billing.this , PaypalActivity.class);
                startActivity(i);
            }});}}