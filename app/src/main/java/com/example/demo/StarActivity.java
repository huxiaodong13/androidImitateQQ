package com.example.demo;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StarActivity extends AppCompatActivity {
    private WebView webView;
    private Button loadURL;
    private EditText url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置页面布局 ,界面
        setContentView(R.layout.web_layout);
        webView = (WebView) findViewById(R.id.webView);
        loadURL  = (Button) this.findViewById(R.id.loadURL);
        url = (EditText) this.findViewById(R.id.url);

        WebSettings settings = webView.getSettings();
        settings.setSupportZoom(true); //支持缩放
        settings.setBuiltInZoomControls(true); //启用内置缩放装置
        settings.setJavaScriptEnabled(true); //启用JS脚本


        loadURL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!(url.getText().toString()).isEmpty()) {
                    Uri uri = Uri.parse(url.getText().toString());
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }else {
                    url.getText();
                    Toast.makeText(StarActivity.this, "网页为空", Toast.LENGTH_SHORT).show();
                }
                /*webView.loadUrl(url.getText().toString()); //加载url
                webView.requestFocus(); //获取焦点
                Intent intent = getIntent();

                Bundle bundle = intent.getExtras();

                String url = bundle.getString("url");

                if(!TextUtils.isEmpty(url)){

                    webView.loadUrl(url);
                    startActivity(intent);
                }*/

            }
        });
    }
}
