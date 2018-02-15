package com.sonika.nepstra;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by user on 2/14/2018.
 */

public class BankActivity  extends AppCompatActivity {


//

    //
////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bankactivity);
        Toast.makeText(this, "hello bank activity", Toast.LENGTH_SHORT).show();


    }}