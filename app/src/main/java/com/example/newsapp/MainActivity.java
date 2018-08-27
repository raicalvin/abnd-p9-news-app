package com.example.newsapp;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.content.Context;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderCallbacks<List<Feature>> {

    private FeatureAdapter mAdapter;

    /** Request URL from Guardian News API */
    private static final String GUARDIAN_REQUEST_URL = "https://content.guardianapis.com/search?api-key=27c64b7b-c582-4358-b169-12ce8c793e41";

    /**
     * Constant value for the Feature loader ID
     */
    private static final int FEATURE_LOADER_ID = 1;

    private TextView mEmptyStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView featuresListView = (ListView) findViewById(R.id.list);

        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        featuresListView.setEmptyView(mEmptyStateTextView);

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

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            // Reference to LoaderManager to interact with Loaders
            LoaderManager loaderManager = getLoaderManager();

            // Initialize loader, pass in ID, and pass in null for bundle.
            // Pass in this activity
            loaderManager.initLoader(FEATURE_LOADER_ID, null, this);
        } else {
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }


    }

    @Override
    public Loader<List<Feature>> onCreateLoader(int i, Bundle bundle) {
        // Create a new loader for URL
        return new FeatureNewsLoader(this, GUARDIAN_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Feature>> loader, List<Feature> features) {
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        mEmptyStateTextView.setText(R.string.no_news_story);

        // Clear the adapter of previous news data
        mAdapter.clear();
        if (features != null && !features.isEmpty()) {
            mAdapter.addAll(features);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Feature>> loader) {
        mAdapter.clear();
    }

}
