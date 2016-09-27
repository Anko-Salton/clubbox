package com.clubbox.clubbox.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.clubbox.clubbox.R;
import com.clubbox.clubbox.model.Match;

public class MatchItemView extends LinearLayout {
    public static final String TAG = "MatchItemView";

    public MatchItemView(Context context) {
        super(context);
        init();
    }

    public MatchItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MatchItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MatchItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private boolean isInitialize;
    private TextView DateMatch;
    private TextView teamH;
    private TextView score;
    private TextView teamA;

    public void init() {
        if (!isInitialize) {
            LayoutInflater.from(getContext()).inflate(R.layout.view_match_item, this, true);
            DateMatch = (TextView) findViewById(R.id.match_date);
            teamH = (TextView) findViewById(R.id.match_teamH);
            score = (TextView) findViewById(R.id.match_score);
            teamA = (TextView) findViewById(R.id.match_teamA);
        }
        isInitialize = true;
    }

    public void setupView(Match match) {
        DateMatch.setText(match.getDatetime());
        teamH.setText(match.getTeamHome().getName());
        score.setText(match.getScoreHome().toString() + " - " + match.getScoreAway().toString());
        teamA.setText(match.getTeamAway().getName());
    }
}
