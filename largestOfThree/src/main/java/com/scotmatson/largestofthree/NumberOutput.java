package com.scotmatson.largestofthree;

import android.app.FragmentManager;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class NumberOutput extends ActionBarActivity implements View.OnClickListener {

    private static final String TAG = "NumberOutput";
    Button bPlayAgain;
    Button bSubmit;
    MediaPlayer mpTone;
    int largestInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_number_output);

        // To launch the fragment which displays the largestInt we must relay a Bundle
        // through this Activity first, and then to the Fragment. To ensure that this happens
        // I have made an integer value a required argument to launch the fragment. largestInt
        // was made into a class variable so that it may be used again to submit it to the
        // scoreboard.
        Bundle bundle = getIntent().getExtras();
        largestInt = bundle.getInt("largestInt");
        attachFragment(largestInt);

        initialize();
    }

    public void attachFragment(int largestInt) {
        // We can see here than a new Bundle is created and loaded with our integer.
        // Next, similar to setExtras, we use setArguments to send the data to our fragment.
        Bundle bundle = new Bundle();
        bundle.putInt("largestInt", largestInt);
        FragmentResults fragmentResults = new FragmentResults();
        fragmentResults.setArguments(bundle);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.llSubmitFragment, fragmentResults)
                .commit();
    }

    public void initialize() {
        bPlayAgain = (Button) findViewById(R.id.bPlayAgain);
        bPlayAgain.setOnClickListener(this);

        bSubmit = (Button) findViewById(R.id.bSubmit);
        bSubmit.setOnClickListener(this);

        mpTone = MediaPlayer.create(this, R.raw.tone);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.bPlayAgain:
                Intent inputIntent = new Intent("com.scotmatson.largestofthree.NUMBERINPUT");
                mpTone.start();
                try {
                    Thread.sleep(50);
                }
                catch(InterruptedException e) {
                    e.printStackTrace();
                }
                startActivity(inputIntent);
                break;
            case R.id.bSubmit:
                Bundle bundle = new Bundle();
                FragmentScoreboard fragmentScoreboard = new FragmentScoreboard();
                fragmentScoreboard.setArguments(bundle);
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                    .replace(R.id.llSubmitFragment, fragmentScoreboard)
                    .commit();
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
