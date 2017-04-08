package com.example.android.scrabblescorekeeperapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    //  Tracks score for Team A
    int eagleScore = 3;

    //  Tracks score for Team B
    int sharkScore = 3;

    // Assigns values to letters in game
    String[] value_of_letters = {" ","eaionrtlsu","dg","bcmp","fhvwy","k","","","jx","","qz"};

    //
    String[] word_dictionary;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayForEagle(eagleScore);
        displayForEagle(sharkScore);
        loadDictionary();
    }

    /**
     * Loads all the words from the dictionary in the attached txt file into an array
     */

    public void loadDictionary(){
//        int word_list = english_dictionary;
//        InputStream dictionary_fil = getResources().openRawResource(R.raw.english_dictionary);
        InputStream dictionary_file = getResources().openRawResource(
                getResources().getIdentifier("english_dictionary",
                        "raw", getPackageName()));

        word_dictionary = readTextFile(dictionary_file);
    };

    /**
     *
     * @param inputStream file to be read
     * @return
     */
    public String[] readTextFile(InputStream inputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        byte buf[] = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {

        }
        String[] final_array = outputStream.toString().split("\n");

        return final_array;

    };

    /**
     * Displays the given score for Team A.
     */
    public void displayForEagle(int score) {
        TextView scoreView = (TextView) findViewById(R.id.eagle_score);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Displays the given score for Team A.
     */
    public void displayForShark(int score) {
        TextView scoreView = (TextView) findViewById(R.id.shark_score);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Increase the score of Team A by 3.
     */
    public void threePointsTeamA(View view) {
        eagleScore += 3;
        displayForEagle(eagleScore);
    }

    /**
     * Increase the score of Team B by 3.
     */
    public void threePointsTeamB(View view) {
        sharkScore += 3;
        displayForShark(sharkScore);
    }

    /**
     * Reset the result for both teams.
     */
    public void newGame(View view) {
        eagleScore = 0;
        sharkScore = 0;
        displayForEagle(eagleScore);
        displayForShark(sharkScore);
    }

}