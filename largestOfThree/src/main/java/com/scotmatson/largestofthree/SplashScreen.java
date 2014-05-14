package com.scotmatson.largestofthree;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class SplashScreen extends Activity {

    MediaPlayer splashSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        splashSound = MediaPlayer.create(this, R.raw.setuniman);
        splashSound.start();
        Thread timer = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                }
                catch(InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    Intent intent = new Intent("com.scotmatson.NUMBERINPUT");
                    startActivity(intent);
                }
            }
        };
        timer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        splashSound.release();
        finish();
    }
}