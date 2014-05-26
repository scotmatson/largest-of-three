package com.scotmatson.largestofthree;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentButtonOutput extends Fragment implements View.OnClickListener{

    Bundle bundle;
    Button bPlayAgain, bSubmit;
    int largestInt;
    MediaPlayer mpTone;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_button_output, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initialize();
    }

    private void initialize() {
        bPlayAgain = (Button) getView().findViewById(R.id.bPlayAgain);
        bPlayAgain.setOnClickListener(this);

        bSubmit = (Button) getView().findViewById(R.id.bSubmit);
        bSubmit.setOnClickListener(this);

        // mpTone = MediaPlayer.create(this, R.raw.tone);

        bundle = this.getArguments();
        largestInt = bundle.getInt("largestInt");
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.bPlayAgain:
                Intent inputIntent = new Intent("com.scotmatson.largestofthree.NUMBERINPUT");
                //mpTone.start();
                try {
                    Thread.sleep(50);
                }
                catch(InterruptedException e) {
                    e.printStackTrace();
                }
                startActivity(inputIntent);
                break;

            case R.id.bSubmit:
                FragmentSubmit fragmentSubmit = new FragmentSubmit();

                fragmentSubmit.setArguments(bundle);

                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.llSubmitFragment, fragmentSubmit)
                        .remove(this)
                        .commit();
                break;
        }
    }
}
