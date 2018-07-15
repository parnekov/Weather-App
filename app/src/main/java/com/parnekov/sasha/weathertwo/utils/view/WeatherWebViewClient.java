package com.parnekov.sasha.weathertwo.utils.view;

import android.webkit.WebView;
import android.webkit.WebViewClient;


/**
 * Util class for wor with WeatherWebViewActivity
 */
public final class WeatherWebViewClient extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url)
    {
        view.loadUrl(url);
        return true;
    }
}
