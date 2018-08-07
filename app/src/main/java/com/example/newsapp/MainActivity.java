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

        ArrayList<Feature> features = new ArrayList<>();
        features.add(new Feature("Sports", "Kobe Comes Back", "August 9, 2017", 1));
        features.add(new Feature("Technology", "Something about iPhone", "March 10, 2017", 3));
        features.add(new Feature("Technology", "Android and iOS Merging", "December 18, 2017", 7));
        features.add(new Feature("Health", "Zombie Virus Spreading in Southern Texas", "January 23, 2017", 19));
        features.add(new Feature("Sports", "Warriors Coming to Oakland", "October 1, 2017", 21));
        features.add(new Feature("Shopping", "Black Friday Deals Happening Right Now", "April 7, 2017", 45));

        ListView featuresListView = (ListView) findViewById(R.id.list);

        FeatureAdapter adapter = new FeatureAdapter(this, features);

        featuresListView.setAdapter(adapter);

    }
}
