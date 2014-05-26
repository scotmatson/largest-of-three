package com.scotmatson.largestofthree;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentScoreboard extends Fragment {

    TextView tvPlayerName, tvPlayerScore;
    public static final String HIGHSCORES = "player_scores";
    SharedPreferences savedScores;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_scoreboard, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tvPlayerName = (TextView) getView().findViewById(R.id.tvPlayerName);
        tvPlayerScore = (TextView) getView().findViewById(R.id.tvPlayerScore);

        savedScores = getActivity().getSharedPreferences(HIGHSCORES, 0);
        String playerName = savedScores.getString("playerName", "Player 01");
        int playerScore = savedScores.getInt("playerScore", 0);

        tvPlayerName.setText(playerName);
        tvPlayerScore.setText(String.valueOf(playerScore));
    }
}
