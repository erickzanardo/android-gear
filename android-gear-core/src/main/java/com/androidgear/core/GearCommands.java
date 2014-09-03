package com.androidgear.core;

public abstract class GearCommands {
    private static GearCommands instance;

    protected GearCommands() {
    }

    public static void prepare(Class<? extends GearCommands> clazz) {
        try {
            instance = clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static GearCommands instance() {
        return instance;
    }

    public abstract void killGame();
    public abstract void vibrate(long milis);
}
