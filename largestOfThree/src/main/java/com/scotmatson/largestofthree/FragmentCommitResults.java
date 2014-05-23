package com.scotmatson.largestofthree;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentCommitResults extends Fragment {

    Button bSaveAndReplay;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_commit_results, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bSaveAndReplay = (Button) getView().findViewById(R.id.bSaveAndReplay);
        bSaveAndReplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.scotmatson.largestofthree.MENU");
                startActivity(intent);
            }
        });
    }
}
