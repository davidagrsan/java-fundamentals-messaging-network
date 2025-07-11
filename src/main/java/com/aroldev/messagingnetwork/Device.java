package com.aroldev.messagingnetwork;

import com.aroldev.messagingnetwork.Exceptions.InvalidMessageException;

public abstract class Device {
    private String name;
    private int id;
    private boolean darkMode;

    public Device(String name, int id) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isDarkMode() {
        return darkMode;
    }

    public void setDarkMode(boolean darkMode) {
        this.darkMode = darkMode;
    }

    public abstract void sendMessage(Message msg);
    
    public abstract void receiveMessage(Message msg) throws InvalidMessageException;
}
