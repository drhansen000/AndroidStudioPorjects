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
    }

    public void clearList(View view) {
        // Reset the progress bar
        progressBar.setProgress(0);

        // Clear the list
        numberArrayAdapter.clear();

        // Let the user know that everything's cleared
        Toast.makeText(this, "Cleared", Toast.LENGTH_SHORT).show();
    }
}
