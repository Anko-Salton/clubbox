package com.clubbox.clubbox.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.clubbox.clubbox.R;
import com.clubbox.clubbox.model.Availability;

/**
 * Created by floriantainlot on 05/10/2016.
 */

public class AvailabilityItemView extends LinearLayout{

    public static final String TAG = "MatchItemView";

    public AvailabilityItemView(Context context) {
        super(context);
        init();
    }

    public AvailabilityItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AvailabilityItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AvailabilityItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private boolean isInitialize;
    private TextView dateMatch;
    private TextView placeMatch;
    private RadioGroup radioGroup;
    private RadioButton radioPresent;
    private RadioButton radioAbsent;

    public void init() {
        if (!isInitialize) {
            LayoutInflater.from(getContext()).inflate(R.layout.view_availibility_item, this, true);
            dateMatch = (TextView) findViewById(R.id.dateMatch);
            placeMatch = (TextView) findViewById(R.id.matchPlace);
            radioGroup = (RadioGroup) findViewById(R.id.radioGroupAvailable);
            radioAbsent = (RadioButton) findViewById(R.id.radioAbsent);
            radioPresent = (RadioButton) findViewById(R.id.radioPresent);
        }
        isInitialize = true;
    }

    public void setupView(Availability availability) {
        dateMatch.setText(availability.getIdMatch().getDatetime());
        placeMatch.setText(availability.getIdMatch().getPlace());
        radioAbsent.setChecked(!availability.getAvailability());
        radioPresent.setChecked(availability.getAvailability());
    }

}
