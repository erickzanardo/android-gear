package com.androidgear.core.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.JFrame;

import com.androidgear.core.Game;
import com.androidgear.core.window.JavaGameWindow;
import com.androidgear.core.window.JavaSimpleWindow;
import com.androidgear.core.window.JavaSimulatorWindow;

public class JavaConfig {

    private static final String JAVA_CONFIG_PROPERTIES = "java-config.properties";
    private static int DEFAULT_WIDTH = 480;
    private static int DEFAULT_HEIGHT = 600;

    public int width = DEFAULT_WIDTH;
    public int height = DEFAULT_HEIGHT;
    public JavaGameWindow window;

    public void init(JFrame window, Game game) {
        Properties p = loadProperties();
        this.window = new JavaSimpleWindow(window, DEFAULT_WIDTH,
                DEFAULT_HEIGHT, game);
        if (p == null) {
            System.out.println("File: " + JAVA_CONFIG_PROPERTIES
                    + " not found, running with default configuraton");
        } else {
            System.out.println("Parsing " + JAVA_CONFIG_PROPERTIES);

            if (p.get("width") != null) {
                setWidth(Integer.parseInt(p.get("width").toString()));
            }

            if (p.get("height") != null) {
                setHeight(Integer.parseInt(p.get("height").toString()));
            }

            if (p.get("window") != null && "Simulator".equals(p.get("window"))) {
                this.window = new JavaSimulatorWindow(window, getWidth(),
                        getHeight(), game);
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public JavaGameWindow getWindow() {
        return window;
    }

    public void setWindow(JavaGameWindow window) {
        this.window = window;
    }

    private Properties loadProperties() {
        Properties props = new Properties();
        InputStream inputStream = this.getClass().getClassLoader()
                .getResourceAsStream(JAVA_CONFIG_PROPERTIES);

        if (inputStream == null) {
            return null;
        }

        try {
            props.load(inputStream);
        } catch (IOException e) {
            return null;
        }

        return props;
    }
}
