package com.scotmatson.largestofthree;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;


public class NumberOutput extends ActionBarActivity implements View.OnClickListener {

    TextView tvMaxInt;
    Button bPlayAgain;
    MediaPlayer mpTone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_number_output);
        initialize();
    }

    public void initialize() {
        tvMaxInt = (TextView) findViewById(R.id.tvMaxInt);
        bPlayAgain = (Button) findViewById(R.id.bPlayAgain);

        bPlayAgain.setOnClickListener(this);

        mpTone = MediaPlayer.create(this, R.raw.tone);

        Intent receivedInput = getIntent();
        int largestInt = receivedInput.getIntExtra("largestInt", 0);
        tvMaxInt.setText(String.valueOf(largestInt));
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.bPlayAgain:
                Intent inputIntent = new Intent("com.scotmatson.NUMBERINPUT");
                mpTone.start();
                try {
                    Thread.sleep(50);
                }
                catch(InterruptedException e) {
                    e.printStackTrace();
                }
                startActivity(inputIntent);
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
