package com.example.somayyeh.news;

/**
 * Created by somayyeh on 8/19/16.
 */
public class News {
    private String mTitle;
    private String mUrl;
    private String mPublishedDate;
    public News(String title , String url , String publishDate)
    {
        mTitle = title;
        mUrl = url;
        mPublishedDate = publishDate;

    }
    public String getTitle()
    {
        return mTitle;
    }
    public String getUrl()
    {
        return mUrl;
    }
    public String getmPublishedDate()
    {
        return mPublishedDate;
    }
}
