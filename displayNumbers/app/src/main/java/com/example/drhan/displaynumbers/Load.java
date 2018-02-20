package com.example.drhan.displaynumbers;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * Created by drhan on 2/19/2018.
 */

public class Load extends AsyncTask <Void, String, Void>
{
    ProgressBar progressBar;
    String FILE;
    Context currentActivity;
    ArrayAdapter<Integer> numberArrayAdapter;

    /**
     * constructor
     * @param progressBar
     */
    Load(ProgressBar progressBar, String FILE, Context currentActivity, ArrayAdapter<Integer> numberArrayAdapter)
    {
        this.progressBar        = progressBar;
        this.FILE               = FILE;
        this.currentActivity    = currentActivity;
        this.numberArrayAdapter = numberArrayAdapter;
    }

    /**
     * this executes first on the main thread
     */
    @Override
    protected void onPreExecute()
    {
        progressBar.setProgress(0);
        super.onPreExecute();
    }

    /**
     * This happens after onPreExecute
     * this method can't update the user interface since it's running on the background thread
     * @param voids
     * @return
     */
    @Override
    protected Void doInBackground(Void... voids)
    {
        //create a string that will take in the file's values one line at a time
        String fileLine;
        try
        {
            //Open the file and put it into an inputStream
            FileInputStream inputStream = currentActivity.openFileInput(FILE);
            //create BufferedReader to read specified file
            BufferedReader readFileVar = new BufferedReader(new InputStreamReader(inputStream));
            //initiate the progress bar at 0
            progressBar.setProgress(0);

            while ((fileLine = readFileVar.readLine()) != null)
            {
                publishProgress(fileLine);
                //simulate a heavier load
                Thread.sleep(250);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * this method updates the main thread when it's called in doInBackground method
     */
    @Override
    protected void onProgressUpdate(String... values)
    {
        //take in the first item(there's only one), change it into an Integer, and add it to Adapter
        numberArrayAdapter.add(Integer.parseInt(values[0]));
        System.out.println(Integer.parseInt(values[0]));
        //update the progressBar by +10 every call
        progressBar.incrementProgressBy(10);
        super.onProgressUpdate(values);
    }

    /**
     * this method updates the main thread when the doInBackground method finishes running
     */
    @Override
    protected void onPostExecute(Void aVoid)
    {
        //notify the user that we are all done
        Toast.makeText(currentActivity, "Finished Loading", Toast.LENGTH_SHORT).show();
        super.onPostExecute(aVoid);
    }
}
