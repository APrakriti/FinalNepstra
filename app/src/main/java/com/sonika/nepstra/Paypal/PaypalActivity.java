package com.sonika.nepstra.Paypal;

import android.app.Activity;
import android.content.Intent;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;
import com.paypal.android.sdk.payments.ShippingAddress;
import com.sonika.nepstra.Configuration.PayPalConfig;
import com.sonika.nepstra.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by sonika on 10/30/2017.
 */

public class PaypalActivity extends AppCompatActivity implements View.OnClickListener {

    //The views
    private Button buttonPay;
    SharedPreferences sm;
    //  private EditText editTextAmount;

    //Payment Amount
    String paymentAmount;
    String name,city, country,state, mail, phoneno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paypal);

        buttonPay = (Button) findViewById(R.id.buttonPay);

        buttonPay.setOnClickListener(this);
        Intent intent = new Intent(this, PayPalService.class);

        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);

        startService(intent);
    }

    @Override
    public void onClick(View v) {
        getPayment();

    }


    public static final int PAYPAL_REQUEST_CODE = 123;


    //Paypal Configuration Object
    private static PayPalConfiguration config = new PayPalConfiguration()
            // Start with mock environment.  When ready, switch to sandbox (ENVIRONMENT_SANDBOX)
            // or live (ENVIRONMENT_PRODUCTION)
            .environment(PayPalConfiguration.ENVIRONMENT_PRODUCTION)
            .clientId(PayPalConfig.PAYPAL_CLIENT_ID);


    @Override
    public void onDestroy() {
        stopService(new Intent(this, PayPalService.class));
        super.onDestroy();
    }

    private void getPayment() {

        sm = getSharedPreferences("USER_LOGIN", 0);
        paymentAmount = sm.getString("total_amount", null);
        sm = getSharedPreferences("USER_LOGIN", 0);
        country = sm.getString("country", null);
        PayPalPayment payment = new PayPalPayment(new BigDecimal(paymentAmount), "AUD", "Total cost:", PayPalPayment.PAYMENT_INTENT_SALE);
        enableShippingAddressRetrieval(payment, true);
        addAppProvidedShippingAddress(payment);
        Intent intent = new Intent(this, PaymentActivity.class);

        //putting the paypal configuration to the intent
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);

        //Puting paypal payment to the intent
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);

        //Starting the intent activity for result
        //the request code will be used on the method onActivityResult
        startActivityForResult(intent, PAYPAL_REQUEST_CODE);
    }

    private void addAppProvidedShippingAddress(PayPalPayment paypalPayment) {

        sm = getSharedPreferences("USER_LOGIN", 0);
        name = sm.getString("total_amount", null);
        sm = getSharedPreferences("USER_LOGIN", 0);
        city = sm.getString("total_amount", null);
        sm = getSharedPreferences("USER_LOGIN", 0);
        state = sm.getString("total_amount", null);
        sm = getSharedPreferences("USER_LOGIN", 0);
        phoneno = sm.getString("phone", null);
        sm = getSharedPreferences("USER_LOGIN", 0);
        mail = sm.getString("email", null);
        sm = getSharedPreferences("USER_LOGIN", 0);
        country = sm.getString("country", null);

        ShippingAddress shippingAddress =  new ShippingAddress().recipientName(name).line1("52 North Main St.")
                .city(city).state(state).postalCode("78729").countryCode("US");
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


    @Override
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
                        startActivity(new Intent(this, ConfirmationActivity.class)
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


}