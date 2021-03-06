package com.clubbox.clubbox;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.clubbox.clubbox.model.Club;
import com.clubbox.clubbox.model.Departement;
import com.clubbox.clubbox.model.Division;
import com.clubbox.clubbox.model.Match;
import com.clubbox.clubbox.model.Team;
import com.clubbox.clubbox.model.User;
import com.clubbox.clubbox.network.MatchREST;
import com.clubbox.clubbox.propertie.Properties;
import com.clubbox.clubbox.views.MatchListView;

import java.util.Date;
import java.util.List;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class ListMatchActivity extends AppCompatActivity {

    private static final String TAG = "ListMatchActivity";

    //Eléments du menu
    private ImageButton menuButton;
    private Button navListMatch;
    private Button closeMenu;
    private Button navHome;
    private Button navMessages;
    private Button navProfil;
    private Button navScorers;
    private LinearLayout theMenu;

    private MatchListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_match);

        //Eléments du menu
        menuButton = (ImageButton) findViewById(R.id.navButton);
        navListMatch = (Button) findViewById(R.id.nav_result);
        closeMenu = (Button) findViewById(R.id.nav_close);
        navHome = (Button) findViewById(R.id.nav_accueil);
        navMessages = (Button) findViewById(R.id.nav_messages);
        navProfil = (Button) findViewById(R.id.nav_profil);
        navScorers = (Button) findViewById(R.id.nav_rank);
        theMenu = (LinearLayout) findViewById(R.id.menuLeft);

        mListView = (MatchListView) findViewById(R.id.listViewMatch);

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
                    final List<Match> list = matchREST.getAllMatchByClubId(connectedUser.getClub().getId().intValue()).execute().body();
                    if (list.size() > 0) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mListView.setupView(list);
                            }
                        });

                    }
                    Log.e("liste : ", list.toString());
                } catch (Exception e) {
                    Log.e("PERSONNAL ERROR LOG", "Erreur : " + e.getMessage());
                }
            }
        };
        Thread thread = new Thread(run);
        thread.start();
        mListView.setOnMatchSelectedListener(new MatchListView.OnMatchSelectedListener() {
            @Override
            public void onMatchSelected(Match match) {
                Log.d(TAG, match.toString());
                Intent i = new Intent(ListMatchActivity.this, MatchActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("theMatch", match);
                i.putExtras(bundle);
                startActivity(i);
            }
        });

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
                Intent i = new Intent(ListMatchActivity.this, MainActivity.class);
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
                Intent i = new Intent(ListMatchActivity.this, ListScorerActivity.class);
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
                theMenu.setVisibility(View.INVISIBLE);
            }
        });

        navMessages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ListMatchActivity.this, ListChannelActivity.class);
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
                Intent i = new Intent(ListMatchActivity.this, ProfilActivity.class);
                startActivity(i);
                LinearLayout theMenu = (LinearLayout) findViewById(R.id.menuLeft);
                if (theMenu != null) {
                    theMenu.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
}
