package com.example.newsapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * These methods are Helper methods that help request and receive Guardian news stories.
 */

public final class QueryUtils {

    /** Sample JSON response for a Guardian query */
    private static final String SAMPLE_JSON_RESPONSE = "{\"response\":{\"status\":\"ok\",\"userTier\":\"developer\",\"total\":2053235,\"startIndex\":1,\"pageSize\":10,\"currentPage\":1,\"pages\":205324,\"orderBy\":\"newest\",\"results\":[{\"id\":\"commentisfree/2018/aug/08/the-guardian-view-on-shahidul-alam-bangladesh-should-let-him-go\",\"type\":\"article\",\"sectionId\":\"commentisfree\",\"sectionName\":\"Opinion\",\"webPublicationDate\":\"2018-08-08T17:16:24Z\",\"webTitle\":\"The Guardian view on Shahidul Alam: Bangladesh should let him go | Editorial\",\"webUrl\":\"https://www.theguardian.com/commentisfree/2018/aug/08/the-guardian-view-on-shahidul-alam-bangladesh-should-let-him-go\",\"apiUrl\":\"https://content.guardianapis.com/commentisfree/2018/aug/08/the-guardian-view-on-shahidul-alam-bangladesh-should-let-him-go\",\"isHosted\":false,\"pillarId\":\"pillar/opinion\",\"pillarName\":\"Opinion\"},{\"id\":\"business/2018/aug/08/homebase-set-to-announce-closure-of-up-to-80-stores\",\"type\":\"article\",\"sectionId\":\"business\",\"sectionName\":\"Business\",\"webPublicationDate\":\"2018-08-08T17:10:29Z\",\"webTitle\":\"Homebase set to announce closure of up to 80 stores\",\"webUrl\":\"https://www.theguardian.com/business/2018/aug/08/homebase-set-to-announce-closure-of-up-to-80-stores\",\"apiUrl\":\"https://content.guardianapis.com/business/2018/aug/08/homebase-set-to-announce-closure-of-up-to-80-stores\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"world/2018/aug/08/what-muslim-women-ought-not-to-wear-isnt-a-matter-for-boris-johnson\",\"type\":\"article\",\"sectionId\":\"world\",\"sectionName\":\"World news\",\"webPublicationDate\":\"2018-08-08T17:08:13Z\",\"webTitle\":\"What Muslim women ought not to wear isn’t a matter for Boris Johnson | Letters\",\"webUrl\":\"https://www.theguardian.com/world/2018/aug/08/what-muslim-women-ought-not-to-wear-isnt-a-matter-for-boris-johnson\",\"apiUrl\":\"https://content.guardianapis.com/world/2018/aug/08/what-muslim-women-ought-not-to-wear-isnt-a-matter-for-boris-johnson\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"environment/2018/aug/08/with-the-world-on-fire-we-must-act-now-to-tackle-climate-change\",\"type\":\"article\",\"sectionId\":\"environment\",\"sectionName\":\"Environment\",\"webPublicationDate\":\"2018-08-08T17:08:02Z\",\"webTitle\":\"With the world on fire, we must act now to tackle climate change | Letters\",\"webUrl\":\"https://www.theguardian.com/environment/2018/aug/08/with-the-world-on-fire-we-must-act-now-to-tackle-climate-change\",\"apiUrl\":\"https://content.guardianapis.com/environment/2018/aug/08/with-the-world-on-fire-we-must-act-now-to-tackle-climate-change\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"world/2018/aug/08/psychotherapy-and-issues-of-sexuality\",\"type\":\"article\",\"sectionId\":\"world\",\"sectionName\":\"World news\",\"webPublicationDate\":\"2018-08-08T17:07:18Z\",\"webTitle\":\"Psychotherapy and issues of sexuality | Letter\",\"webUrl\":\"https://www.theguardian.com/world/2018/aug/08/psychotherapy-and-issues-of-sexuality\",\"apiUrl\":\"https://content.guardianapis.com/world/2018/aug/08/psychotherapy-and-issues-of-sexuality\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"business/2018/aug/08/giant-shipload-of-soybeans-drifts-off-china-victim-of-trade-war-with-us\",\"type\":\"article\",\"sectionId\":\"business\",\"sectionName\":\"Business\",\"webPublicationDate\":\"2018-08-08T17:03:39Z\",\"webTitle\":\"Giant shipload of soybeans drifts off China, victim of trade war with US\",\"webUrl\":\"https://www.theguardian.com/business/2018/aug/08/giant-shipload-of-soybeans-drifts-off-china-victim-of-trade-war-with-us\",\"apiUrl\":\"https://content.guardianapis.com/business/2018/aug/08/giant-shipload-of-soybeans-drifts-off-china-victim-of-trade-war-with-us\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"environment/2018/aug/08/reflecting-suns-rays-would-cause-crops-to-fail-scientists-warn\",\"type\":\"article\",\"sectionId\":\"environment\",\"sectionName\":\"Environment\",\"webPublicationDate\":\"2018-08-08T17:00:02Z\",\"webTitle\":\"Reflecting sun's rays would cause crops to fail, scientists warn\",\"webUrl\":\"https://www.theguardian.com/environment/2018/aug/08/reflecting-suns-rays-would-cause-crops-to-fail-scientists-warn\",\"apiUrl\":\"https://content.guardianapis.com/environment/2018/aug/08/reflecting-suns-rays-would-cause-crops-to-fail-scientists-warn\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"football/2018/aug/08/mauricio-pochettino-criticise-shorter-transfer-window-tottenham\",\"type\":\"article\",\"sectionId\":\"football\",\"sectionName\":\"Football\",\"webPublicationDate\":\"2018-08-08T16:59:01Z\",\"webTitle\":\"Why Mauricio Pochettino is wrong to criticise shorter transfer window | Paul MacInnes\",\"webUrl\":\"https://www.theguardian.com/football/2018/aug/08/mauricio-pochettino-criticise-shorter-transfer-window-tottenham\",\"apiUrl\":\"https://content.guardianapis.com/football/2018/aug/08/mauricio-pochettino-criticise-shorter-transfer-window-tottenham\",\"isHosted\":false,\"pillarId\":\"pillar/sport\",\"pillarName\":\"Sport\"},{\"id\":\"sport/blog/2018/aug/08/counties-need-to-revolt-the-hundred-ecb\",\"type\":\"article\",\"sectionId\":\"sport\",\"sectionName\":\"Sport\",\"webPublicationDate\":\"2018-08-08T16:57:05Z\",\"webTitle\":\"Counties should revolt against The Hundred and reverse the ECB coup | Matthew Engel\",\"webUrl\":\"https://www.theguardian.com/sport/blog/2018/aug/08/counties-need-to-revolt-the-hundred-ecb\",\"apiUrl\":\"https://content.guardianapis.com/sport/blog/2018/aug/08/counties-need-to-revolt-the-hundred-ecb\",\"isHosted\":false,\"pillarId\":\"pillar/sport\",\"pillarName\":\"Sport\"},{\"id\":\"commentisfree/2018/aug/08/austerity-kills-life-expectancy-standstill-britain\",\"type\":\"article\",\"sectionId\":\"commentisfree\",\"sectionName\":\"Opinion\",\"webPublicationDate\":\"2018-08-08T16:52:39Z\",\"webTitle\":\"Austerity kills: this week’s figures show its devastating toll | Owen Jones\",\"webUrl\":\"https://www.theguardian.com/commentisfree/2018/aug/08/austerity-kills-life-expectancy-standstill-britain\",\"apiUrl\":\"https://content.guardianapis.com/commentisfree/2018/aug/08/austerity-kills-life-expectancy-standstill-britain\",\"isHosted\":false,\"pillarId\":\"pillar/opinion\",\"pillarName\":\"Opinion\"}]}}";

    /**
     * This QueryUtils class is only meant to hold static variables and methods that can be accessed directly from the class name. A QueryUtils object should NOT be created.
     */
    private QueryUtils() {}

    public static ArrayList<Feature> extractFeatures() {

        // Empty Features ArrayList to add features to
        ArrayList<Feature> features = new ArrayList<>();

        // Parse the JSON response
        // If there is an error with JSON format, exception thrown
        // Catch exception, print error to logs
        try {

            // Parse the JSON response and build a list of Feature objects
            JSONObject root = new JSONObject(SAMPLE_JSON_RESPONSE);
            JSONObject response = root.getJSONObject("response");
            JSONArray results = response.getJSONArray("results");

            JSONObject currentResultObj;
            String currentDateString;
            String currentTitleString;
            String currentCategoryString;

            for (int i = 0; i < results.length(); i++) {
                currentResultObj = results.getJSONObject(i);
                currentDateString = currentResultObj.getString("webPublicationDate");
                currentTitleString = currentResultObj.getString("webTitle");
                currentCategoryString = currentResultObj.getString("sectionName");
                features.add(new Feature(
                        currentCategoryString,
                        currentTitleString,
                        formatDate(currentDateString),
                        1));
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

}
