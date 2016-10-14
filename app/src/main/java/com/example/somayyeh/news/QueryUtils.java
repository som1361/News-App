package com.example.somayyeh.news;

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
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by somayyeh on 8/19/16.
 */
public class QueryUtils
{
public static List<News> fetchData(String mUrl) throws IOException, JSONException {
    URL url = new URL(mUrl);
    String jsonResponse = makeHttpRequest(url);
    List<News> output = parseJsonResponse(jsonResponse);
    return output;
}
    public static String makeHttpRequest(URL url) throws IOException
    {
        StringBuilder result = new StringBuilder();

        HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
        InputStream inputStream = urlConnection.getInputStream();
        InputStreamReader streamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
        BufferedReader bufferedReader = new BufferedReader(streamReader);
        String line = bufferedReader.readLine();
        while(line != null)
        {
            result.append(line);
            line = bufferedReader.readLine();
        }
      return result.toString();

    }
    public static List<News> parseJsonResponse(String jsonResponse) throws JSONException {
        List<News> news = new ArrayList<News>();

        JSONObject root = new JSONObject(jsonResponse);
        JSONArray articles = root.optJSONArray("articles");

        if (articles != null) {
            for (int index = 0; index < articles.length();index++ )
            {
                JSONObject articleItem  = articles.getJSONObject(index);
                String title = articleItem.getString("title");
                String url = articleItem.getString("url");
                String pubDate = articleItem.getString("publishedAt");
                News newsItem = new News(title, url,pubDate);
                news.add(newsItem);
            }
        }
        return news;
    }
}
