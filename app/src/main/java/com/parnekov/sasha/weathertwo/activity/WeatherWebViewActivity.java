package com.parnekov.sasha.weathertwo.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.Toast;

import com.parnekov.sasha.weathertwo.R;
import com.parnekov.sasha.weathertwo.utils.intent.IntentUtils;
import com.parnekov.sasha.weathertwo.utils.view.WeatherWebViewClient;

/** WeatherWebViewActivity for showing web pages*/
public class WeatherWebViewActivity extends AppCompatActivity {

    private WebView mWebView;
    private String extras;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        // initialize
        mWebView = findViewById(R.id.web_view);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WeatherWebViewClient());

        // show web page
        getPage();
    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }


    // getExtras from Intent and load the page
    private void getPage() {
        if (getIntent().getExtras().getString(IntentUtils.FACEBOOK) != null) {
            extras = getIntent().getExtras().getString(IntentUtils.FACEBOOK);
        } else if (getIntent().getExtras().getString(IntentUtils.LINKEDIN) != null) {
            extras = getIntent().getExtras().getString(IntentUtils.LINKEDIN);
        } else if (getIntent().getExtras().getString(IntentUtils.GOOGLE_PLAY) != null) {
            extras = getIntent().getExtras().getString(IntentUtils.GOOGLE_PLAY);
        } else {
            Toast.makeText(this, "Wow, have a problem", Toast.LENGTH_SHORT).show();
        }

        if (extras != null) {
            switch (extras) {
                case IntentUtils.FACEBOOK:
                    mWebView.loadUrl(getApplicationContext().getResources().getString(R.string.fb_url));
                    break;
                case IntentUtils.LINKEDIN:
                    mWebView.loadUrl(getApplicationContext().getResources().getString(R.string.li_url));
                    break;
                case IntentUtils.GOOGLE_PLAY:
                    mWebView.loadUrl(getApplicationContext().getResources().getString(R.string.gp_url));
                    break;

                default:
                    onBackPressed();
            }
        }
    }
}
