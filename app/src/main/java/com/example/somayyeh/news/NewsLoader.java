package com.example.somayyeh.news;

import android.content.AsyncTaskLoader;
import android.content.Context;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

/**
 * Created by somayyeh on 8/19/16.
 */
public class NewsLoader extends AsyncTaskLoader<List<News>>
{
    private String mUrl;

    public NewsLoader(Context context , String url) {
        super(context);
        mUrl = url;
    }

    @Override
    public List<News> loadInBackground() {
        try {
            return QueryUtils.fetchData(mUrl);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onStartLoading() {
forceLoad();
    }
}
