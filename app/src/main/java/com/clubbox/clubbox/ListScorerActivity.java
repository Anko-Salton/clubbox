package com.clubbox.clubbox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.clubbox.clubbox.model.Scorer;
import com.clubbox.clubbox.model.User;
import com.clubbox.clubbox.network.ScorerREST;
import com.clubbox.clubbox.propertie.Properties;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class ListScorerActivity extends AppCompatActivity {

    public static final String TAG = "NewsActivity : ";

    //Eléments du menu
    private ImageButton menuButton;
    private Button navListMatch;
    private Button closeMenu;
    private Button navHome;
    private Button navMessages;
    private Button navScorers;
    private Button navProfil;
    private LinearLayout theMenu;

    private ListView listViewScorers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_scorer);

        //Eléments du menu
        menuButton = (ImageButton) findViewById(R.id.navButton);
        navListMatch = (Button) findViewById(R.id.nav_result);
        closeMenu = (Button) findViewById(R.id.nav_close);
        navHome = (Button) findViewById(R.id.nav_accueil);
        navMessages = (Button) findViewById(R.id.nav_messages);
        navProfil = (Button) findViewById(R.id.nav_profil);
        theMenu = (LinearLayout) findViewById(R.id.menuLeft);
        navScorers = (Button) findViewById(R.id.nav_rank);

        listViewScorers = (ListView) findViewById(R.id.scorers);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                ScorerREST scorerREST = new Retrofit.Builder()
                        .baseUrl(ScorerREST.ENDPOINT)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                        .create(ScorerREST.class);
                ArrayList<String> scorersStr = new ArrayList<String>();

                try {
                    ArrayList<Map<Integer, User>> scorers = scorerREST.listScorerByClub(Properties.getInstance().getConnectedUser().getClub().getId().intValue()).execute().body();
                    Collections.sort(scorers, new Comparator<Map<Integer, User>>() {
                        @Override
                        public int compare(Map<Integer, User> lhs, Map<Integer, User> rhs) {
                            return lhs.keySet().toArray()[0].toString().compareTo(rhs.keySet().toArray()[0].toString());
                        }
                    });
                    for (Map<Integer, User> a : scorers) {
                        scorersStr.add(a.keySet().toArray()[0].toString() + " // " + a.get(a.keySet().toArray()[0]).getName());
                    }
                    final ArrayAdapter<String> adapter = new ArrayAdapter<String>(ListScorerActivity.this, android.R.layout.simple_list_item_1, scorersStr);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listViewScorers.setAdapter(adapter);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });
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
                Intent i = new Intent(ListScorerActivity.this, MainActivity.class);
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
                Intent i = new Intent(ListScorerActivity.this, ListScorerActivity.class);
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
                Intent i = new Intent(ListScorerActivity.this, ListMatchActivity.class);
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
                Intent i = new Intent(ListScorerActivity.this, ListChannelActivity.class);
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
                Intent i = new Intent(ListScorerActivity.this, ProfilActivity.class);
                startActivity(i);
                LinearLayout theMenu = (LinearLayout) findViewById(R.id.menuLeft);
                if (theMenu != null) {
                    theMenu.setVisibility(View.INVISIBLE);
                }
            }
        });

    }
}