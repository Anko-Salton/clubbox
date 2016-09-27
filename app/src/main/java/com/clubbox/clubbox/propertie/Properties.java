package com.clubbox.clubbox.propertie;

import com.clubbox.clubbox.model.User;

/**
 * Created by cdsm16 on 06/06/2016.
 */
public class Properties {

    private static Properties instance;
    private User connectedUser;
    private Boolean isConnected;

    public Properties(){

    }

    public static synchronized Properties getInstance(){
        if(instance==null){
            instance = new Properties();
        }
        return instance;
    }

    public User getConnectedUser() {
        return connectedUser;
    }

    public void setConnectedUser(User connectedUser) {
        this.connectedUser = connectedUser;
    }

    public Boolean getConnected() {
        return isConnected;
    }

    public void setConnected(Boolean connected) {
        isConnected = connected;
    }
}
