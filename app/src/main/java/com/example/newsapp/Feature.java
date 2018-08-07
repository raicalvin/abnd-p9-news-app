package com.example.newsapp;

public class Feature {

    private String newsCategory;
    private String newsTitle;
    private String newsDate;
    private int newsImageResourceID;

    public Feature(String category, String title, String date, int imageID) {
        newsCategory = category;
        newsTitle = title;
        newsDate = date;
        newsImageResourceID = imageID;
    }

    public String getNewsCategory() {
        return newsCategory;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public String getNewsDate() {
        return newsDate;
    }

    public int getNewsImageResourceID() {
        return newsImageResourceID;
    }

}
