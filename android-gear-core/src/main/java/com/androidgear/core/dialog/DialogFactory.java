package com.androidgear.core.dialog;


public abstract class DialogFactory {
    private static DialogFactory factory;

    public static void prepare(Class<? extends DialogFactory> clazz) {
        try {
            factory = clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static DialogFactory instance() {
        return factory;
    }

    protected abstract Dialog buildInstance();

    public static Dialog newDialog() {
        return factory.buildInstance();
    }
}
