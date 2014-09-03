package com.androidgear.core.dialog;

import javax.swing.JFrame;


public class JavaDialogFactory extends DialogFactory {

    private JFrame mainWindow;

    @Override
    protected Dialog buildInstance() {
        return new JavaDialog(mainWindow);
    }

    public void setJFrame(JFrame mainWindow) {
        this.mainWindow = mainWindow;
    }

}
