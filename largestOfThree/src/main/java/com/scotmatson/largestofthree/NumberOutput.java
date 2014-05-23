package com.scotmatson.largestofthree;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class NumberOutput extends Activity {

    Bundle bundle;
    int largestInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_number_output);

        initialize();
        attachFragment();
    }

    public void attachFragment() {
        FragmentResults fragmentResults = new FragmentResults();
        FragmentButtonOutput fragmentButtonOutput = new FragmentButtonOutput();

        fragmentResults.setArguments(bundle);
        fragmentButtonOutput.setArguments(bundle);

        FragmentManager fragmentManager = getFragmentManager();

        fragmentManager.beginTransaction()
                .add(R.id.llSubmitFragment, fragmentResults)
                .add(R.id.llOutputActions, fragmentButtonOutput)
                .commit();

    }

    public void initialize() {
        bundle = new Bundle();
        bundle = getIntent().getExtras();
        largestInt = bundle.getInt("largestInt");
        bundle.putInt("largestInt", largestInt);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
