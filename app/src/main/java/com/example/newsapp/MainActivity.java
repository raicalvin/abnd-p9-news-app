package com.example.newsapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /** Request URL from Guardian News API */
    private static final String GUARDIAN_REQUEST_URL = "https://content.guardianapis.com/search?api-key=27c64b7b-c582-4358-b169-12ce8c793e41";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Feature> features = QueryUtils.extractFeatures();

        ListView featuresListView = (ListView) findViewById(R.id.list);

        FeatureAdapter adapter = new FeatureAdapter(this, features);

        featuresListView.setAdapter(adapter);

        // Use this for testing the JSON parsing
        QueryUtils.extractFeatures();

    }

    private class FeatureAsyncTask extends AsyncTask<String, Void, List<Feature>> {

        @Override
        protected List<Feature> doInBackground(String... strings) {
            return null;
        }

        @Override
        protected void onPostExecute(List<Feature> data) {

        }
    }

}
