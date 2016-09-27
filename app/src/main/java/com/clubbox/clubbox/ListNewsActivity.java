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
import com.clubbox.clubbox.views.NewsListView;

import java.util.Date;

public class ListNewsActivity extends AppCompatActivity {

    //Eléments du menu
    private ImageButton menuButton;
    private Button navListNews;
    private Button navListMatch;
    private Button closeMenu;
    private Button navHome;
    private Button navMessages;
    private Button navProfil;
    private LinearLayout theMenu;

    private NewsListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_news);

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

        //On crée des données afin de tester l'affichage et la création d'objet
        //TODO: récupérer les données depuis la base de donnée.
        News.List list = new News.List();
        for (int i = 0; i < 25; i++) {
            News news = new News();
            news.setId(i);
            news.setTitle("Barbecue " + i);
            news.setContent("Nous organisons le barecue de fin de saison, vous êtes tous invités, on va se mettre une grosse mine ça va être trop bien ! Tim mangera toute les saucisses ce petit gourmand ! Venez nombreux pour que Tim soit satisfait");
            news.setDateadd(new Date());
            list.add(news);
        }

        if (list.size() > 0) {
            mListView.setupView(list);
        }
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

        navListNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                theMenu.setVisibility(View.INVISIBLE);
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
