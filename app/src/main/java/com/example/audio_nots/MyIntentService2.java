package com.example.audio_nots;

import android.app.IntentService;
import android.content.Intent;
import android.media.MediaPlayer;
import android.util.Log;


public class MyIntentService2 extends IntentService {

    public MyIntentService2() {
        super("MyIntentService2");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String s = "inside onHandleIntent";
        Log.d(s,"onHandleIntent");
        if (intent != null) {
            MediaPlayer mp=MediaPlayer.create(this, R.raw.b);
            try{
                //mp.setDataSource("android.resource://" + getPackageName() + "/" + R.raw.b);//Write your location here

                //mp.prepare();
                mp.start();

            }catch(Exception e){e.printStackTrace();}
        }
    }
}