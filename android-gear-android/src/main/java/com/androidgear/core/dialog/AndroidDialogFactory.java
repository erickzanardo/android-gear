package com.androidgear.core.dialog;

import android.app.Activity;

public class AndroidDialogFactory extends DialogFactory {

    private Activity activity;

    @Override
    protected Dialog buildInstance() {
        return new AndroidDialog(activity);
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
