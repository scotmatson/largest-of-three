package com.scotmatson.largestofthree;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentSubmit extends Fragment {

    TextView tvMaxInt;
    Button bSaveAndReplay;
    EditText saveNameAndInt;
    //String myText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_submit, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Acessess the TextView and populates it with the bundled largest integer
        tvMaxInt = (TextView) getView().findViewById(R.id.tvMaxInt);
        Bundle bundle = this.getArguments();
        int largestInt = bundle.getInt("largestInt");
        tvMaxInt.setText(String.valueOf(largestInt));

        bSaveAndReplay = (Button) getView().findViewById(R.id.bSaveAndReplay);
        saveNameAndInt = (EditText) getView().findViewById(R.id.playerName);

        bSaveAndReplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String myText = saveNameAndInt.getText().toString();

                Toast toast = Toast.makeText(getActivity(), myText, Toast.LENGTH_SHORT);
                toast.show();

                //Intent intent = new Intent("com.scotmatson.largestofthree.MENU");
                //startActivity(intent);
            }
        });
    }
}
