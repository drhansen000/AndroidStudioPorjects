package com.example.drhansen000.prove05;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

/**
 * This activity prompts the user for a scripture in 3 different EditText views. If the user clicks
 * the "Submit Scripture" button, then the data will be sent to the next activity, a debug statement
 * will be created and the next activity will appear. If the user clicks the "Load Scripture"
 * button, then the data that is stored in shared preferences will fill in the EditText views.
 * @Author Daniel Hansen
 */

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "IntentCreation";

    /**
     * Generic onCreate method
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method takes the data inputted in the EditText views and passes them into an intent,
     * which will be read by the DisplayScripture activity. It also creates a debug statement right
     * before the intent is created.
     * I chose to store pass the data as 3 separate strings so that the EditText views could be
     * filled by the correct values later on, instead of one whole scripture reference.
     * Help from https://stackoverflow.com/questions/9379023/sending-multiple-variable-values-
     * to-another-activity
     * @param view
     */
    public void sendScripture(View view) {
        //get the book information and convert it into a string
        View book = findViewById(R.id.book);
        EditText book_editText = (EditText) book;
        String sBook = book_editText.getText().toString();

        //get the chapter information and append it into a string
        View chapter = findViewById(R.id.chapter);
        EditText chapter_editText = (EditText) chapter;
        String sChapter = chapter_editText.getText().toString();

        //get the chapter information and append it into a string
        View verse = findViewById(R.id.verse);
        EditText verse_editText = (EditText) verse;
        String sVerse = verse_editText.getText().toString();

        //Display the debug statement
        Log.d(TAG, "About to create intent with " + sBook + " " + sChapter + ":" + sVerse);

        //create the intent and link it to second activity
        Intent intent = new Intent(this, DisplayScriptureActivity.class);

        //send the data
        intent.putExtra("Book", sBook);
        intent.putExtra("Chapter", sChapter);
        intent.putExtra("Verse", sVerse);

        //start the sendScripture activity
        startActivity(intent);
    }


    /**
     * Retrieves the data from shared preferences and auto-fills the EditText views with said data.
     * Code retrieved from:
     * https://stackoverflow.com/questions/12074156/android-storing-retrieving-
     * strings-with-shared-preferences
     * @param view
     */
    public void loadScripture(View view) {
        //create the context (it's the activity that we're currently in)
        Context context = this;

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);

        //store the retrieved data into strings. If there is no data, use the default values
        // passed in the second parameter
        String storedBook = prefs.getString("BOOKLABEL", "Book");
        String storedChapter = prefs.getString("CHAPTERLABEL", "Chapter");
        String storedVerse = prefs.getString("VERSELABEL", "Verse");

        //fill the correct EditText views
        EditText bookView = findViewById(R.id.book);
        EditText chapterView = findViewById(R.id.chapter);
        EditText verseView = findViewById(R.id.verse);

        //fill the EditText views with the correct information
        bookView.setText(storedBook);
        chapterView.setText(storedChapter);
        verseView.setText(storedVerse);
    }
}
