package com.clubbox.clubbox.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.clubbox.clubbox.R;
import com.clubbox.clubbox.model.News;

public class NewsItemView extends LinearLayout {
    public static final String TAG = "NewsItemView";

    public NewsItemView(Context context) {
        super(context);
        init();
    }

    public NewsItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NewsItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public NewsItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private boolean isInitialize;
    private TextView mTitleTextView;
    private TextView mDescTextView;
    private TextView mDateTextView;

    public void init() {
        if (!isInitialize) {
            LayoutInflater.from(getContext()).inflate(R.layout.view_news_item, this, true);
            mTitleTextView = (TextView) findViewById(R.id.text_title);
            mDescTextView = (TextView) findViewById(R.id.text_desc);
            mDateTextView = (TextView) findViewById(R.id.text_date);
        }
        isInitialize = true;
    }

    public void setupView(News news) {
        mTitleTextView.setText(news.getTitle());
        mDescTextView.setText(news.getContent());
        mDateTextView.setText(news.getDateFormatFR());
    }
}
