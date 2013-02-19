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
        Log.d("URL", url);
        
//        if (url.startsWith(OAuth2ClientCredentials.REDIRECT_URI)){
        if (name.startsWith("Success")){
          Log.d("Title", "yes it matches!");
            String code = extractCodeFromUrl(name);
            Log.d("HERES THE CODE!!!", code);
          
        }
      }
      
      // grabs the code from the title header..
      private String extractCodeFromUrl(String name){
        return name.substring(13, name.length());
      }
    });
    
    webView.loadUrl(auth_url);
    
  }
  
}