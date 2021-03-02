package com.example.audio_nots;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.gifdecoder.StandardGifDecoder;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class MainActivity extends Activity {
    public static final String MYFLAG = "MYFLAG";
    private Button b1,b2,b3,b4;
    private ImageView iv;
    private MediaPlayer mediaPlayer;

    private double startTime = 0;
    private double finalTime = 0;

    private Handler myHandler = new Handler();;
    private int forwardTime = 5000;
    private int backwardTime = 5000;
    private SeekBar seekbar;
    private TextView tx1,tx2,tx3;

    String M = "ANKUR";

    private static final int NOTIF_ID = 1234;
    private static NotificationCompat.Builder mBuilder;
    private static NotificationManager mNotificationManager;
    private static RemoteViews mRemoteViews=null;
    private static Notification mNotification;

    private static RemoteViews expandedRemote;

    public static int oneTimeOnly = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imagecheck);
        ArrayList<Bitmap> bitmaps=giftoframes();
        Log.d(M,"back to main");
        Log.d(M, String.valueOf(bitmaps.size()));
        //glidecheck(bitmaps);

        //final working
        first();
        //working 1
        /*setUpNotification();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        //updateNotification();
        /*ImageView imageView = (ImageView)findViewById(R.id.image_view);

        //char myname='c';
        //int id = getResources().getIdentifier(getPackageName()+":drawable/" + myname, null, null);
        imageView.setImageResource(R.drawable.b);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        imageView.setImageResource(R.drawable.c);
*/

        //working 2
        /*int k=10;
        while(k>0){
            for(int i=97;i<117;i++){

                char myname=(char)i;
                String name=myname+"";
                //Drawable drawable = getResources().getDrawable(getResources().getIdentifier(""+(char)i, "drawable", getPackageName()));
                int id = getResources().getIdentifier(getPackageName()+":drawable/" + myname, null, null);

                //imageView.setImageResource(id);

                updateNotification(id);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Log.d(M,name+" "+id+" "+R.drawable.a);
            }
            k--;
        }*/

        /*b1 = (Button) findViewById(R.id.button);
        b2 = (Button) findViewById(R.id.button2);
        b3 = (Button)findViewById(R.id.button3);
        b4 = (Button)findViewById(R.id.button4);
        iv = (ImageView)findViewById(R.id.imageView);

        tx1 = (TextView)findViewById(R.id.textView2);
        tx2 = (TextView)findViewById(R.id.textView3);
        tx3 = (TextView)findViewById(R.id.textView4);
        tx3.setText("Song.mp3");

        mediaPlayer = MediaPlayer.create(this, R.raw.b);
        seekbar = (SeekBar)findViewById(R.id.seekBar);
        seekbar.setClickable(false);
        b2.setEnabled(false);

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Playing sound",Toast.LENGTH_SHORT).show();
                        mediaPlayer.start();                                                         //start

                finalTime = mediaPlayer.getDuration();
                startTime = mediaPlayer.getCurrentPosition();

                if (oneTimeOnly == 0) {
                    seekbar.setMax((int) finalTime);
                    oneTimeOnly = 1;
                }

                tx2.setText(String.format("%d min, %d sec",
                        TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
                        TimeUnit.MILLISECONDS.toSeconds((long) finalTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                        finalTime)))
                );

                tx1.setText(String.format("%d min, %d sec",
                        TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                        TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                        startTime)))
                );

                seekbar.setProgress((int)startTime);
                myHandler.postDelayed(UpdateSongTime,100);
                b2.setEnabled(true);
                b3.setEnabled(false);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Pausing sound",Toast.LENGTH_SHORT).show();
                        mediaPlayer.pause();
                b2.setEnabled(false);
                b3.setEnabled(true);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = (int)startTime;

                if((temp+forwardTime)<=finalTime){
                    startTime = startTime + forwardTime;
                    mediaPlayer.seekTo((int) startTime);
                    Toast.makeText(getApplicationContext(),"You have Jumped forward 5 seconds",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Cannot jump forward 5 seconds",Toast.LENGTH_SHORT).show();
                }
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = (int)startTime;

                if((temp-backwardTime)>0){
                    startTime = startTime - backwardTime;
                    mediaPlayer.seekTo((int) startTime);
                    Toast.makeText(getApplicationContext(),"You have Jumped backward 5seconds",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Cannot jump backward 5 seconds",Toast.LENGTH_SHORT).show();
                }
            }
        });*/
    }

    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            startTime = mediaPlayer.getCurrentPosition();
            tx1.setText(String.format("%d min, %d sec",
                    TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                    TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                    toMinutes((long) startTime)))
            );
            seekbar.setProgress((int)startTime);
            myHandler.postDelayed(this, 100);
        }
    };



    public void sendNotification(View view){
        /*Intent intent = new Intent(this, commandRecieved.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,1,intent,PendingIntent.FLAG_UPDATE_CURRENT);
*/
        Intent serviceIntent = new Intent(this, MyIntentService.class);
        serviceIntent.putExtra(MYFLAG,"play");

        PendingIntent servicePendingIntent = PendingIntent.getService(this,2,serviceIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        Intent serviceIntentPause = new Intent(this, MyIntentService.class);
        serviceIntentPause.putExtra(MYFLAG,"pause");

        PendingIntent servicePendingIntentPause = PendingIntent.getService(this,2,serviceIntentPause,PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "MyNotifications")
                .setContentTitle("music")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentText("my music")
                .addAction(R.drawable.play,"play",servicePendingIntent)
                .addAction(R.drawable.play,"pause",servicePendingIntentPause);


        NotificationManagerCompat manager = NotificationManagerCompat.from(this);
        manager.notify(123, builder.build());
    }

    public void first(){

        Intent serviceIntent = new Intent(this, MyIntentService.class);
        //serviceIntent.putExtra(MYFLAG,"play");
        PendingIntent servicePendingIntent = PendingIntent.getService(this,2,serviceIntent,PendingIntent.FLAG_UPDATE_CURRENT);


        mNotificationManager = (NotificationManager) getSystemService(this.NOTIFICATION_SERVICE);
        mRemoteViews = new RemoteViews(getPackageName(), R.layout.custom_notification_small);
        expandedRemote = new RemoteViews(getPackageName(), R.layout.imagecheck);

        mRemoteViews.setImageViewResource(R.id.notif_icon, R.drawable.a);
        mRemoteViews.setTextViewText(R.id.notif_content, getResources().getString(R.string.content_text));

        expandedRemote.setImageViewResource(R.id.image_view, R.drawable.a);

        mBuilder = new NotificationCompat.Builder(this,"Mynot");

        mBuilder.setSmallIcon(R.drawable.ic_launcher_foreground)
                .setStyle(new NotificationCompat.DecoratedCustomViewStyle())
                .setAutoCancel(false)
                .setOngoing(true)
                .setContent(mRemoteViews)
                .setCustomBigContentView(expandedRemote)
                .addAction(R.drawable.play,"play gif",servicePendingIntent);;

        mNotificationManager.notify(NOTIF_ID, mBuilder.build());
    }

    private void playGif(){

    }

    // call this method to setup notification for the first time
    private void setUpNotification(){

        mNotificationManager = (NotificationManager) getSystemService(this.NOTIFICATION_SERVICE);

        // we need to build a basic notification first, then update it
        Intent intentNotif = new Intent(this, MainActivity.class);
        intentNotif.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendIntent = PendingIntent.getActivity(this, 0, intentNotif, PendingIntent.FLAG_UPDATE_CURRENT);

        // notification's layout
        mRemoteViews = new RemoteViews(getPackageName(), R.layout.custom_notification_small);
        // notification's icon
        mRemoteViews.setImageViewResource(R.id.notif_icon, R.drawable.a);
        // notification's title
        mRemoteViews.setTextViewText(R.id.notif_title, getResources().getString(R.string.app_name));
        // notification's content
        mRemoteViews.setTextViewText(R.id.notif_content, getResources().getString(R.string.content_text));

        mBuilder = new NotificationCompat.Builder(this,"Mynot");

        CharSequence ticker = getResources().getString(R.string.ticker_text);
        int apiVersion = Build.VERSION.SDK_INT;

        if (apiVersion < Build.VERSION_CODES.HONEYCOMB) {
            mNotification = new Notification(R.drawable.ic_launcher_foreground, ticker, System.currentTimeMillis());
            mNotification.contentView = mRemoteViews;
            mNotification.contentIntent = pendIntent;

            mNotification.flags |= Notification.FLAG_NO_CLEAR; //Do not clear the notification
            mNotification.defaults |= Notification.DEFAULT_LIGHTS;

            // starting service with notification in foreground mode
            //startForeground(NOTIF_ID, mNotification);

        }else if (apiVersion >= Build.VERSION_CODES.HONEYCOMB) {
            mBuilder.setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setAutoCancel(false)
                    .setOngoing(true)
                    .setContentIntent(pendIntent)
                    .setContent(mRemoteViews)
                    .setTicker(ticker);

            mNotificationManager.notify(NOTIF_ID, mBuilder.build());
            // starting service with notification in foreground mode
            //startForeground(NOTIF_ID, mBuilder.build());
        }
    }

    // use this method to update the Notification's UI
    public static void updateNotification(int id){

        int api = Build.VERSION.SDK_INT;
        // update the icon
        //mRemoteViews.setImageViewResource(R.id.notif_icon, R.drawable.b);
        //working 1
        //mRemoteViews.setImageViewResource(R.id.notif_icon,id);

        expandedRemote.setImageViewResource(R.id.image_view,id);
        // update the title
        //mRemoteViews.setTextViewText(R.id.notif_title, getResources().getString(R.string.new_title));
        // update the content
        //mRemoteViews.setTextViewText(R.id.notif_content, getResources().getString(R.string.new_content_text));

        // update the notification
        if (api < Build.VERSION_CODES.HONEYCOMB) {
            mNotificationManager.notify(NOTIF_ID, mNotification);
        }else if (api >= Build.VERSION_CODES.HONEYCOMB) {
            mNotificationManager.notify(NOTIF_ID, mBuilder.build());
        }
    }

    public ArrayList<Bitmap> giftoframes(){


        ArrayList<Bitmap> bitmaps = new ArrayList<>();
        Bitmap bitmap[]=new Bitmap[1000];

        Glide.with(this)
                .asGif()
                .load(R.raw.giphy)
                .dontAnimate()
                .into(new SimpleTarget<GifDrawable>() {

                    @Override
                    public void onResourceReady(@NonNull GifDrawable resource, @Nullable Transition<? super GifDrawable> transition) {
                        try {
                            Log.d(M,"inside method");
                            Object GifState = resource.getConstantState();
                            Field frameLoader = GifState.getClass().getDeclaredField("frameLoader");
                            frameLoader.setAccessible(true);
                            Object gifFrameLoader = frameLoader.get(GifState);

                            Log.d(M,"inside try");

                            Bitmap bmp = BitmapFactory.decodeResource(getResources(),R.drawable.a);

                            Field gifDecoder = gifFrameLoader.getClass().getDeclaredField("gifDecoder");
                            gifDecoder.setAccessible(true);
                            StandardGifDecoder standardGifDecoder = (StandardGifDecoder) gifDecoder.get(gifFrameLoader);
                            for (int i = 0; i < standardGifDecoder.getFrameCount(); i++) {
                                Log.d(M,"inside loop");
                                standardGifDecoder.advance();
                                bitmaps.add(standardGifDecoder.getNextFrame());
                                //mine
                                //bitmap[i]=standardGifDecoder.getNextFrame();
                                Bitmap al=bitmaps.get(i);
                                if(al.sameAs(bmp)){
                                    Log.d(M,"same as a");
                                }
                            }
                            Log.d(M,"outside loop");
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        Log.d(M,"outside try");
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                        Log.d(M,"inside cleared");
                        return;
                    }
                });
        Log.d(M,"outside all");
        return bitmaps;

    }

    public void glidecheck(ArrayList<Bitmap> bitmaps){
            //*Drawable d = new BitmapDrawable(getResources(), bitmaps.get(0));
            Bitmap bmp = BitmapFactory.decodeResource(getResources(),R.drawable.a);

            ImageView check = (ImageView) findViewById(R.id.image_view);
            //check.setImageBitmap(bitmap[1]);

            //Bitmap a=bitmap[1];
            Bitmap al=bitmaps.get(0);
            if(al.sameAs(bmp)){
                Log.d(M,"same as a");
            }

            //chck glide
        /*String url="https://picsum.photos/600";
        String gifu="https://media0.giphy.com/media/xTcnSXDc4tRe27yS8o/giphy.webp";
        ImageView check = (ImageView) findViewById(R.id.image_view);

        Glide.with(this)
                .load(url)
                .centerCrop()
                .into(check);*/

    }

    public void giftoframes2(){
        /*DrawableRequestBuilder builder = Glide.with(this).load(R.raw.giphy);
        builder.listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                if (resource.isAnimated()) {
                    target.onResourceReady(new GlideBitmapDrawable(null, ((GifDrawable) resource).getFirstFrame()), null);
                }
                return handled;
            }
        });
        builder.into(mImageView);*/

    }


}

//                .load("https://media4.giphy.com/media/7YUQ5zpf8ZUHeO7QAe/giphy.gif?cid=ecf05e4724c036aa32a018605fb7bdd234e294e9c6a93232&rid=giphy.gif")
//            String audioPath = "android.resource://" + getPackageName() + "/" + R.raw.b;