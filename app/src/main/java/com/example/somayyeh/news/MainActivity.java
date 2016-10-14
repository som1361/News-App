package com.example.somayyeh.news;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {
    private NewsAdapter adapter;
    private final String NEWS_URL = "https://newsapi.org/v1/articles?source=bbcsport&apiKey=3e22f2fcc1344975ae2b2e69379e2a6e";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter = new NewsAdapter(this , new ArrayList<News>());
        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setEmptyView(findViewById(R.id.emptyList));

        ConnectivityManager myCon = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = myCon.getActiveNetworkInfo();
        if(netInfo != null && netInfo.isConnected()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(0, null, this);
        }
        else
        {
            ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
            progressBar.setVisibility(View.GONE);
            TextView emptyText = (TextView)findViewById(R.id.emptyList);
            emptyText.setText(R.string.no_network_data);

        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                News currentNews = (News) adapter.getItem(position);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(currentNews.getUrl()));
                startActivity(intent);
            }
        });
    }

    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args)
    {
        return new NewsLoader(this,NEWS_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> data) {
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        adapter.clear();

        if (data != null) {
            adapter.addAll(data);
        }
            TextView emptyText = (TextView) findViewById(R.id.emptyList);
            emptyText.setText(R.string.emptyData);

    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader)
    {
      adapter.clear();
    }
}

