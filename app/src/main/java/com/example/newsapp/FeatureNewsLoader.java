package com.example.newsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class FeatureNewsLoader extends AsyncTaskLoader<List<Feature>> {

    /** Tag log for console messages output */
    private static final String LOG_TAG = FeatureNewsLoader.class.getName();

    /** Variable to hold Query URL */
    private String mUrl;

    public FeatureNewsLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Feature> loadInBackground() {
        List<Feature> features = QueryUtils.fetchNewsFeatures(mUrl);
        return features;
    }
}
