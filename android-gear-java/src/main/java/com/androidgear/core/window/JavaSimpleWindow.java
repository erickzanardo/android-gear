package com.androidgear.core.window;

import java.awt.Graphics2D;
import java.awt.Insets;

import javax.swing.JFrame;

import com.androidgear.core.Game;
import com.androidgear.core.graphics.Graphics;

public class JavaSimpleWindow extends JavaGameWindow {
    private Insets insets;

    public JavaSimpleWindow(JFrame window, int windowWidth, int windowHeight, Game game) {
        super(window, windowWidth, windowHeight, game);

        window.getAlignmentX();
        window.getAlignmentY();
        insets = window.getInsets();

        window.setSize(windowWidth + getLeft() + getRight(), windowHeight + getTop() + getBottom());
    }

    @Override
    public void update(long delta) {

    }

    @Override
    public int getLeft() {
        return insets.left;
    }

    @Override
    public int getRight() {
        return insets.right;
    }

    @Override
    public int getTop() {
        return insets.top;
    }

    @Override
    public int getBottom() {
        return insets.bottom;
    }

    @Override
    public void draw(Graphics2D g, Graphics gearGraphics) {
        game.render(gearGraphics);
    }

    @Override
    public void delegateClick(int x, int y) { }
}
