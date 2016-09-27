package com.clubbox.clubbox.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.clubbox.clubbox.R;
import com.clubbox.clubbox.model.Channel;
import com.clubbox.clubbox.model.Message;

import java.util.ArrayList;

public class ChannelItemView extends LinearLayout {

    public static final String TAG = "MatchItemView";

    public ChannelItemView(Context context) {
        super(context);
        init();
    }

    public ChannelItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ChannelItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ChannelItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private boolean isInitialize;
    private TextView nameChannel;
    private TextView lastMessChannel;

    public void init() {
        if (!isInitialize) {
            LayoutInflater.from(getContext()).inflate(R.layout.view_channel_item, this, true);
            nameChannel = (TextView) findViewById(R.id.channelName);
            lastMessChannel = (TextView) findViewById(R.id.channelLastMessage);
        }
        isInitialize = true;
    }

    public void setupView(Channel channel) {
        nameChannel.setText(channel.getName());
        ArrayList<Message> messages = channel.getMessages();
        int size = messages.size();
        lastMessChannel.setText(messages.get(size - 1).getContent());
    }

}
