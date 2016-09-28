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

import com.clubbox.clubbox.model.Match;

import java.util.List;

public class MatchListView extends ListView {
    public static final String TAG = "MatchListView";

    public MatchListView(Context context) {
        super(context);
        init();
    }

    public MatchListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MatchListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MatchListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private MatchAdapter mAdapter;
    private OnMatchSelectedListener mListener;

    public void init() {
        mAdapter = new MatchAdapter();
        setAdapter(mAdapter);
        setOnItemClickListener(mOnMatchSelectedListener);
    }

    public void setupView(List<Match> matchs) {
        mAdapter.setMatchList(matchs);
    }
    private class MatchAdapter extends BaseAdapter {
        List<Match> mMatchs = new Match.List();

        @Override
        public int getCount() {
            return mMatchs.size();
        }

        @Override
        public Match getItem(int position) {
            return mMatchs.get(position);
        }

        @Override
        public long getItemId(int position) {
            return mMatchs.get(position).getId();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            MatchItemView view;
            if (convertView == null) {
                view = new MatchItemView(getContext());
            } else {
                view = (MatchItemView) convertView;
            }
            view.setupView(getItem(position));
            return view;
        }

        public void setMatchList(List<Match> matchs) {
            mMatchs = matchs;
            notifyDataSetChanged();
        }
    }

    public interface OnMatchSelectedListener {
        void onMatchSelected(Match match);
    }

    public void setOnMatchSelectedListener(OnMatchSelectedListener listener) {
        mListener = listener;
    }

    private AdapterView.OnItemClickListener mOnMatchSelectedListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            mListener.onMatchSelected(mAdapter.getItem(position));
        }
    };

}
