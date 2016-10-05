package com.clubbox.clubbox.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.clubbox.clubbox.model.Availability;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by floriantainlot on 05/10/2016.
 */

public class AvailabilityListView extends ListView {

    public static final String TAG = "AvailabilityListView";

    public AvailabilityListView(Context context) {
        super(context);
        init();
    }

    public AvailabilityListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AvailabilityListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AvailabilityListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private AvailabilityListView.AvailabilityAdapter mAdapter;

    public void init() {
        mAdapter = new AvailabilityListView.AvailabilityAdapter();
        setAdapter(mAdapter);
    }

    public void setupView(List<Availability> availabilities) {
        mAdapter.setAvailabilityList(availabilities);
    }
    private class AvailabilityAdapter extends BaseAdapter {
        List<Availability> mAvailabilities = new ArrayList<Availability>();

        @Override
        public int getCount() {
            return mAvailabilities.size();
        }

        @Override
        public Availability getItem(int position) {
            return mAvailabilities.get(position);
        }

        @Override
        public long getItemId(int position) {
            return mAvailabilities.get(position).getId();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            AvailabilityItemView view;
            if (convertView == null) {
                view = new AvailabilityItemView(getContext());
            } else {
                view = (AvailabilityItemView) convertView;
            }
            view.setupView(getItem(position));
            return view;
        }

        public void setAvailabilityList(List<Availability> availabilities) {
            mAvailabilities = availabilities;
            notifyDataSetChanged();
        }
    }

}
