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

import com.clubbox.clubbox.model.Channel;

public class ChannelListView extends ListView {

    public static final String TAG = "ChannelListView";

    public ChannelListView(Context context) {
        super(context);
        init();
    }

    public ChannelListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ChannelListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ChannelListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private ChannelAdapter mAdapter;
    private OnChannelSelectedListener mListener;

    public void init() {
        mAdapter = new ChannelAdapter();
        setAdapter(mAdapter);
        setOnItemClickListener(mOnChannelSelectedListener);
    }

    public void setupView(Channel.List channels) {
        mAdapter.setChannelList(channels);
    }
    private class ChannelAdapter extends BaseAdapter {
        Channel.List mChannels = new Channel.List();

        @Override
        public int getCount() {
            return mChannels.size();
        }

        @Override
        public Channel getItem(int position) {
            return mChannels.get(position);
        }

        @Override
        public long getItemId(int position) {
            return mChannels.get(position).getId();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ChannelItemView view;
            if (convertView == null) {
                view = new ChannelItemView(getContext());
            } else {
                view = (ChannelItemView) convertView;
            }
            view.setupView(getItem(position));
            return view;
        }

        public void setChannelList(Channel.List channels) {
            mChannels = channels;
            notifyDataSetChanged();
        }
    }

    public interface OnChannelSelectedListener {
        void onChannelSelected(Channel channel);
    }

    public void setOnChannelSelectedListener(OnChannelSelectedListener listener) {
        mListener = listener;
    }

    private AdapterView.OnItemClickListener mOnChannelSelectedListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            mListener.onChannelSelected(mAdapter.getItem(position));
        }
    };

}