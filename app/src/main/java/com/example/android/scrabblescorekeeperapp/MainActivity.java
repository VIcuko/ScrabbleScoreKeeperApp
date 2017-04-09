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

import static android.R.id.message;
import static android.view.View.Z;
import static com.example.android.scrabblescorekeeperapp.R.id.shark_score;

public class MainActivity extends AppCompatActivity {
    //  Tracks score for Player Eagle
    private TextView eagleScoreDisplay;
    int eagle_score = 0;

    //  Tracks score for Player Shark
    private TextView sharkScoreDisplay;
    int shark_score = 0;

    // Tracks the previous used words for Player Eagle
    private TextView eaglePreviousWords;
    String eagle_previous_words;

    // Tracks the previous used words for Player Shark
    private TextView sharkPreviousWords;
    String shark_previous_words;

    // Receives input for next word from user
    private EditText eagleNextWord;

    // Receives input for next word from user
    private EditText sharkNextWord;

    // Assigns values to letters in game
    String[] value_of_letters = {" ","eaionrtlsu","dg","bcmp","fhvwy","k","","","jx","","qz"};

    //
    String[] word_dictionary;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eagleScoreDisplay = (TextView) findViewById(R.id.eagle_score);
        sharkScoreDisplay = (TextView) findViewById(R.id.shark_score);
        eaglePreviousWords = (TextView) findViewById(R.id.eagle_previous_words);
        sharkPreviousWords = (TextView) findViewById(R.id.shark_previous_words);
        eagleNextWord = (EditText) findViewById(R.id.eagle_word);
        sharkNextWord = (EditText) findViewById(R.id.shark_word);
        displayScore(eagleScoreDisplay,eagle_score);
        displayScore(sharkScoreDisplay,shark_score);
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

    public void addPointsEagle(View view){
        int new_points = getPoints(eagle_score ,eagleNextWord, eaglePreviousWords);
        eagle_score = eagle_score + new_points;
        displayScore(eagleScoreDisplay,eagle_score);
        displayPreviousWords(eagleNextWord.getText().toString()+" ("+new_points+")", eaglePreviousWords);
        eagleNextWord.setText("");
    };

    public void addPointsShark(View view){
        int new_points = getPoints(shark_score ,sharkNextWord, sharkPreviousWords);
        shark_score = shark_score + new_points;
        displayScore(sharkScoreDisplay,shark_score);
        displayPreviousWords(sharkNextWord.getText().toString()+" ("+new_points+")", sharkPreviousWords);
        sharkNextWord.setText("");
    };

    /**
     * Increase the score of Player Eagle.
     */
    public int getPoints(int current_score, EditText text_field, TextView previous_words_list) {
        String introduced_word = text_field.getText().toString();
        String message;
        int points = 0;

        if (introduced_word.equals(null) ||
                introduced_word.equals("") || introduced_word.matches("[^a-zA-Z]")) {
            message = "Sorry, but it seems the word you introduced has invalid characters";
        }

        else if (validateWordInDictionary(introduced_word)){
            points = calculatePoints(introduced_word);
            message = "Woohoo!!\nYou added " + points + " points";
        }

        else {
            message = "Sorry, it seems that word isn't in the Dictionary";
        };
        //Mostrar correspondiente mensaje
        return points;
    }

    /**
     * Method to search the dictionary and validate if the word exists
     * @param introduced_word word introduced by user in EditText field
     */
    public boolean validateWordInDictionary(String introduced_word){
        String word_to_validate = introduced_word.toUpperCase();

        int lower_limit = 0;
        int higher_limit = word_dictionary.length;
        int compared_string_position;
        String compared_string;
        boolean word_found = false;
        boolean end_loop = false;

        while (end_loop==false) {
            compared_string_position = lower_limit + (higher_limit-lower_limit)/2;
            compared_string = word_dictionary[compared_string_position].toUpperCase();

            if (higher_limit-lower_limit<=1) {
                if (introduced_word.compareTo(word_dictionary[higher_limit])==0 || introduced_word.compareTo(word_dictionary[lower_limit])==0){
                    word_found = true;
                    end_loop = true;
                }

                else {
                    end_loop = true;
                };
            }

            else {
                int comparison = introduced_word.compareToIgnoreCase(compared_string);

                if (comparison < 0) {
                    higher_limit = compared_string_position;
                }

                else if (comparison > 0) {
                    lower_limit = compared_string_position;
                }

                else if (comparison == 0) {
                    word_found = true;
                    end_loop = true;
                };

            };
        };

        return word_found;
    };

    /**
     * This method calculates the points to be added for the word introduced by the user
     * @param introduced_word word introduced by user in EditText field
     * @return amount of points for the indicated word
     */
    public int calculatePoints(String introduced_word){
        int points = 0;
        String[] introduced_letters = introduced_word.split("");

        for (int i=0; i<introduced_letters.length; i++){

            for (int j=0; j<value_of_letters.length; j++){
              if (value_of_letters[j].contains(introduced_letters[i])){
                  points = points + j;
                  break;
              };
          };
        };

        return points;
    };

    /**
     * This method includes the word introduced into the displayed list of previous words
     * @param introduced_word word introduced by user in EditText field
     */
    public void displayPreviousWords(String introduced_word, TextView previous_words_display){
        if (previous_words_display.getText().toString().contains("None yet!")){
            previous_words_display.setText(introduced_word.toLowerCase());
        }
        else{

            previous_words_display.setText(introduced_word.toLowerCase()+"\n"+previous_words_display.getText().toString());
        };
    };

    /**
     * This method clears the list of previous words and leaves the phrase "None yet!" instead
     * @param previous_words_list TextView with list of previous words
     */
    public void clearPreviousWords(TextView previous_words_list){
        previous_words_list.setText("None yet!");
    }

    public void undoLastWord(TextView previous_words_list, TextView score){
        int current_points = Integer.parseInt(score.getText().toString());
        String last_line = previous_words_list.getText().toString().split("\n")[0];
        int previous_points = Integer.parseInt(Character.toString(last_line.charAt(last_line.length()-1)));

    }
    /**
     * Reset the result for both teams.
     */
    public void newGame(View view) {
        displayScore(eagleScoreDisplay,0);
        displayScore(sharkScoreDisplay,0);
        clearPreviousWords(eaglePreviousWords);
        clearPreviousWords(sharkPreviousWords);
    }

}