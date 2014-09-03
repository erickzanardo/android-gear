package com.androidgear.core;

import android.content.Context;
import android.os.Process;
import android.os.Vibrator;

public class AndroidGearCommands extends GearCommands {

    private AbstractAndroidGearActivity activity;

    public AbstractAndroidGearActivity getActivity() {
        return activity;
    }

    public void setActivity(AbstractAndroidGearActivity activity) {
        this.activity = activity;
    }

    @Override
    public void killGame() {
        int myPid = Process.myPid();
        Process.killProcess(myPid);
    }

    @Override
    public void vibrate(long milis) {
        Vibrator v = (Vibrator) activity.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(milis);
    }

}
