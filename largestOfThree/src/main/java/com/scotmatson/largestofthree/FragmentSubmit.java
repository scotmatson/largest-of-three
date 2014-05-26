package com.scotmatson.largestofthree;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FragmentSubmit extends Fragment {

    Button bSaveAndGoToMenu;
    EditText etPlayerName;
    public static final String HIGHSCORES = "player_scores";
    SharedPreferences savedScores;
    TextView tvPlayerInt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_submit, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        etPlayerName = (EditText) getView().findViewById(R.id.playerName);

        // Access the TextView and populates it with the bundled largest integer
        tvPlayerInt = (TextView) getView().findViewById(R.id.tvMaxInt);
        Bundle bundle = this.getArguments();
        int largestInt = bundle.getInt("largestInt");
        tvPlayerInt.setText(String.valueOf(largestInt));

        savedScores = getActivity().getSharedPreferences(HIGHSCORES, 0);

        bSaveAndGoToMenu = (Button) getView().findViewById(R.id.bSaveAndReplay);
        bSaveAndGoToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Collecting name and integer values
                String playerName = etPlayerName.getText().toString();
                int playerScore = Integer.valueOf(tvPlayerInt.getText().toString());

                // Saving name and integer values
                SharedPreferences.Editor editor = savedScores.edit();
                editor.putString("playerName", playerName);
                editor.putInt("playerScore", playerScore);
                editor.commit();

                Intent intent = new Intent("com.scotmatson.largestofthree.MENU");
                startActivity(intent);
            }
        });
    }
}
