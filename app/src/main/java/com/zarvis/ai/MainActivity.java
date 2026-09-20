package com.zarvis.ai;

import android.os.Bundle;
import android.webkit.JsPromptResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WebView web = new WebView(this);
        WebSettings s = web.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);
        s.setAllowFileAccess(true);
        s.setAllowContentAccess(true);

        web.setBackgroundColor(0xFF212121);

        web.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsPrompt(android.webkit.WebView view, String url, String message, String defaultValue, final JsPromptResult result) {
                android.widget.EditText input = new android.widget.EditText(MainActivity.this);
                new android.app.AlertDialog.Builder(MainActivity.this)
                    .setTitle(message)
                    .setView(input)
                    .setPositiveButton("OK", (dialog, which) -> {
                        result.confirm(input.getText().toString());
                    })
                    .setNegativeButton("Cancel", (dialog, which) -> {
                        result.cancel();
                    })
                    .setCancelable(false)
                    .show();
                return true;
            }
        });

        web.loadUrl("file:///android_asset/index.html");
        setContentView(web);
    }

    @Override
    public void onBackPressed() {
        WebView web = (WebView) findViewById(android.R.id.content).getRootView();
        super.onBackPressed();
    }
}
