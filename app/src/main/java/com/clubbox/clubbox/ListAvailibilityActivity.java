package com.clubbox.clubbox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.clubbox.clubbox.model.Availability;
import com.clubbox.clubbox.model.Match;
import com.clubbox.clubbox.model.User;
import com.clubbox.clubbox.network.MatchREST;
import com.clubbox.clubbox.propertie.Properties;
import com.clubbox.clubbox.views.AvailabilityListView;
import com.clubbox.clubbox.views.MatchListView;

import java.util.ArrayList;
import java.util.List;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class ListAvailibilityActivity extends AppCompatActivity {

    private static final String TAG = "ListAvailibilityActivity";

    //Eléments du menu
    private ImageButton menuButton;
    private Button navListMatch;
    private Button closeMenu;
    private Button navHome;
    private Button navMessages;
    private Button navProfil;
    private Button navScorers;
    private LinearLayout theMenu;

    private AvailabilityListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_availibility);

        //Eléments du menu
        menuButton = (ImageButton) findViewById(R.id.navButton);
        navListMatch = (Button) findViewById(R.id.nav_result);
        closeMenu = (Button) findViewById(R.id.nav_close);
        navHome = (Button) findViewById(R.id.nav_accueil);
        navMessages = (Button) findViewById(R.id.nav_messages);
        navProfil = (Button) findViewById(R.id.nav_profil);
        navScorers = (Button) findViewById(R.id.nav_rank);
        theMenu = (LinearLayout) findViewById(R.id.menuLeft);

        final User connectedUser = Properties.getInstance().getConnectedUser();
        mListView = (AvailabilityListView) findViewById(R.id.convocations);
        final ArrayList<Match> matchList = new ArrayList<Match>();

        Runnable run = new Runnable() {
            @Override
            public void run() {
                MatchREST matchREST = new Retrofit.Builder()
                        .baseUrl(MatchREST.ENDPOINT)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                        .create(MatchREST.class);
                User connectedUser = Properties.getInstance().getConnectedUser();
                try {
                    final List<Match> listMatch = matchREST.getAllMatchByClubId(connectedUser.getClub().getId().intValue()).execute().body();
                    for (int j=0;j<listMatch.size();j++) {
                        matchList.add(listMatch.get(j));
                    }
                    Log.e("liste : ", listMatch.toString());
                } catch (Exception e) {
                    Log.e("PERSONNAL ERROR LOG", "Erreur : " + e.getMessage());
                }
            }
        };
        Thread thread = new Thread(run);
        thread.start();

        for (int i=0; i<10; i++) {
            Match m = matchList.get(i);
            Availability a = new Availability(connectedUser, m, true);
        }

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout theMenu = (LinearLayout) findViewById(R.id.menuLeft);
                if (theMenu != null) {
                    theMenu.setVisibility(View.VISIBLE);
                }
            }
        });

        closeMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                theMenu.setVisibility(View.INVISIBLE);
            }
        });

        navHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ListAvailibilityActivity.this, MainActivity.class);
                startActivity(i);
                LinearLayout theMenu = (LinearLayout) findViewById(R.id.menuLeft);
                if (theMenu != null) {
                    theMenu.setVisibility(View.INVISIBLE);
                }
            }
        });

        navScorers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ListAvailibilityActivity.this, ListScorerActivity.class);
                startActivity(i);
                LinearLayout theMenu = (LinearLayout) findViewById(R.id.menuLeft);
                if (theMenu != null) {
                    theMenu.setVisibility(View.INVISIBLE);
                }
            }
        });

        navListMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ListAvailibilityActivity.this, ListMatchActivity.class);
                startActivity(i);
                LinearLayout theMenu = (LinearLayout) findViewById(R.id.menuLeft);
                if (theMenu != null) {
                    theMenu.setVisibility(View.INVISIBLE);
                }
            }
        });

        navMessages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ListAvailibilityActivity.this, ListChannelActivity.class);
                startActivity(i);
                LinearLayout theMenu = (LinearLayout) findViewById(R.id.menuLeft);
                if (theMenu != null) {
                    theMenu.setVisibility(View.INVISIBLE);
                }
            }
        });

        navProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ListAvailibilityActivity.this, ProfilActivity.class);
                startActivity(i);
                LinearLayout theMenu = (LinearLayout) findViewById(R.id.menuLeft);
                if (theMenu != null) {
                    theMenu.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
}
