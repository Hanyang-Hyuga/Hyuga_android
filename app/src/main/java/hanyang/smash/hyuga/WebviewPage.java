package hanyang.smash.hyuga;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebviewPage extends AppCompatActivity {

    WebView web;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_page);
        web = (WebView) findViewById(R.id.webView);
        web.loadUrl("http://exitsoft.co.kr/hyuga");

        web.setWebViewClient(new WebViewClient() {


        });

        web.setWebChromeClient(new WebChromeClient(){


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
