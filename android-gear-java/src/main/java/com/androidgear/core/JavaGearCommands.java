package com.androidgear.core;

public class JavaGearCommands extends GearCommands {

    private long milisVibrate;

    @Override
    public void killGame() {
        System.exit(0);
    }

    @Override
    public void vibrate(long milis) {
        milisVibrate = milis;
    }

    public void setMilisVibrate(long milisVibrate) {
        this.milisVibrate = milisVibrate;
    }

    public long getMilisVibrate() {
        return milisVibrate;
    }
}
