package com.example.drhan.displaynumbers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    private static String FILE = "numbers.txt";
    private ProgressBar progressBar;
    ListView numberListView;
    ArrayAdapter<Integer> numberArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //connect the numberListView to the ListView on the UI
        numberListView = findViewById(R.id.listView);

        //instantiate ArrayAdapter
        numberArrayAdapter = new ArrayAdapter<>(this, R.layout.item_layout);

        //connect the ArrayAdapter to the ListView
        numberListView.setAdapter(numberArrayAdapter);

        //create references
        progressBar       = findViewById(R.id.progressBar);
        Button createFile = findViewById(R.id.Save);
        Button loadFile   = findViewById(R.id.Load);

        createFile.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Create c = new Create(progressBar, FILE, MainActivity.this);
                c.execute();
            }
        }
        );

        loadFile.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Load l = new Load(progressBar, FILE, MainActivity.this, numberArrayAdapter);
                l.execute();
            }
        }
        );


//        // First create the List to fulfill the parameter requirements of the ArrayAdapter
//        numberList = new ArrayList<>();
//        //pass in this context, the layout for each textView, and the numberList that was just
//        //instantiated
//        numberListAdapter = new ArrayAdapter<Integer>(this, R.layout.item_layout, numberList);
//
//        // Now connect the ArrayAdapter to the ListView
//        ListView listView = (ListView) findViewById(R.id.listView);
//        listView.setAdapter(numberListAdapter);
    }

//    /**
//     *
//     * @param view
//     */
//    public void createFile(View view) {
//        progressBar.setProgress(0);
//
//        try {
//            FileOutputStream outputStream;
//            String insertLine = new String();
//
//            //use the output stream so that we can open and write to a specified file
//            outputStream = openFileOutput(FILE, Context.MODE_PRIVATE);
//
//            for(int i = 1; i <= 10; i++) {
//                //format is a number, then a newline
//                insertLine = insertLine.format("%d%n", i);
//                //write to the file, converting the string into bytes
//                outputStream.write(insertLine.getBytes());
//                //update the progressBar
//                progressBar.incrementProgressBy(10);
//                //simulate heavy program by adding a time delay
//                Thread.sleep(250);
//            }
//            //close the file
//            outputStream.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        if(progressBar.getProgress() == 100) {
//
//        }
//    }

//    public void loadFile(View view) {
//        //create a string that will take in the file's values one line at a time
//        String fileLine;
//        try {
//            //Open the file and put it into an inputStream
//            FileInputStream inputStream = this.openFileInput(FILE);
//            //create BufferedReader to read specified file
//            BufferedReader readFileVar = new BufferedReader(new InputStreamReader(inputStream));
//            //initiate the progress bar at 0
//            progressBar.setProgress(0);
//            while ((fileLine = readFileVar.readLine()) != null) {
//                //convert the file line into an Integer
//                Integer i = Integer.parseInt(fileLine);
//                //add the Integer to the adapter
//                numberListAdapter.add(i);
//                //increment the progress bar
//                progressBar.incrementProgressBy(i * 10);
//                //simulate a heavier load
//                Thread.sleep(250);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        //let the user know that we're done loading
//        if (progressBar.getProgress() == 100) {
//            Toast.makeText(getApplicationContext(), "Finished Loading", Toast.LENGTH_SHORT).show();
//        }
//    }
//
    public void clearList(View view) {
        // Reset the progress bar
        progressBar.setProgress(0);

        // Clear the list
        numberArrayAdapter.clear();

        // Let the user know that everything's cleared
        Toast.makeText(this, "Cleared", Toast.LENGTH_SHORT).show();
    }
}
