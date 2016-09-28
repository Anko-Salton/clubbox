package com.clubbox.clubbox;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.clubbox.clubbox.model.Match;
import com.clubbox.clubbox.model.News;
import com.clubbox.clubbox.model.User;
import com.clubbox.clubbox.network.MatchREST;
import com.clubbox.clubbox.network.NewsREST;
import com.clubbox.clubbox.network.UserREST;
import com.clubbox.clubbox.propertie.Properties;

import java.util.List;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class Acceuil extends AppCompatActivity {

    private ProgressDialog p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {User connectedUser = new User(0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuil);
        final EditText txtUser = (EditText)findViewById(R.id.txtLogin);
        final EditText txtPass = (EditText)findViewById(R.id.txtPassword);
        Button btnConnect = (Button) findViewById(R.id.connect);
        p = new ProgressDialog(this);
        assert btnConnect != null;
        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                p.setMessage("Veuillez patienter...");
                p.setCancelable(false);
                //Requete synchrone --> séparation des threads.
                Runnable auth = new Runnable() {
                    @Override
                    public void run() {
                        String username = txtUser.getText().toString();
                        String password = txtPass.getText().toString();
                        try{
                            UserREST userRest = new Retrofit.Builder()
                                    .baseUrl(UserREST.ENDPOINT)
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build()
                                    .create(UserREST.class);
                            User connectedUser = userRest.getAuth(username, password).execute().body();
                            Log.e("ConnectedUser : ",connectedUser.getId().toString());
                            //Toast.makeText(Acceuil.this, connectedUser.getId()+"// "+connectedUser.getName(), Toast.LENGTH_SHORT).show();
                            if(connectedUser != null && connectedUser.getId() != 0){
                                MatchREST matchREST = new Retrofit.Builder()
                                        .baseUrl(MatchREST.ENDPOINT)
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build()
                                        .create(MatchREST.class);
                                Properties.getInstance().setConnected(true);
                                Properties.getInstance().setConnectedUser(connectedUser);
                                Intent changeOnLogin = new Intent(Acceuil.this,MainActivity.class);
                                Bundle bundle = new Bundle();
                                Match lastMatch = matchREST.getLastMatchByClubId(connectedUser.getClub().getId().intValue()).execute().body();
                                Match nextMatch = matchREST.getNextMatchByClubId(connectedUser.getClub().getId().intValue()).execute().body();
                                bundle.putSerializable("lastMatch",lastMatch);
                                changeOnLogin.putExtra("lastMatch",bundle);
                                bundle.putSerializable("nextMatch",nextMatch);
                                changeOnLogin.putExtra("nextMatch",bundle);
                                startActivity(changeOnLogin);
                            }
                        }catch(Exception e){
                            Log.e("PERSONNAL ERROR LOG","Erreur : "+e.getMessage());
                            Toast.makeText(Acceuil.this, "Une erreur est survenue lors de la connexion", Toast.LENGTH_SHORT).show();
                        }
                        p.dismiss();
                    }
                };
                Thread thread = new Thread(auth);
                if(!p.isShowing()) {
                    p.show();
                }
                thread.start();
            }
        });

    }
}
