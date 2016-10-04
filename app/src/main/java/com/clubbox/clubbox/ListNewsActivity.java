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

import com.clubbox.clubbox.model.News;
import com.clubbox.clubbox.model.User;
import com.clubbox.clubbox.network.NewsREST;
import com.clubbox.clubbox.propertie.Properties;
import com.clubbox.clubbox.views.NewsListView;

import java.util.Date;
import java.util.List;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class ListNewsActivity extends AppCompatActivity {

    //Eléments du menu
    private ImageButton menuButton;
    private Button navListMatch;
    private Button closeMenu;
    private Button navHome;
    private Button navMessages;
    private Button navProfil;
    private Button navScorers;
    private LinearLayout theMenu;

    private NewsListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_news);

        //Eléments du menu
        menuButton = (ImageButton) findViewById(R.id.navButton);
        navListMatch = (Button) findViewById(R.id.nav_result);
        closeMenu = (Button) findViewById(R.id.nav_close);
        navHome = (Button) findViewById(R.id.nav_accueil);
        navMessages = (Button) findViewById(R.id.nav_messages);
        navProfil = (Button) findViewById(R.id.nav_profil);
        navScorers = (Button) findViewById(R.id.nav_rank);
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
                    List<News> list = newsREST.allNewsFromClub(connectedUser.getClub().getId().intValue()).execute().body();
                    if (list.size() > 0) {
                        mListView.setupView(list);
                    }
                    Log.e("liste : ", list.size() + "");
                } catch (Exception e) {
                    Log.e("PERSONNAL ERROR LOG", "Erreur : " + e.toString());
                }
            }
        };
        Thread thread = new Thread(run);
        thread.start();

        mListView.setOnNewsSelectedListener(new NewsListView.OnNewsSelectedListener() {
            @Override
            public void onNewsSelected(News news) {
                Intent i = new Intent(ListNewsActivity.this, NewsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("newsId",news.getId());
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
                theMenu.setVisibility(View.INVISIBLE);
            }
        });

        navHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ListNewsActivity.this, MainActivity.class);
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
                Intent i = new Intent(ListNewsActivity.this, ListMatchActivity.class);
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
                Intent i = new Intent(ListNewsActivity.this, ListScorerActivity.class);
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
                Intent i = new Intent(ListNewsActivity.this, ListChannelActivity.class);
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
                Intent i = new Intent(ListNewsActivity.this, ProfilActivity.class);
                startActivity(i);
                LinearLayout theMenu = (LinearLayout) findViewById(R.id.menuLeft);
                if (theMenu != null) {
                    theMenu.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
}
