package com.example.nyukoukanri2021_1;

import android.app.PendingIntent;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class readActivity extends AppCompatActivity {

    //初期化
    NfcAdapter nfcAdapter;
    SoundPool soundPool;
    static String gate = "str";
    static String idmString = "str";
    int soundpi;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        //NFCアダプタの初期化
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        nfcAdapter.enableReaderMode(readActivity.this, new MyReaderCallback(), NfcAdapter.FLAG_READER_NFC_F, null);

        //オーディオの初期化
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                .build();
        soundPool = new SoundPool.Builder()
                .setAudioAttributes(audioAttributes)
                .setMaxStreams(1)
                .build();
        soundpi = soundPool.load(this, R.raw.sharan, 1);

        //入構キャンパスの引継ぎ
        Intent intent = getIntent();
        gate = intent.getStringExtra("gate");
        Log.d("gate", gate);
    }


    @Override
    protected void onResume(){
        super.onResume();
        Log.d("startReading", "読み取りを開始したツノ！");

        Intent intent = new Intent(this, this.getClass());
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        //ほかのアプリが開かないようにする
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),0, intent, 0);
        nfcAdapter.enableForegroundDispatch(this, pendingIntent, null, null);
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d("pauseReading", "読み取りを一時停止しているツノ");

        nfcAdapter.disableForegroundDispatch(this);
    }

    class MyReaderCallback implements NfcAdapter.ReaderCallback{
        @RequiresApi(api = Build.VERSION_CODES.N)


        public void onTagDiscovered(Tag tag){
            Log.d("cardDiscovered", "カードが見つかったツノ！");

            byte[] idm = tag.getId();

            //16進数に変換
            StringBuilder stringBuilder = new StringBuilder();
            for(byte d: idm){
                stringBuilder.append(String.format("%02X", d));
            }
            String idmString0 = stringBuilder.toString();
            idmString = "0" + idmString0.substring(1);
            Log.d("idm: ", idmString);

            //音を鳴らしてSSに送る
            final Handler mainHandler = new Handler(Looper.getMainLooper());
            mainHandler.post(new Runnable() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void run() {
                    setContentView(R.layout.activity_read);

                    WebView webView = findViewById(R.id.webView);

                    webView.getSettings().setJavaScriptEnabled(true);
                    webView.getSettings().setDomStorageEnabled(true);
                    getWindow().setFlags(
                            WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                            WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);

                    webView.setWebViewClient(new WebViewClient());
                    String sentURL = "https://script.google.com/a/wasedasai.net/macros/s/AKfycbzdl8gVXhd2Dkqy1B6-rTGKD_ewKWpS2FimTIkZiOA2bVf_IQo/exec?idm=" + idmString + "&&gate=" + gate;
                    webView.loadUrl(sentURL);

                    soundPool.play(soundpi,1.0f, 1.0f, 0, 0,1);

                }
            });
        }
    }




}
