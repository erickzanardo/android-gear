package com.androidgear.core.window;

import java.awt.Graphics2D;

import javax.swing.JFrame;

import com.androidgear.core.Game;
import com.androidgear.core.graphics.Graphics;

public abstract class JavaGameWindow {
    protected JFrame window;
    protected int windowHeight;
    protected int windowWidth;
    protected Game game;

    public JavaGameWindow(JFrame window, int windowWidth, int windowHeight, Game game) {
        this.window = window;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.game = game;
    }

    public abstract void update(long delta);

    public abstract int getLeft();

    public abstract int getRight();

    public abstract int getTop();

    public abstract int getBottom();

    public abstract void draw(Graphics2D g, Graphics gearGraphics);

    public abstract void delegateClick(int x, int y);

    @Override
    public String toString() {
        return getLeft() + " - " + getRight() + " - " + getTop() + " - "
                + getBottom();
    }

    public int getWindowHeight() {
        return windowHeight;
    }

    public int getWindowWidth() {
        return windowWidth;
    }

}
