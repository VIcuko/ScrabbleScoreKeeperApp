package com.example.android.scrabblescorekeeperapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //  Tracks score for Team A
    int eagleScore = 0;

    //  Tracks score for Team B
    int sharkScore = 0;

    // Assigns values to letters in game
    String[] value_of_letters = {" ","eaionrtlsu","dg","bcmp","fhvwy","k","","","jx","","qz"};

    BufferedReader word_list = new InputStreamReader(getAssets().open(""));
    String str;

    List<String> list = new ArrayList<String>();
    while((str = word_list.readLine()) != null){
        list.add(str);
    };

    String[] stringArr = list.toArray(new String[0]);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayForTeamA(scoreTeamA);
    }

    /**
     * Displays the given score for Team A.
     */
    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Displays the given score for Team A.
     */
    public void displayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Increase the score of Team A by 3.
     */
    public void threePointsTeamA(View view) {
        scoreTeamA += 3;
        displayForTeamA(scoreTeamA);
    }

    /**
     * Increase the score of Team B by 3.
     */
    public void threePointsTeamB(View view) {
        scoreTeamB += 3;
        displayForTeamB(scoreTeamB);
    }

    /**
     * Reset the result for both teams.
     */
    public void newGame(View view) {
        scoreTeamA = 0;
        scoreTeamB = 0;
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
    }

}