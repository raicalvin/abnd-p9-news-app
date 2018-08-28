package com.example.newsapp;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * These methods are Helper methods that help request and receive Guardian news stories.
 */

public final class QueryUtils {

    public static final String LOG_TAG = "QueryUtils";

    /**
     * This QueryUtils class is only meant to hold static variables and methods that can be accessed directly from the class name. A QueryUtils object should NOT be created.
     */
    private QueryUtils() {}

    public static List<Feature> extractFeaturesFromJson(String newsJSON) {

        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(newsJSON)) {
            return null;
        }

        // Empty Features ArrayList to add features to
        List<Feature> features = new ArrayList<>();

        // Parse the JSON response
        // If there is an error with JSON format, exception thrown
        // Catch exception, print error to logs
        try {

            // Parse the JSON response and build a list of Feature objects
            JSONObject root = new JSONObject(newsJSON);
            JSONObject response = root.getJSONObject("response");
            JSONArray results = response.getJSONArray("results");

            JSONObject currentResultObj;
            String currentDateString;
            String currentTitleString;
            String currentCategoryString;
            String currentWebsiteURLString;

            JSONArray currentTagsArray;
            JSONObject currentTagsObject;
            String currentWebTitleString;


            for (int i = 0; i < results.length(); i++) {
                currentResultObj = results.getJSONObject(i);
                currentDateString = currentResultObj.getString("webPublicationDate");
                currentTitleString = currentResultObj.getString("webTitle");
                currentCategoryString = currentResultObj.getString("sectionName");
                currentWebsiteURLString = currentResultObj.getString("webUrl");
                currentTagsArray = currentResultObj.getJSONArray("tags");
                currentTagsObject = currentTagsArray.getJSONObject(0);
                currentWebTitleString = currentTagsObject.getString("webTitle");
                Log.i(LOG_TAG, "extractFeaturesFromJson: " + currentWebTitleString);
                features.add(new Feature(
                        currentCategoryString,
                        currentTitleString,
                        formatDate(currentDateString),
                        1,
                        currentWebsiteURLString));
                Log.i("QueryUtils", "extractFeatures: " + features.get(i).getNewsDate());
            }

            // Log.i("QueryUtils", "results is: " + results);

        } catch (JSONException e) {

            // If error thrown while executing above, catch exception here
            Log.e("QueryUtils", "Problem parsing the feature JSON results", e);

        }

        // Return a list of features
        return features;
    }

    /**
     * This function takes in the full date and only returns the "YYYY-MM-DD" format
     * @param incomingDate Unformatted date
     * @return formatted date in "YYYY-MM-DD"
     */
    private static String formatDate(String incomingDate) {
        String formattedDate = incomingDate.substring(0, 10);
        return formattedDate;
    }

    /**
     * Returns new URL object from the given string URL.
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the Guardian News JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    /**
     * Query the Guardian dataset and return a list of {@link Feature} objects.
     */
    public static List<Feature> fetchNewsFeatures(String requestUrl) {
        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        // Extract relevant fields from the JSON response and create a list of {@link Earthquake}s
        List<Feature> features = extractFeaturesFromJson(jsonResponse);

        // Return the list of {@link Earthquake}s
        return features;
    }

}
