package com.google.api.services.samples.calendar.android;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class WebViewActivity extends Activity {
  private WebView webView;
  
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.webview);
    
    // get Extras 
    Bundle extras = getIntent().getExtras();
    String auth_url = extras.getString("auth_url");
    
    webView = (WebView) findViewById(R.id.webView1);
    webView.getSettings().setJavaScriptEnabled(true);
    
    // prepare to extract URL
    webView.setWebViewClient(new WebViewClient(){
      @Override
      public void onPageFinished(WebView view, String url){
        
        String name = webView.getTitle();
        Log.d("Title",name);
        
        if (url.startsWith(OAuth2ClientCredentials.REDIRECT_URI)){
          
          if (url.indexOf("code")!= -1) {
            String code = extractCodeFromUrl(url);
            Log.d("HERES THE CODE!!!", code);
          }
          
        }
      }
      
      // grabs the code from the title header..
      private String extractCodeFromUrl(String url){
        return url.substring(OAuth2ClientCredentials.REDIRECT_URI.length()+7, url.length());
      }
    });
    
    webView.loadUrl(auth_url);
    
  }
  
}