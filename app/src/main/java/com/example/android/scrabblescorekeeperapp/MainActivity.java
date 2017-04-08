package com.example.android.scrabblescorekeeperapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //  Tracks score for Team A
    int scoreTeamA = 0;

    //  Tracks score for Team B
    int scoreTeamB = 0;

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
     * Increase the score of Team A by 2.
     */
    public void twoPointsTeamA(View view) {
        scoreTeamA += 2;
        displayForTeamA(scoreTeamA);
    }

    /**
     * Increase the score of Team B by 2.
     */
    public void twoPointsTeamB(View view) {
        scoreTeamB += 2;
        displayForTeamB(scoreTeamB);
    }

    /**
     * Increase the score of Team A by 1.
     */
    public void onePointTeamA(View view) {
        scoreTeamA += 1;
        displayForTeamA(scoreTeamA);
    }

    /**
     * Increase the score of Team B by 1.
     */
    public void onePointTeamB(View view) {
        scoreTeamB += 1;
        displayForTeamB(scoreTeamB);
    }

    /**
     * Reset the result for both teams.
     */
    public void resetResult(View view) {
        scoreTeamA = 0;
        scoreTeamB = 0;
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
    }

}