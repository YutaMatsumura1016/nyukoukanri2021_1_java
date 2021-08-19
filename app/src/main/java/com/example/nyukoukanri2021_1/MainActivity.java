package com.example.nyukoukanri2021_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //初期化
    static String gate = "str";

    //ボタンの設定
    Button buttonWaseda = findViewById(R.id.buttonWaseda);
    Button buttonToyama = findViewById(R.id.buttonToyama);
    Button buttonLogin = findViewById(R.id.buttonLogin);
    Button buttonFinishLogin = findViewById(R.id.buttonFinishLogin);
    WebView loginWebView = findViewById(R.id.loginWebView);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonFinishLogin.setVisibility(View.INVISIBLE);


        //ボタン押された
        buttonWaseda.setOnClickListener(v -> {
            gate = "waseda";

            Intent intent = new Intent(getApplication(), readActivity.class);
            intent.putExtra("gate", gate);
            startActivity(intent);
        });
        buttonToyama.setOnClickListener(v -> {
            gate = "toyama";

            Intent intent = new Intent(getApplication(), readActivity.class);
            intent.putExtra("gate", gate);
            startActivity(intent);
        });
        buttonLogin.setOnClickListener(v -> {
            loginWebView.setWebViewClient(new WebViewClient());
            String sentURL = "https://www.yahoo.co.jp";

            loginWebView.loadUrl(sentURL);
            buttonFinishLogin.setVisibility(View.VISIBLE);
            buttonWaseda.setVisibility(View.INVISIBLE);
            buttonToyama.setVisibility(View.INVISIBLE);
            buttonLogin.setVisibility((View.INVISIBLE));
        });
        buttonFinishLogin.setOnClickListener(v -> {
            loginWebView.setVisibility(View.INVISIBLE);
            buttonFinishLogin.setVisibility(View.INVISIBLE);
            buttonWaseda.setVisibility(View.VISIBLE);
            buttonWaseda.setVisibility(View.VISIBLE);
        });
    }





}