package com.scotmatson.largestofthree;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentScoreboard extends Fragment {

    // onCreateView will be called whenever this fragment is created.
    // The LayoutInflater will inflate the XML file into the view.
    // ViewGroup is an abstract class that all ViewGroups extend
    // Bundle is used to pass data between activities.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Puts the fragment on the device - defines what we want to inflate
        // push in container and use false to NOT attach the fragment to the main activity
        return inflater.inflate(R.layout.fragment_scoreboard, container, false);

    }
}
