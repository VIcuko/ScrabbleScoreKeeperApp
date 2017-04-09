package com.example.android.scrabblescorekeeperapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;

import static android.view.View.Z;

public class MainActivity extends AppCompatActivity {
    //  Tracks score for Player Eagle
    private TextView eagleScoreDisplay;

    //  Tracks score for Player Shark
    private TextView sharkScoreDisplay;

    // Tracks the previous used words for Player Eagle
    private TextView eaglePreviousWords;

    // Tracks the previous used words for Player Shark
    private TextView sharkPreviousWords;

    // Receives input for next word from user
    private String eagleNextWord;

    // Receives input for next word from user
    private String sharkNextWord;

    // Assigns values to letters in game
    String[] value_of_letters = {" ","eaionrtlsu","dg","bcmp","fhvwy","k","","","jx","","qz"};

    //
    String[] word_dictionary;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eagleScoreDisplay = (TextView) findViewById(R.id.eagle_score);
        sharkScoreDisplay = (TextView) findViewById(R.id.eagle_score);
        eaglePreviousWords = (TextView) findViewById(R.id.eagle_previous_words);
        sharkPreviousWords = (TextView) findViewById(R.id.shark_previous_words);
        eagleNextWord = ((EditText) findViewById(R.id.eagle_word)).toString();
        sharkNextWord = ((EditText) findViewById(R.id.shark_word)).toString();
        displayScore(eagleScoreDisplay,0);
        displayScore(sharkScoreDisplay,0);
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
     * @return String[]
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
     *
     * @param textview indicates the TextView to be updated
     * @param score integer with the value to be updated in the TextView
     */
    public void displayScore(TextView textview, int score) {
        textview.setText(String.valueOf(score));
    }


    /**
     * Increase the score of Player Eagle.
     */
    public void addPoints(TextView score_display, String introduced_word) {
        String message;
        int new_score = 0;

        if (introduced_word.equals(null) ||
                introduced_word.equals("") || introduced_word.matches("[^a-zA-Z]")) {
            message = "Sorry, but it seems the word you introduced has invalid characters";
        }

        else if (validateWordInDictionary(eagleNextWord)){
            int points = calculatePoints(introduced_word);
            new_score = Integer.parseInt(score_display.toString()) + points;
            message = "Woohoo!!\nYou added " + points + " points";
        }

        else {
            message = "Sorry, it seems that word isn't in the Dictionary";
        };

        if (new_score<=0) {
            //Enseñar solo el mensaje
        }
        else {
            //Enseñar mensaje
            displayScore(score_display,new_score);
        };
    }

    /**
     *
     * @param introduced_word
     */
    public boolean validateWordInDictionary(String introduced_word){
        String word_to_validate = introduced_word.toUpperCase();

        int lower_limit = 0;
        int higher_limit = (word_dictionary.length-1)/2;
        boolean word_found = false;
        boolean end_loop = false;

        if (lower_limit<=higher_limit){
            end_loop = true;
        }

        else {
                eagleNextWord.compareTo(word_dictionary[lower_limit]);
            };
        return word_found;
        };

    /**
     * Increase the score of Player Shark.
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