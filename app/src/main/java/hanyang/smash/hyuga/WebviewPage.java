package hanyang.smash.hyuga;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;



public class WebviewPage extends Activity {


    WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        web = (WebView) findViewById(R.id.webView);

        web.loadUrl("http://exitsoft.co.kr/hyuga");

        web.setWebViewClient(new WebViewClient() {
        });

        web.setWebChromeClient(new WebChromeClient() {


        });

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        if (web.canGoBack()) {
            web.goBack();
        } else {
            finish();
        }
    }
}
