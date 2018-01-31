package com.sonika.nepstra;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarTab;
import com.roughike.bottombar.OnTabSelectListener;
import com.sonika.nepstra.Paypal.PaypalActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginVolley extends AppCompatActivity {
    EditText email, password;
    String semail, spassword;
    TextView forgetPassword;
    Button login;
    ProgressDialog mprogressDialog;
    SharedPreferences loginpref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_volley);
        email = (EditText) findViewById(R.id.lbl_username_login);
        password = (EditText) findViewById(R.id.lbl_password_login);
        login = (Button) findViewById(R.id.btn_login_login);
        forgetPassword = findViewById(R.id.txt_forgot_password);

        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginVolley.this, ForgetPwd.class);
                startActivity(i);

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                semail = email.getText().toString();
                spassword = password.getText().toString();

                if (semail.length() <= 0 || spassword.length() <= 0) {
                    Toast.makeText(LoginVolley.this, "Please, fill all the fields! ", Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("Tag", "signupPrakriti");
                    mprogressDialog = new ProgressDialog(LoginVolley.this);
                    mprogressDialog.setMessage("Loading...");

                    mprogressDialog.show();

                    RequestQueue queue = Volley.newRequestQueue(LoginVolley.this);


                    StringRequest sr = new StringRequest(Request.Method.POST,
                            "https://nepstra.com/api/android/verifyuser.php?email=" + semail + "&pass=" + spassword,
                            //  https://nepstra.com/api/android/verifyuser.php/?email=send_correct_user&pass=send_correct_password
                            //  http://nepstra.com/api/android/verifyuser.php?email=email&pass=password
                            // https://nepstra.com/api/android/verifyuser.php/?user=send_correct_user&pass=send_correct_password
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Log.e("response", response);
                                    try {
                                        JSONObject jsonObject = new JSONObject(response);
                                        Log.e("simi", "monkey");
                                        String status = jsonObject.getString("status");
//                                        String message = jsonObject.getString("message");
////                                      Integer data = jsonObject.getInt(String.valueOf(1)); //yo k gareko?? data =
                                        Log.e("status", status);

                                        loginpref = getSharedPreferences("LOGINPREF", MODE_PRIVATE);
                                        SharedPreferences.Editor loginedit = loginpref.edit();

                                        if (status.equals("success")) {
                                            Intent i = new Intent(LoginVolley.this, MainActivity.class);
                                            startActivity(i);
                                            loginedit.putBoolean("login", true);
                                            loginedit.putString("email", semail);
                                            loginedit.commit();
                                        } else if (status.equals("error")) {
                                            loginedit.putBoolean("login", false);
                                            loginedit.commit();
                                            Toast.makeText(LoginVolley.this, "Wrong email adddress", Toast.LENGTH_SHORT).show();
                                        }

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                    //  msgResponse.setText(response.toString());

                                    mprogressDialog.hide();

                                    Log.e("HttpClientlogin", "success! response: " + response.toString());
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Log.e("HttpClientlogin", "error: " + error.toString());
                                    mprogressDialog.hide();
                                }
                            }) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<String, String>();
//                            params.put("email", semail);
//                            params.put("pass", spassword);
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
            }
        });

        final BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        BottomBarTab dummy = bottomBar.getTabWithId(R.id.tab_dummy);
        dummy.setVisibility(View.GONE);

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.tab_dummy:
                        break;

                    case R.id.tab_home:
                        Intent intentHome = new Intent(LoginVolley.this, MainActivity.class);
                        startActivity(intentHome);


                        break;


                    case R.id.tab_products:
                        Intent iiproducts = new Intent(LoginVolley.this, CategoriesActivity.class);
                        startActivity(iiproducts);


                        break;
                    case R.id.tab_order:
                        Intent intentOrder = new Intent(LoginVolley.this, OrderedProducts.class);
                        startActivity(intentOrder);

                        break;
                    case R.id.tab_account:
                        Toast.makeText(LoginVolley.this, "Account", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;

                }
            }
        });
    }
}


