package com.sonika.nepstra;


import android.app.Activity;
import android.view.Menu;
import android.webkit.WebView;
import android.app.ActionBar;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by user on 1/24/2018.
 */



public class Orderweb extends Activity {


//

    //
////
    private WebView myWebView;
    private Spinner menuspinner;
    private EditText editText;
    public static final String PREFS_NAME = "SharedPreferences";
    public static final String PREF_STRING = "https://nepstra.com/shop/checkout/";
    private SharedPreferences mPrefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_webview);
        mPrefs = getSharedPreferences(PREFS_NAME, 0);

        myWebView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.loadUrl(mPrefs.getString(PREF_STRING, "https://nepstra.com/shop/checkout/"));

        //  myWebView.setWebViewClient(new WebViewClient());
        myWebView.setWebViewClient(new MyAppWebViewClient());
        myWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url)
            {
//                myWebView.loadUrl("javascript:(function() { " +
//                        "document.getElementById('sidebar-footer').style.display='none'; " +
//                        "document.getElementById('masthead').style.display='none'; " +
//                        "document.getElementById('colophon').style.display='none'; " +
//                        "document.getElementsByClassName('header-promo')[0].style.display='none'; " +
//                        "})()");
                //   myWebView.loadUrl("javascript:document.getElementById(\"sidebar-footer\").setAttribute(\"style\",\"display:none;\");");

            }
        });
        myWebView.loadUrl("https://nepstra.com/shop/checkout/                                                                                                                                                                                      ");




/*        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if(actionId == EditorInfo.IME_ACTION_SEND) {
                    myWebView.loadUrl(editText.getText().toString());
                    handled = true;
                }
                return handled;
            }*/
    };
}

