package com.example.audio_nots;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;

public class commandRecieved extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        MediaPlayer mp=MediaPlayer.create(context, R.raw.b);
        try{
            //mp.setDataSource("android.resource://" + getPackageName() + "/" + R.raw.b);//Write your location here

            mp.prepare();
            mp.start();

        }catch(Exception e){e.printStackTrace();}
    }
}
