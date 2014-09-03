package com.androidgear.core.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;

public class AndroidDialog implements Dialog {

    private Builder builder;
    private AlertDialog alert;

    public AndroidDialog(Activity a) {
        builder = new AlertDialog.Builder(a);
    }

    @Override
    public Dialog show() {
        if (alert == null) {
            throw new RuntimeException(
                    "You must call prepare() before using de dialog.");
        }
        alert.show();
        return this;
    }

    @Override
    public Dialog cancel() {
        if (alert == null) {
            throw new RuntimeException(
                    "You must call prepare() before using de dialog.");
        }
        alert.cancel();
        return this;
    }

    @Override
    public Dialog setMessage(String message) {
        builder.setMessage(message);
        return this;
    }

    @Override
    public Dialog setPositiveButton(String label,
            final DialogButtonListener listener) {
        builder.setPositiveButton(label, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                listener.execute();
            }
        });
        return this;
    }

    @Override
    public Dialog setNegativeButton(String label,
            final DialogButtonListener listener) {
        builder.setNegativeButton(label, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                listener.execute();
            }
        });
        return this;
    }

    @Override
    public Dialog prepare() {
        alert = builder.create();
        return this;
    }
}
