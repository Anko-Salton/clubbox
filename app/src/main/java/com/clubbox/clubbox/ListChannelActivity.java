package com.clubbox.clubbox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.clubbox.clubbox.model.Channel;
import com.clubbox.clubbox.model.Club;
import com.clubbox.clubbox.model.Departement;
import com.clubbox.clubbox.model.Division;
import com.clubbox.clubbox.model.Message;
import com.clubbox.clubbox.model.Team;
import com.clubbox.clubbox.model.User;
import com.clubbox.clubbox.views.ChannelListView;

import java.util.Date;

public class ListChannelActivity extends AppCompatActivity {

    private static final String TAG = "ListChannelActivity";

    //Eléments du menu
    private ImageButton menuButton;
    private Button navListNews;
    private Button navListMatch;
    private Button closeMenu;
    private Button navHome;
    private Button navMessages;
    private Button navProfil;
    private LinearLayout theMenu;

    private ChannelListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_channel);

        //Eléments du menu
        menuButton = (ImageButton) findViewById(R.id.navButton);
        navListNews = (Button) findViewById(R.id.nav_event);
        navListMatch = (Button) findViewById(R.id.nav_result);
        closeMenu = (Button) findViewById(R.id.nav_close);
        navHome = (Button) findViewById(R.id.nav_accueil);
        navMessages = (Button) findViewById(R.id.nav_messages);
        navProfil = (Button) findViewById(R.id.nav_profil);
        theMenu = (LinearLayout) findViewById(R.id.menuLeft);

        mListView = (ChannelListView) findViewById(R.id.listViewChannel);

        //On crée des données afin de tester l'affichage et la création d'objet
        //TODO: récupérer les données depuis la base de donnée.
        Channel.List channels = new Channel.List();
        User.List users = new User.List();
        Message.List messages = new Message.List();
        Club c1 = new Club((long) 0);
        Departement dept1 = new Departement(68, "Haut-rhin");
        Division d1 = new Division(0, "Div 1", dept1);
        Team th = new Team(0, c1, "Colmar", d1);
        Team ta = new Team(0, c1, "Strasbourg", d1);

        for (int i = 0; i < 5; i++) {
            User user = new User(i, "email"+i+"@blabla.fr", "PassTest"+i, "Name"+i, "Forname"+i, "", "0606060606", "/images/profil", 0);
            users.add(user);
        }

        for (int i = 0; i < 5; i++) {
            Message message = new Message(i, new Date(), "Contenu du message"+i, users.get(i));
            message.setLeft(false);
            messages.add(message);
        }

        for (int i = 0; i < 25; i++) {
            Channel channel = new Channel(i, "Channel "+i, users, messages);
            channels.add(channel);
        }

        if (channels.size() > 0) {
            mListView.setupView(channels);
        }
        mListView.setOnChannelSelectedListener(new ChannelListView.OnChannelSelectedListener() {
            @Override
            public void onChannelSelected(Channel channel) {
                Intent i = new Intent(ListChannelActivity.this, ChannelActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("theChannel", channel);
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
                Intent i = new Intent(ListChannelActivity.this, MainActivity.class);
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
                Intent i = new Intent(ListChannelActivity.this, ListNewsActivity.class);
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
                Intent i = new Intent(ListChannelActivity.this, ListMatchActivity.class);
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
                theMenu.setVisibility(View.INVISIBLE);
            }
        });

        navProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ListChannelActivity.this, ProfilActivity.class);
                startActivity(i);
                LinearLayout theMenu = (LinearLayout) findViewById(R.id.menuLeft);
                if (theMenu != null) {
                    theMenu.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
}
