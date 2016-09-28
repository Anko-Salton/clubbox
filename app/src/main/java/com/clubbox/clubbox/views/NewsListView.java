package com.clubbox.clubbox.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.clubbox.clubbox.model.News;

import java.util.List;


public class NewsListView extends ListView {
    public static final String TAG = "NewsListView";

    public NewsListView(Context context) {
        super(context);
        init();
    }

    public NewsListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NewsListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public NewsListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private NewsAdapter mAdapter;
    private OnNewsSelectedListener mListener;

    public void init() {
        mAdapter = new NewsAdapter();
        setAdapter(mAdapter);
        setOnItemClickListener(mOnNewsSelectedListener);

    }

    public void setupView(List<News> newses) {
        mAdapter.setNewsList(newses);
    }
    private class NewsAdapter extends BaseAdapter {
        List<News> mNews = new News.List();

        @Override
        public int getCount() {
            return mNews.size();
        }

        @Override
        public News getItem(int position) {
            return mNews.get(position);
        }

        @Override
        public long getItemId(int position) {
            return mNews.get(position).getId();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            NewsItemView view;
            if (convertView == null) {
                view = new NewsItemView(getContext());
            } else {
                view = (NewsItemView) convertView;
            }
            view.setupView(getItem(position));
            return view;
        }

        public void setNewsList(List<News> news) {
            mNews = news;
            notifyDataSetChanged();
        }
    }

    public interface OnNewsSelectedListener {
        void onNewsSelected(News news);
    }

    public void setOnNewsSelectedListener(OnNewsSelectedListener listener) {
        mListener = listener;
    }

    private AdapterView.OnItemClickListener mOnNewsSelectedListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            mListener.onNewsSelected(mAdapter.getItem(position));
        }
    };

}
