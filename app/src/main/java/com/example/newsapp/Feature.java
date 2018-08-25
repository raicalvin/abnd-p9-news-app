package com.example.newsapp;

public class Feature {

    /** Category of the feature item */
    private String newsCategory;

    /** Main title of the feature item */
    private String newsTitle;

    /** Date of the feature item posting */
    private String newsDate;

    /** Resource ID for the feature item thumbnail */
    private int newsImageResourceID;

    /** String URL for the news article website article */
    private String newsWebsiteUrl;

    /**
     * Constructs a new {@link Feature} object.
     *
     * @param category is the category of the single news item
     * @param title is the main heading for the news item
     * @param date is the date of publication for the news item
     * @param imageID is the image ID for the news item thumbnail
     */
    public Feature(String category, String title, String date, int imageID, String webURL) {
        newsCategory = category;
        newsTitle = title;
        newsDate = date;
        newsImageResourceID = imageID;
        newsWebsiteUrl = webURL;
    }

    /**
     * Returns the news category of the feature item
     */
    public String getNewsCategory() {
        return newsCategory;
    }

    /**
     * Returns the news title heading of the feature item
     */
    public String getNewsTitle() {
        return newsTitle;
    }

    /**
     * Returns the publication date of the feature item
     */
    public String getNewsDate() {
        return newsDate;
    }

    /**
     * Returns the image ID for the feature thumbnail
     */
    public int getNewsImageResourceID() {
        return newsImageResourceID;
    }

    public String getUrl() {
        return newsWebsiteUrl;
    }

}
