package com.clubbox.clubbox;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.clubbox.clubbox.model.Match;
import com.clubbox.clubbox.model.News;
import com.clubbox.clubbox.model.Scorer;
import com.clubbox.clubbox.model.User;
import com.clubbox.clubbox.network.MatchREST;
import com.clubbox.clubbox.network.ScorerREST;
import com.clubbox.clubbox.propertie.Properties;
import com.clubbox.clubbox.views.NewsListView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class MatchActivity extends AppCompatActivity {
    public static final String TAG = "MatchActivity : ";

    //Eléments du menu
    private ImageButton menuButton;
    private Button navListMatch;
    private Button closeMenu;
    private Button navHome;
    private Button navMessages;
    private Button navProfil;
    private Button navScorers;
    private LinearLayout theMenu;

    //Eléments de la view
    private TextView matchDate;
    private TextView matchTeamH;
    private TextView matchTeamA;
    private TextView matchScore;
    private TextView matchResume;
    private RadioButton present;
    private RadioButton absent;
    private RadioButton aConfirmer;
    private ListView listScorers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        //Eléments du menu
        menuButton = (ImageButton) findViewById(R.id.navButton);
        navListMatch = (Button) findViewById(R.id.nav_result);
        closeMenu = (Button) findViewById(R.id.nav_close);
        navHome = (Button) findViewById(R.id.nav_accueil);
        navMessages = (Button) findViewById(R.id.nav_messages);
        navProfil = (Button) findViewById(R.id.nav_profil);
        navScorers = (Button) findViewById(R.id.nav_rank);
        theMenu = (LinearLayout) findViewById(R.id.menuLeft);

        matchDate= (TextView) findViewById(R.id.matchDate);
        matchTeamH= (TextView) findViewById(R.id.matchTeamD);
        matchTeamA= (TextView) findViewById(R.id.matchTeamE);
        matchScore= (TextView) findViewById(R.id.matchScore);
        matchResume= (TextView) findViewById(R.id.matchResume);
        listScorers = (ListView) findViewById(R.id.scorers);

        Intent i = getIntent();
        Bundle b = i.getExtras();

        //On récupère le match sur lequel l'utilisateur a cliqué précédement.
        final Match theMatch = (Match) b.getSerializable("theMatch");
        matchDate.setText(theMatch.getDatetime());
        matchTeamH.setText(theMatch.getTeamHome().getName());
        matchTeamA.setText(theMatch.getTeamAway().getName());
        matchScore.setText(theMatch.getScoreHome() + " - " + theMatch.getScoreAway());
        matchResume.setText(theMatch.getResumeHome());

        Runnable run = new Runnable() {
            @Override
            public void run() {
                final ScorerREST scorerREST = new Retrofit.Builder()
                        .baseUrl(ScorerREST.ENDPOINT)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                        .create(ScorerREST.class);
                User connectedUser = Properties.getInstance().getConnectedUser();
                try {
                    final List<Scorer> list = scorerREST.listScorerByMatch(theMatch.getId().intValue()).execute().body();
                    if (list.size() > 0) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                List<String> scorers = new ArrayList<String>();
                                for (int i=0;i<list.size();i++) {
                                    scorers.add(list.get(i).getIdUser().getName());
                                }
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MatchActivity.this, android.R.layout.simple_list_item_1, scorers);
                                listScorers.setAdapter(adapter);
                            }
                        });
                    }
                } catch (Exception e) {
                    Log.e("PERSONNAL ERROR LOG", "Erreur : " + e.getMessage());
                }
            }
        };
        Thread thread = new Thread(run);
        thread.start();

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
                LinearLayout theMenu = (LinearLayout) findViewById(R.id.menuLeft);
                if (theMenu != null) {
                    theMenu.setVisibility(View.INVISIBLE);
                }
            }
        });

        navHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MatchActivity.this, MainActivity.class);
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
                Intent i = new Intent(MatchActivity.this, ListScorerActivity.class);
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
                Intent i = new Intent(MatchActivity.this, ListMatchActivity.class);
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
                Intent i = new Intent(MatchActivity.this, ListChannelActivity.class);
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
                Intent i = new Intent(MatchActivity.this, ProfilActivity.class);
                startActivity(i);
                LinearLayout theMenu = (LinearLayout) findViewById(R.id.menuLeft);
                if (theMenu != null) {
                    theMenu.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
}