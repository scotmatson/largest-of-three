package com.scotmatson.largestofthree;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentResults extends Fragment {

    private static final String TAG = "FragmentResults";
    TextView tvMaxInt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_results, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tvMaxInt = (TextView) getView().findViewById(R.id.tvMaxInt);
        Bundle bundle = this.getArguments();
        int i = bundle.getInt("largestInt");
        tvMaxInt.setText(String.valueOf(i));
    }
}
