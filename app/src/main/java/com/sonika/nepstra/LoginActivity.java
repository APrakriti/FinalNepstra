//package com.sonika.nepstra;
//
//import android.app.ProgressDialog;
//import android.content.Intent;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.support.annotation.IdRes;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import com.google.gson.JsonParser;
//import com.roughike.bottombar.BottomBar;
//import com.roughike.bottombar.BottomBarTab;
//import com.roughike.bottombar.OnTabSelectListener;
//import com.sonika.nepstra.parser.JsonParserA;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.HashMap;
//
///**
// * Created by sonika on 10/11/2017.
// */
//
//public class LoginActivity extends AppCompatActivity {
//
//    public boolean click = true;
//    EditText username, password;
//    String sname, spassword;
//    Button btnRegister, login;
//    ProgressDialog mprogressDialog;
//
//    JSONObject jsonObject;
//    int flag;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//        username = (EditText) findViewById(R.id.lbl_username_login);
//        password = (EditText) findViewById(R.id.lbl_password_login);
//        login = (Button) findViewById(R.id.btn_login_login);
//
//
//        login.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//
//
//                sname = username.getText().toString();
//                spassword = password.getText().toString();
//
//                if (sname.length() <= 0 || spassword.length() <= 0) {
//                    Toast.makeText(LoginActivity.this, "Please, fill all the fields! ", Toast.LENGTH_SHORT).show();
////                } else if (!isValidContact(sphone)) {
////                    phone.setError("Please enter your valid number");
//                } else {
//                    Log.e("Tag", "signupPrakriti");
//                    new loginAsyncTask().execute();
//                }
//            }
//        });
//    }
//
//
//    class loginAsyncTask extends AsyncTask<String, String, String> {
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            mprogressDialog = new ProgressDialog(LoginActivity.this);
//            mprogressDialog.setMessage("please wait");
//            mprogressDialog.setCancelable(false);
//            mprogressDialog.show();
//
//        }
//
//        @Override
//        protected String doInBackground(String... params) {
//            HashMap<String, String> loginHashMap = new HashMap<>();
//            loginHashMap.put("email", sname);
//            loginHashMap.put("pass", spassword);
//            JsonParserA jsonParser = new JsonParserA();
//            jsonObject = jsonParser.performPostCI("https://nepstra.com/api/android/verifyuser.php", loginHashMap);
//            //https://nepstra.com/api/android/verifyuser.php/?email=send_correct_user&pass=send_correct_password
//
//            try {
//                if (jsonObject == null) {
//                    flag = 1;
//                }
//               // else if (jsonObject.getString("data").equals("1")){
//
//                else if (jsonObject.getString("status").equals("success")) {
//
//                    flag = 2;
//
//                } else {
//                    flag = 3;
//
//                }
//            } catch (JSONException e) {
//
//            }
//
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//            mprogressDialog.dismiss();
//
//
//            if (flag == 1) {
//                Toast.makeText(LoginActivity.this, "Server/Network issue", Toast.LENGTH_SHORT).show();
//
//            } else if (flag == 2) {
//
//
//                Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                startActivity(intent);
//
//            } else {
//                Toast.makeText(LoginActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
//            }
//
//
//            final BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
//            BottomBarTab dummy = bottomBar.getTabWithId(R.id.tab_dummy);
//            dummy.setVisibility(View.GONE);
//
//            bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
//                @Override
//                public void onTabSelected(@IdRes int tabId) {
//                    switch (tabId) {
//                        case R.id.tab_dummy:
//                            break;
//
//                        case R.id.tab_home:
//                            Intent intentHome = new Intent(LoginActivity.this, MainActivity.class);
//                            startActivity(intentHome);
//
//
//                            break;
//
//
//                        case R.id.tab_products:
//                            //    Intent iiproducts = new Intent(LoginActivity.this,CategoriesActivity.class);
//                            //  startActivity(iiproducts);
//
//
//                            break;
//                        case R.id.tab_order:
//                            Intent intentOrder = new Intent(LoginActivity.this, OrderedProducts.class);
//                            startActivity(intentOrder);
//
//                            break;
//                        case R.id.tab_account:
//                            Toast.makeText(LoginActivity.this, "Account", Toast.LENGTH_SHORT).show();
//                            break;
//                        default:
//                            break;
//
//                    }
//                }
//            });
//
//
//            btnRegister = (Button) findViewById(R.id.btn_register_login);
//            btnRegister.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
//                }
//            });
//        }}}
//
//
