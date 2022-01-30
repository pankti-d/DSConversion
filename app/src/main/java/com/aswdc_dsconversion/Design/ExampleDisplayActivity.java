package com.aswdc_dsconversion.Design;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.ActionBar;

import com.aswdc_dsconversion.R;

public class ExampleDisplayActivity extends BaseActivity {


    WebView webViewExample;

    @Override
    public void initVariables() {
        Bundle bundle=getIntent().getExtras();
        String str=bundle.getString("html");
        webViewExample = findViewById(R.id.webView_htmlTable);
        webViewExample.loadData(str, "text/html", "utf-8");
    }

    @Override
    public void bindWidgetEvents() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_webviewexample);

        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Stack Trace Example");
    }
}

