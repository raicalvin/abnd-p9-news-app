package com.example.newsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class FeatureNewsLoader extends AsyncTaskLoader<List<Feature>> {

    public FeatureNewsLoader(Context context, String url) {
        super(context);
        // TODO: Finish implementing constructor
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Feature> loadInBackground() {
        // TODO: Implement this method
    }
}
