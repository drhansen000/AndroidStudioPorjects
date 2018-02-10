package com.example.drhansen000.prove05;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This activity retrieves the data from the main activity and displays it. Then, if the user
 * clicks the "Save Scripture" button, it will save the data into the shared preferences
 * @Author Daniel Hansen
 */

public class DisplayScriptureActivity extends AppCompatActivity {
    public static final String TAG = "IntentReceived";
    private String bookSent;
    private String chapterSent;
    private String verseSent;
    private String scriptureSent;

    /**
     * This method retrieves the string sent from the main activity, displays it, and creates a
     * debug statement declaring that the intent was received.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_scripture);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        bookSent = intent.getStringExtra("Book");
        chapterSent = intent.getStringExtra("Chapter");
        verseSent = intent.getStringExtra("Verse");

        //correctly format the data into one string
        scriptureSent = bookSent + " " + chapterSent + ":" + verseSent;

        //create debug statement declaring that the intent was received
        Log.d(TAG, "Received intent with " + scriptureSent + ".");

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.displayScripture);
        textView.setText(scriptureSent);
    }

    /**
     * This method saves the passed in string into shared Preferences.
     * Code retrieved from:
     * https://stackoverflow.com/questions/12074156/android-storing-retrieving-
     * strings-with-shared-preferences
     * @param view
     */
    public void saveScripture(View view) {
        //create the context to save the data, just reference the activity being used
        Context context = this;

        //look at the URL below to learn more about the following variable instantiation
        // https://developer.android.com/reference/android/preference/PreferenceManager.html
        SharedPreferences.Editor preferencesEditor =
                PreferenceManager.getDefaultSharedPreferences(context).edit();

        //This adds a string attached to a key that I create
        preferencesEditor.putString("BOOKLABEL", bookSent);
        preferencesEditor.putString("CHAPTERLABEL", chapterSent);
        preferencesEditor.putString("VERSELABEL", verseSent);
        preferencesEditor.apply(); //this applies the changes to the preferences

        //make the toast
        Toast.makeText(getApplicationContext(), "\"" + scriptureSent +
                        "\" was saved succesfully", Toast.LENGTH_LONG).show();
    }

}
