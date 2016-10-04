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
import com.clubbox.clubbox.network.ChannelREST;
import com.clubbox.clubbox.propertie.Properties;
import com.clubbox.clubbox.views.ChannelListView;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class ListChannelActivity extends AppCompatActivity {

    private static final String TAG = "ListChannelActivity";

    //Eléments du menu
    private ImageButton menuButton;
    private Button navListMatch;
    private Button closeMenu;
    private Button navHome;
    private Button navMessages;
    private Button navProfil;
    private Button navScorers;
    private LinearLayout theMenu;

    private ChannelListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_channel);

        //Eléments du menu
        menuButton = (ImageButton) findViewById(R.id.navButton);
        navListMatch = (Button) findViewById(R.id.nav_result);
        navScorers = (Button) findViewById(R.id.nav_rank);
        closeMenu = (Button) findViewById(R.id.nav_close);
        navHome = (Button) findViewById(R.id.nav_accueil);
        navMessages = (Button) findViewById(R.id.nav_messages);
        navProfil = (Button) findViewById(R.id.nav_profil);
        theMenu = (LinearLayout) findViewById(R.id.menuLeft);

        mListView = (ChannelListView) findViewById(R.id.listViewChannel);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                ChannelREST channelREST = new Retrofit.Builder()
                        .baseUrl(ChannelREST.ENDPOINT)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build().create(ChannelREST.class);
                try{
                    final List<Channel> channels = channelREST.getAllChannelFromClub(Properties.getInstance().getConnectedUser().getClub().getId().intValue()).execute().body();
                    if (channels != null && channels.size() > 0) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (channels.size() > 0) {
                                    mListView.setupView(channels);
                                }
                            }
                        });
                    }
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        });
        thread.start();


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

        navScorers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ListChannelActivity.this, ListScorerActivity.class);
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
