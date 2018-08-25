package com.example.newsapp;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FeatureAdapter mAdapter;

    /** Request URL from Guardian News API */
    private static final String GUARDIAN_REQUEST_URL = "https://content.guardianapis.com/search?api-key=27c64b7b-c582-4358-b169-12ce8c793e41";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView featuresListView = (ListView) findViewById(R.id.list);

        // Create a new adapter that takes an empty list of features as input
        mAdapter = new FeatureAdapter(this, new ArrayList<Feature>());

        featuresListView.setAdapter(mAdapter);

        featuresListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Feature currentFeature = mAdapter.getItem(position);
                Uri earthquakeUri = Uri.parse(currentFeature.getUrl());

                // Create a new intent to view the Guardian URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });

        // Start the AsyncTask to fetch the earthquake data
        FeatureAsyncTask task = new FeatureAsyncTask();
        task.execute(GUARDIAN_REQUEST_URL);

    }

    private class FeatureAsyncTask extends AsyncTask<String, Void, List<Feature>> {

        @Override
        protected List<Feature> doInBackground(String... urls) {

            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            List<Feature> result = QueryUtils.fetchNewsFeatures(urls[0]);

            return result;

        }

        @Override
        protected void onPostExecute(List<Feature> data) {

            // Clear the adapter of previous news data
            mAdapter.clear();

            // If there is a valid list of {@link Feature}s, then add them to the adapter's
            // data set. This will trigger the ListView to update.
            if (data != null && !data.isEmpty()) {
                mAdapter.addAll(data);
            }

        }
    }

}
