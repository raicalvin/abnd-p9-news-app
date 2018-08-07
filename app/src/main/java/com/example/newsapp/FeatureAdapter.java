package com.example.newsapp;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class FeatureAdapter extends ArrayAdapter<Feature> {

    /**
     * Constructs a new {@link FeatureAdapter}.
     *
     * @param context of the app
     * @param features is a list of features, which is data source for adapter
     */
    public FeatureAdapter(Context context, List<Feature> features) {
        super(context, 0, features);
    }

    /**
     * Returns a list item view that displays information about the feature at position specified
     * @param position of the item within the list
     * @param convertView recycled view, if available
     * @param parent
     * @return custom view for the list view
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;

        // Check if an existing list view can be converted. If empty, inflate a new view.
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.news_item, parent, false);
        }

        Feature currentFeature = getItem(position);

        TextView categoryTV = listItemView.findViewById(R.id.tv_news_category);
        TextView titleTV = listItemView.findViewById(R.id.tv_news_title);
        TextView dateTV = listItemView.findViewById(R.id.tv_news_date);

        categoryTV.setText(currentFeature.getNewsCategory());
        titleTV.setText(currentFeature.getNewsTitle());
        dateTV.setText(currentFeature.getNewsDate());

        return listItemView;

    }

}
