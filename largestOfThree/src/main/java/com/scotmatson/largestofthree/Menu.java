package com.scotmatson.largestofthree;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends Activity implements View.OnClickListener {

    Button bPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        attachFragment();
        initialize();
    }

    private void initialize() {
        bPlay = (Button) findViewById(R.id.playGame);
        bPlay.setOnClickListener(this);
    }

    private void attachFragment() {
        // Provides different methods for fragments to interact inside the Activity
        FragmentManager fragmentManager = getFragmentManager();

        // Allows add / remove / replace / define / etc... for fragments
        // We are ready to begin editing the fragments we are using
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Creates a new instance of the fragment
        FragmentScoreboard fragmentScoreboard = new FragmentScoreboard();

        // Adds the fragment to the activity. Note the ID and instance given as an argument
        fragmentTransaction.add(R.id.scoreboardFragment, fragmentScoreboard);

        // Schedules for the addition of the fragment to the Activity
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.playGame:
                Intent intent = new Intent("com.scotmatson.largestofthree.NUMBERINPUT");
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
