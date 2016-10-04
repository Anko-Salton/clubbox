package com.clubbox.clubbox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.clubbox.clubbox.model.Match;
import com.clubbox.clubbox.model.News;
import com.clubbox.clubbox.model.User;
import com.clubbox.clubbox.network.MatchREST;
import com.clubbox.clubbox.network.NewsREST;
import com.clubbox.clubbox.propertie.Properties;
import com.clubbox.clubbox.views.NewsListView;

import java.util.Date;
import java.util.List;
import java.util.concurrent.RunnableFuture;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";

    //Eléments du menu
    private ImageButton menuButton;
    private Button navListNews;
    private Button navListMatch;
    private Button closeMenu;
    private Button navHome;
    private Button navMessages;
    private Button navProfil;
    private LinearLayout theMenu;
    private Match lastMatch;
    private Match nextMatch;

    private NewsListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent intent = getIntent();

        //TODO: gérer le LIVE

        //Eléments du menu
        menuButton = (ImageButton) findViewById(R.id.navButton);
        navListNews = (Button) findViewById(R.id.nav_event);
        navListMatch = (Button) findViewById(R.id.nav_result);
        closeMenu = (Button) findViewById(R.id.nav_close);
        navHome = (Button) findViewById(R.id.nav_accueil);
        navMessages = (Button) findViewById(R.id.nav_messages);
        navProfil = (Button) findViewById(R.id.nav_profil);
        theMenu = (LinearLayout) findViewById(R.id.menuLeft);

        mListView = (NewsListView) findViewById(R.id.listViewNews);

        Runnable run = new Runnable() {
            @Override
            public void run() {
                try {
                    NewsREST newsREST = new Retrofit.Builder()
                            .baseUrl(NewsREST.ENDPOINT)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                            .create(NewsREST.class);
                    User connectedUser = Properties.getInstance().getConnectedUser();
                    final List<News> list = newsREST.allNewsFromClub(connectedUser.getClub().getId().intValue()).execute().body();
                    if (list.size() > 0) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mListView.setupView(list);
                            }
                        });
                    }
                    Log.e("liste : ", list.size() + "");

                    MatchREST matchREST = new Retrofit.Builder()
                            .baseUrl(MatchREST.ENDPOINT)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                            .create(MatchREST.class);

                    lastMatch = matchREST.getLastMatchByClubId(connectedUser.getClub().getId().intValue()).execute().body();
                    nextMatch = matchREST.getNextMatchByClubId(connectedUser.getClub().getId().intValue()).execute().body();

                    TextView teamHomeLast = (TextView) findViewById(R.id.equipe_a_last);
                    TextView teamAwayLast = (TextView) findViewById(R.id.equipe_b_last);
                    TextView scoreHomeLast = (TextView) findViewById(R.id.score_a_last);
                    TextView scoreAwayLast = (TextView) findViewById(R.id.score_b_last);
                    TextView teamHome = (TextView) findViewById(R.id.equipe_a_next);
                    TextView teamAway = (TextView) findViewById(R.id.equipe_b_next);
                    TextView scoreHome = (TextView) findViewById(R.id.score_a_next);
                    TextView scoreAway = (TextView) findViewById(R.id.score_b_next);

                    if (lastMatch instanceof Match && lastMatch.getId() != 0) {
                        Log.e("lastMatchIF : ", "ok");

                        teamHomeLast.setText(lastMatch.getTeamHome().getName());
                        teamAwayLast.setText(lastMatch.getTeamAway().getName());
                        scoreHomeLast.setText(String.valueOf(lastMatch.getScoreHome()));
                        scoreAwayLast.setText(String.valueOf(lastMatch.getScoreAway()));
                    }

                    /*if (nextMatch instanceof Match && nextMatch.getId() != 0) {
                        Log.e("nextMatchIf : ", "ok");

                        teamHome.setText(nextMatch.getTeamHome().getName());
                        teamAway.setText(nextMatch.getTeamAway().getName());
                        scoreHome.setText(String.valueOf(nextMatch.getScoreHome()));
                        scoreAway.setText(String.valueOf(nextMatch.getScoreAway()));
                    }*/
                } catch (Exception e) {
                    Log.e("PERSONNAL ERROR LOG", "Erreur : " + e.toString());
                }
            }
        };
        Thread thread = new Thread(run);
        thread.start();

        //Sur le clic d'une news, on affiche la fiche de la news
        mListView.setOnNewsSelectedListener(new NewsListView.OnNewsSelectedListener() {
            @Override
            public void onNewsSelected(News news) {
                Intent i = new Intent(MainActivity.this, NewsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("newsId", news.getId());
                bundle.putString("newsTitle", news.getTitle());
                bundle.putString("newsDate", news.getDateFormatFR());
                bundle.putString("newsDesc", news.getContent());
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
                LinearLayout theMenu = (LinearLayout) findViewById(R.id.menuLeft);
                if (theMenu != null) {
                    theMenu.setVisibility(View.INVISIBLE);
                }
            }
        });

        navHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                theMenu.setVisibility(View.INVISIBLE);
            }
        });

        navListNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ListNewsActivity.class);
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
                Intent i = new Intent(MainActivity.this, ListMatchActivity.class);
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
                Intent i = new Intent(MainActivity.this, ListChannelActivity.class);
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
                Intent i = new Intent(MainActivity.this, ProfilActivity.class);
                startActivity(i);
                LinearLayout theMenu = (LinearLayout) findViewById(R.id.menuLeft);
                if (theMenu != null) {
                    theMenu.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
}