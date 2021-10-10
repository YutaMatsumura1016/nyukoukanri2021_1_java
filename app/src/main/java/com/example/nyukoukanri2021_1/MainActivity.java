package com.example.nyukoukanri2021_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    //初期化
    static String gate = "str";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ボタンの設定
        ImageButton buttonWaseda = findViewById(R.id.buttonWaseda);
        ImageButton buttonToyama = findViewById(R.id.buttonToyama);
        ImageButton buttonKougai = findViewById(R.id.buttonKougai);
        ImageButton buttonGakkan = findViewById(R.id.buttonGakkan);
        ImageButton buttonLogin = findViewById(R.id.buttonLogin);
        ImageButton buttonFinishLogin = findViewById(R.id.buttonFinishLogin);
        WebView loginWebView = findViewById(R.id.loginWebView);
        buttonFinishLogin.setVisibility(View.INVISIBLE);
        loginWebView.setVisibility(View.INVISIBLE);

        loginWebView.getSettings().setJavaScriptEnabled(true);
        loginWebView.getSettings().setDomStorageEnabled(true);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);


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
        buttonKougai.setOnClickListener(v -> {
            gate = "kougai";

            Intent intent = new Intent(getApplication(), readActivity.class);
            intent.putExtra("gate", gate);
            startActivity(intent);
        });
        buttonGakkan.setOnClickListener(v -> {
            gate = "gakkan";

            Intent intent = new Intent(getApplication(), readActivity.class);
            intent.putExtra("gate", gate);
            startActivity(intent);
        });
        buttonLogin.setOnClickListener(v -> {
            loginWebView.setVisibility(View.VISIBLE);
            loginWebView.setWebViewClient(new WebViewClient());
            String sentURL = "https://script.google.com/a/wasedasai.net/macros/s/AKfycbzaVnrwI0sa0CUYjsz-PNF9sdiyTPZtVP6CC_ZzA3f2vpFMdC4/exec";

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
            buttonToyama.setVisibility(View.VISIBLE);
        });
    }






}


