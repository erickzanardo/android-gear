package com.androidgear.core.window;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.androidgear.core.Game;
import com.androidgear.core.GearCommands;
import com.androidgear.core.JavaGearCommands;
import com.androidgear.core.graphics.Graphics;
import com.androidgear.core.graphics.JavaGraphics;

public class JavaSimulatorWindow extends JavaGameWindow {

    private static final int SIMULATOR_MARGIN = 100;
    private static final int VIBRATE_RATE = 7;

    private int borderX, borderY, borderW, borderH;
    private int labelX, labelY, rotateLabelX, rotateLabelY;
    private double angleLabel;

    private int backBtX, backBtY, rotateBackX, rotateBackY;
    private double angleBack;

    private int invertBtX, invertBtY;

    private Color background;

    private JavaGearCommands instance;

    private double translateX;
    private double translateY;

    private boolean vibrate;
    private Random r = new Random();
    private Font font;
    private Color fontColor;

    private Image backButton;
    private Image orientationButton;

    private boolean portrait;

    private BufferedImage drawable;
    private Graphics2D drawableGraphics;

    public JavaSimulatorWindow(JFrame window, int windowWidth,
            int windowHeight, Game game) {
        super(window, windowWidth, windowHeight, game);

        borderX = SIMULATOR_MARGIN / 2;
        borderY = SIMULATOR_MARGIN / 2;

        orientationButton = loadImg("/com/androidgear/simulator/simulator-orientation.png");

        calculateCoords();

        font = new Font("Arial", Font.BOLD, 14);
    }

    private void calculateCoords() {
        window.setSize(windowWidth + SIMULATOR_MARGIN * 2, windowHeight
                + SIMULATOR_MARGIN * 2);
        portrait = windowHeight > windowWidth;

        borderW = windowWidth + SIMULATOR_MARGIN;
        borderH = windowHeight + SIMULATOR_MARGIN;

        if (portrait) {
            labelX = borderW / 2 - 10;
            labelY = borderY + 35;
        } else {
            labelX = borderW - 35;
            labelY = borderH / 2 - 10;
        }

        rotateLabelX = labelX + (60 / 2);
        rotateLabelY = labelY + (50 / 2);

        angleLabel = 90 * Math.PI / 180.0;

        background = new Color(0x292929);
        fontColor = new Color(0x545454);

        instance = (JavaGearCommands) GearCommands.instance();

        backButton = loadImg("/com/androidgear/simulator/simulator-back.png");
        if (portrait) {
            backBtX = borderW / 2 + backButton.getWidth(null) / 2;
            backBtY = borderH + 10;
        } else {
            backBtY = borderH / 2 + backButton.getHeight(null) / 2;
            backBtX = borderX + 10;
        }

        rotateBackX = backBtX + (backButton.getWidth(null) / 2);
        rotateBackY = backBtY + (backButton.getHeight(null) / 2);

        angleBack = 90 * Math.PI / 180.0;

        drawable = new BufferedImage(windowWidth, windowHeight,
                BufferedImage.TYPE_4BYTE_ABGR);
        drawableGraphics = drawable.createGraphics();

        invertBtX = (int) (window.getSize().getWidth()
                - orientationButton.getWidth(null) - 7);
        invertBtY = 50;
    }

    @Override
    public void update(long delta) {
        if (instance.getMilisVibrate() > 0) {
            instance.setMilisVibrate(instance.getMilisVibrate() - delta);

            translateX = r.nextInt(VIBRATE_RATE);
            translateY = r.nextInt(VIBRATE_RATE);
            // -
            if (!r.nextBoolean()) {
                translateX *= -1;
            }
            if (!r.nextBoolean()) {
                translateY *= -1;
            }
            vibrate = true;

            if (instance.getMilisVibrate() < 0) {
                instance.setMilisVibrate(0);
            }
        }
    }

    @Override
    public int getLeft() {
        return SIMULATOR_MARGIN;
    }

    @Override
    public int getRight() {
        return SIMULATOR_MARGIN;
    }

    @Override
    public int getTop() {
        return SIMULATOR_MARGIN;
    }

    @Override
    public int getBottom() {
        return SIMULATOR_MARGIN;
    }

    @Override
    public void draw(Graphics2D g, Graphics gearGraphics) {
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, (int) window.getSize().getWidth(), (int) window
                .getSize().getHeight());

        if (vibrate) {
            if (translateX == 0 && translateY == 0) {
                vibrate = false;
            }
            g.translate(translateX, translateY);
        }

        g.setColor(background);
        g.fillRoundRect(borderX, borderY, borderW, borderH, 30, 30);
        g.setColor(Color.BLACK);

        g.drawRoundRect(borderX, borderY, borderW, borderH, 30, 30);
        g.fillRect(getLeft(), getRight(), windowWidth, windowHeight);

        if (!portrait) {
            g.rotate(angleBack, rotateBackX, rotateBackY);
            g.drawImage(backButton, backBtX, backBtY, null);
            g.rotate(-angleBack, rotateBackX, rotateBackY);
        } else {
            g.drawImage(backButton, backBtX, backBtY, null);
        }

        ((JavaGraphics) gearGraphics).setG2d(drawableGraphics);
        game.render(gearGraphics);
        g.drawImage(drawable, null, getLeft(), getTop());

        g.setColor(fontColor);
        g.setFont(font);
        if (!portrait) {
            g.rotate(angleLabel, rotateLabelX, rotateLabelY);
            g.drawString("Android Gear", labelX, labelY);
            g.rotate(-angleLabel, rotateLabelX, rotateLabelY);
        } else {
            g.drawString("Android Gear", labelX, labelY);
        }

        g.drawImage(orientationButton, invertBtX, invertBtY, null);
    }

    private Image loadImg(String src) {
        URL resource = getClass().getResource(src);
        try {
            Image read = ImageIO.read(resource);

            int max = (SIMULATOR_MARGIN / 2 - 15);
            if (read.getWidth(null) > max || read.getHeight(null) > max) {
                return read.getScaledInstance(max, max,
                        BufferedImage.SCALE_SMOOTH);
            }

            return read;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delegateClick(int x, int y) {
        if (x >= backBtX && x <= backBtX + backButton.getWidth(null)
                && y >= backBtY && y <= backBtY + backButton.getHeight(null)) {
            game.onBackPress();
        } else if (x >= invertBtX
                && x <= invertBtX + orientationButton.getWidth(null)
                && y >= invertBtY
                && y <= invertBtY + orientationButton.getHeight(null)) {
            invertOrientation();
            game.screenChanged(windowWidth, windowHeight);
        }
    }

    private void invertOrientation() {
        int aux = windowHeight;
        windowHeight = windowWidth;
        windowWidth = aux;
        calculateCoords();
    }
}
