package com.example.newsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

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
}
