package com.clubbox.clubbox;

import android.content.Intent;
import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.clubbox.clubbox.model.Channel;
import com.clubbox.clubbox.model.Match;
import com.clubbox.clubbox.model.Message;
import com.clubbox.clubbox.model.ChatArrayAdapter;
import com.clubbox.clubbox.model.User;
import com.clubbox.clubbox.network.ChannelREST;
import com.clubbox.clubbox.network.MatchREST;
import com.clubbox.clubbox.propertie.Properties;

import java.util.ArrayList;
import java.util.List;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class ChannelActivity extends AppCompatActivity {
    public static final String TAG = "ChannelActivity : ";

    //Eléments du menu
    private ImageButton menuButton;
    private Button navListMatch;
    private Button closeMenu;
    private Button navHome;
    private Button navMessages;
    private Button navProfil;
    private Button navScorers;
    private LinearLayout theMenu;

    //Eléments de la vue
    private ChatArrayAdapter chatArrayAdapter;
    private ListView msgview;
    private EditText chatText;
    private Button buttonSend;
    private boolean side = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);

        //Eléments du menu
        menuButton = (ImageButton) findViewById(R.id.navButton);
        navListMatch = (Button) findViewById(R.id.nav_result);
        closeMenu = (Button) findViewById(R.id.nav_close);
        navHome = (Button) findViewById(R.id.nav_accueil);
        navMessages = (Button) findViewById(R.id.nav_messages);
        navProfil = (Button) findViewById(R.id.nav_profil);
        navScorers = (Button) findViewById(R.id.nav_rank);
        theMenu = (LinearLayout) findViewById(R.id.menuLeft);

        //Eléments de la vue
        msgview = (ListView) findViewById(R.id.msgview);
        chatText = (EditText) findViewById(R.id.editTextMessage);
        buttonSend = (Button) findViewById(R.id.btnSend);
        chatArrayAdapter = new ChatArrayAdapter(getApplicationContext(), R.layout.message_right);
        msgview.setAdapter(chatArrayAdapter);


        Intent i = getIntent();
        Bundle b = i.getExtras();

        //On récupère le channel sélectionné précedement
        final Channel theChannel = (Channel) b.getSerializable("theChannel");
        final ChannelREST channelREST = new Retrofit.Builder()
                .baseUrl(ChannelREST.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ChannelREST.class);
        Runnable run = new Runnable() {
            @Override
            public void run() {

                final User connectedUser = Properties.getInstance().getConnectedUser();
                try {
                    final List<Message> list = channelREST.getAllMessageFromChannel(connectedUser.getClub().getId().intValue(), theChannel.getId().intValue()).execute().body();
                    if (list.size() > 0) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                for (int i = 0; i<list.size(); i++) {
                                    Message msg = list.get(i);
                                    Log.d("connectedUser", connectedUser.getId().toString());
                                    Log.d("msgUser", msg.getUser().getId().toString());
                                    if (msg.getUser().getId() == connectedUser.getId()) {
                                        msg.setLeft(false);
                                    } else {
                                        msg.setLeft(true);
                                    }
                                    chatArrayAdapter.add(msg);
                                }
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


        //Ici on commence le traitement de la page
        chatText = (EditText) findViewById(R.id.editTextMessage);
        chatText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    //Si on appuie sur entrée, on envoie le message
                    try{
                        String ok = channelREST.postMessage(Properties.getInstance().getConnectedUser().getClub().getId().intValue()
                                ,theChannel.getId()
                                ,Properties.getInstance().getConnectedUser().getId()
                                ,chatText.getText().toString()).execute().body();

                        if(ok=="ok"){
                            Message msg = new Message();
                            msg.setContent(chatText.getText().toString());
                            msg.setLeft(true);
                            chatArrayAdapter.add(msg);
                            chatText.setText("");
                            side = !side;
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
                return false;
            }
        });

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                sendChatMessage(theChannel);
            }
        });

        msgview.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        msgview.setAdapter(chatArrayAdapter);

        //to scroll the list view to bottom on data change
        chatArrayAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                msgview.setSelection(chatArrayAdapter.getCount() - 1);
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
                Intent i = new Intent(ChannelActivity.this, MainActivity.class);
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
                Intent i = new Intent(ChannelActivity.this, ListMatchActivity.class);
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
                Intent i = new Intent(ChannelActivity.this, ListScorerActivity.class);
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
                Intent i = new Intent(ChannelActivity.this, ListChannelActivity.class);
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
                Intent i = new Intent(ChannelActivity.this, ProfilActivity.class);
                startActivity(i);
                LinearLayout theMenu = (LinearLayout) findViewById(R.id.menuLeft);
                if (theMenu != null) {
                    theMenu.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    private boolean sendChatMessage(Channel theChannel) {
        //Pour l'instant le message s'affiche uniquement mais n'est pas sauvegardé
        //TODO: Gérer l'envoie du message sur la base de donnée.

        return true;
    }
}
