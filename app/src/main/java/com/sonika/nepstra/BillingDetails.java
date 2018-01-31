package com.sonika.nepstra;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonParser;
import com.sonika.nepstra.Paypal.PaypalActivity;
import com.sonika.nepstra.parser.JsonParserA;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class BillingDetails extends AppCompatActivity {
    EditText fname,lname, cname, address_1, address_2,
            city, state,postcode,country,email,phone;
    Button btnplaceorder;
    ProgressDialog mprogressDialog;
    String sname, slname,scname,saddress_1,saddress_2,scity,sstate,spostcode,scountry,semail,sphone ;
    int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing_details);
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
                    Toast.makeText(BillingDetails.this, "Please, fill all the fields! ", Toast.LENGTH_SHORT).show();
//                } else if (!isValidContact(sphone)) {
//                    phone.setError("Please enter your valid number");
                } else {
                    Log.e("Tag", "signupPrakriti");
                    new registerAsyncTask().execute();
                }
            }
        });



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

//k garne ho api naayepaxi :P
        //JHEU LAGISAKYO
    }








//        private boolean isValidContact(String ssphone) {
//            if (ssphone != null && ssphone.length() == 10){
//                return true;
//            }
//            return false;
//        }


    class registerAsyncTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mprogressDialog = new ProgressDialog(BillingDetails.this);
            mprogressDialog.setMessage("loading");
            mprogressDialog.setCancelable(false);
            mprogressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            HashMap<String, String> registerActivityHashMap = new HashMap<>();

//            String uri = Uri.parse("http://nepstra.com/api/android/newcustomer.php?email=prakash1111@email.com&first_name=fn&last_name=ln&username=prakriti1111@email.com&password=pass&b[first_name]=bfn&b[last_name]=bln&b[company]=bc&b[address_1]=ba1&b[address_2]=ba2&b[city]=bc&b[state]=bs&b[postcode]=bpc&b[country]=bc&b[email]=prakriti1111@email.com&b[phone]=bp&s[first_name]=sfn&s[last_name]=sln&s[company]=sc&s[address_1]=sa1&s[address_2]=sa2&s[city]=sc&s[state]=ss&s[postcode]=spc&s[country]=sc&s[email]=prakriti1111@email.com&s[phone]=sphttp://nepstra.com/api/android/newcustomer.php?email=prakriti1111@email.com&first_name=fn&last_name=ln&username=prakriti1111@email.com&password=pass&b[first_name]=bfn&b[last_name]=bln&b[company]=bc&b[address_1]=ba1&b[address_2]=ba2&b[city]=bc&b[state]=bs&b[postcode]=bpc&b[country]=bc&b[email]=prakriti1111@email.com&b[phone]=bp&s[first_name]=sfn&s[last_name]=sln&s[company]=sc&s[address_1]=sa1&s[address_2]=sa2&s[city]=sc&s[state]=ss&s[postcode]=spc&s[country]=sc&s[email]=prakriti1111@email.com&s[phone]=sp")
//                         .buildUpon()
//                         .appendQueryParameter("email", "val")
//                        .build().toString();

            JsonParserA jsonParser = new JsonParserA();
            JSONObject jsonObject = jsonParser.performPostCI("http://nepstra.com/api/android/newcustomer.php", registerActivityHashMap);

            try {
                if (jsonObject == null) {
                    flag = 1;
                    Log.e("Tag", "checkPrakriti");
                } else if (jsonObject.getString("status").equals("success")) {
                    Log.e("Tag", "check1Prakriti");
                    flag = 2;
                } else {
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
                Toast.makeText(BillingDetails.this, "Server/Network issue", Toast.LENGTH_SHORT).show();

            } else if (flag == 2) {
                Toast.makeText(BillingDetails.this, "Success" , Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(BillingDetails.this, PaypalActivity.class);
                startActivity(intent);

            } else {
                Toast.makeText(BillingDetails.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
            }
        }
    }


}


