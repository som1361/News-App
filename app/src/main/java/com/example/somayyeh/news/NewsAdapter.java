package com.example.somayyeh.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by somayyeh on 8/19/16.
 */
public class NewsAdapter extends ArrayAdapter {
    public NewsAdapter(Context context, List<News> newsList)
    {
        super(context, 0, newsList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View listItemView = convertView;
        if (listItemView == null)
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_list, parent, false);
        News newsItem = (News)getItem(position);

        TextView title = (TextView)listItemView.findViewById(R.id.title);
        title.setText(newsItem.getTitle());
        TextView pubDate = (TextView)listItemView.findViewById(R.id.pubDate);
        pubDate.setText(newsItem.getmPublishedDate());
        return listItemView;
    }
}
