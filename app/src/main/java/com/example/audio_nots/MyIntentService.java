package com.example.audio_nots;

import android.app.IntentService;
import android.content.Intent;
import android.media.MediaPlayer;
import android.util.Log;

public class MyIntentService extends IntentService {


    public MyIntentService() {
        super("MyIntentService");
    }

    MediaPlayer mp;
    @Override
    protected void onHandleIntent(Intent intent) {
        String s = "inside onHandleIntent";

        Log.d(s,"onHandleIntent");
        if (intent != null) {
            int k=3;
            while(k>0){
                for(int i=97;i<117;i++){

                    char myname=(char)i;
                    String name=myname+"";
                    //Drawable drawable = getResources().getDrawable(getResources().getIdentifier(""+(char)i, "drawable", getPackageName()));
                    int id = getResources().getIdentifier(getPackageName()+":drawable/" + myname, null, null);

                    //imageView.setImageResource(id);

                    MainActivity.updateNotification(id);
                    try {
                        Thread.sleep(70);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Log.d(s,name+" "+id);
                }
                k--;
            }



            //audio part
           /*if(intent.hasExtra(MainActivity.MYFLAG)){
               String flag=intent.getStringExtra(MainActivity.MYFLAG);
               Log.d(s,flag);
               if(flag.equals("play")){
                   Log.d(s,"in play");
                   mp=MediaPlayer.create(this, R.raw.b);
                   mp.start();
               }else{
                   Log.d(s,"in pause");
                   mp.pause();
               }
           }*/
        }
    }

}