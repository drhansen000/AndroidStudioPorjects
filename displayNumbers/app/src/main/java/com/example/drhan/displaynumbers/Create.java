package com.example.drhan.displaynumbers;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.FileOutputStream;

/**
 * Created by drhan on 2/19/2018.
 */

public class Create extends AsyncTask <Void, Void, Void>
{
    ProgressBar progressBar;
    String FILE;
    Context currentActivity;

    /**
     * constructor references the class to the progressBar in main
     * @param progressBar
     */
    Create(ProgressBar progressBar, String FILE, Context currentActivity)
    {
        this.progressBar = progressBar;
        this.FILE = FILE;
        this.currentActivity = currentActivity;
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
        try
        {
            FileOutputStream outputStream;

            //use the output stream so that we can open and write to a specified file
            outputStream = currentActivity.openFileOutput(FILE, Context.MODE_PRIVATE);

            for(int i = 1; i <= 10; i++)
            {
                String insertLine;
                //format is a number, then a newline
                insertLine = String.format("%d%n", i);
                //write to the file, converting the string into bytes
                outputStream.write(insertLine.getBytes());
                //call the onProgressUpdate method to update progressBar
                onProgressUpdate();
                //simulate heavy program by adding a time delay
                Thread.sleep(250);
            }
            //close the file
            outputStream.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * this method updates the main thread when it's called in doInBackground method
     */
    @Override
    protected void onProgressUpdate(Void... values)
    {
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
        Toast.makeText(currentActivity, "Finished Saving", Toast.LENGTH_SHORT).show();
        super.onPostExecute(aVoid);
    }
}
