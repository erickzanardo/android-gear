package com.androidgear.core.dialog;

public interface Dialog {
    public Dialog show();
    public Dialog cancel();
    public Dialog setMessage(String message);
    public Dialog setPositiveButton(String label, final DialogButtonListener listener);
    public Dialog setNegativeButton(String label, final DialogButtonListener listener);
    public Dialog prepare();
}
