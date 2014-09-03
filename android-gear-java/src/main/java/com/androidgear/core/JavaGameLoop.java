package com.androidgear.core;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import com.androidgear.core.asset.AssetLoader;
import com.androidgear.core.asset.AssetManager;
import com.androidgear.core.asset.JavaAssetLoader;
import com.androidgear.core.config.JavaConfig;
import com.androidgear.core.dialog.DialogFactory;
import com.androidgear.core.dialog.JavaDialogFactory;
import com.androidgear.core.graphics.JavaGraphics;
import com.androidgear.core.window.JavaGameWindow;

public class JavaGameLoop implements WindowListener {
    protected JFrame mainWindow;
    private boolean active;
    private BufferStrategy bufferStrategy;
    private long expectedMilis;

    private final Game game;
    private JavaGraphics graphics;

    private long lastUpdate;
    private boolean isPointerDown;

    private JavaGameWindow gameWindow;
    private JavaConfig config;

    public JavaGameLoop(final Game game) {
        this.game = game;

        GearCommands.prepare(JavaGearCommands.class);

        DialogFactory.prepare(JavaDialogFactory.class);

        mainWindow = new JFrame(game.getClass().getSimpleName());
        mainWindow.setIgnoreRepaint(true);
        mainWindow.setLocation(100, 100);

        config = new JavaConfig();
        config.init(mainWindow, game);

        mainWindow.setVisible(true);
        mainWindow.createBufferStrategy(2);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setResizable(false);

        gameWindow = config.getWindow();

        ((JavaDialogFactory)DialogFactory.instance()).setJFrame(mainWindow);

        mainWindow.addWindowListener(this);
        mainWindow.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent event) {
                int x = event.getX() - gameWindow.getLeft();
                int y = event.getY() - gameWindow.getTop();
                if (!verifyCoords(x, y)) {
                    gameWindow.delegateClick(event.getX(), event.getY());
                    return;
                }
                isPointerDown = false;
                game.onEndPointerEvent(x, y);
            }

            @Override
            public void mousePressed(MouseEvent event) {
                int x = event.getX() - gameWindow.getLeft();
                int y = event.getY() - gameWindow.getTop();
                if (!verifyCoords(x, y)) {
                    return;
                }
                isPointerDown = true;
                game.onStartPointerEvent(x, y);
            }

            public void mouseExited(MouseEvent event) {
            }

            public void mouseEntered(MouseEvent event) {
            }

            public void mouseClicked(MouseEvent event) {
            }
        });
        mainWindow.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent event) {
            }

            @Override
            public void mouseDragged(MouseEvent event) {
                int x = event.getX() - gameWindow.getLeft();
                int y = event.getY() - gameWindow.getTop();
                if (!verifyCoords(x, y)) {
                    return;
                }
                if (isPointerDown) {
                    game.onPointerDragEvent(x, y);
                }
            }
        });

        graphics = new JavaGraphics();
        graphics.setWidth(gameWindow.getWindowWidth());
        graphics.setHeight(gameWindow.getWindowHeight());

        active = false;
    }

    public void terminate() {
        active = false;
    }

    public void run() {
        active = true;

        // Asset Manager
        AssetLoader loader = new JavaAssetLoader();
        AssetManager.setLoader(loader);

        // Game Init
        bufferStrategy = mainWindow.getBufferStrategy();

        expectedMilis = 1000 / game.getFrameRate();
        game.init(graphics);
        lastUpdate = System.currentTimeMillis();

        long lastRender = 0;

        // Game loop
        do {
            long update = System.currentTimeMillis();
            long delta = update - lastUpdate;

            if (lastUpdate - lastRender >= expectedMilis) {
                // Game Render
                Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();

                graphics.setG2d(g);
                gameWindow.draw(g, graphics);

                g.dispose();
                bufferStrategy.show();

                lastRender = System.currentTimeMillis();
            } else {
                // Game update
                gameWindow.update(delta);
                game.update(delta);
                Thread.yield();
            }
            lastUpdate += delta;
        } while (active);

        // Game destroy
        game.destroy();
        bufferStrategy.dispose();
        mainWindow.dispose();
    }

    private boolean verifyCoords(int x, int y) {
        return x >= 0 && x <= gameWindow.getWindowWidth() && y >= 0
                && y <= gameWindow.getWindowHeight();
    }

    public void windowClosing(WindowEvent e) {
        terminate();
    }

    public void windowOpened(WindowEvent e) {
    }

    public void windowClosed(WindowEvent e) {
    }

    public void windowIconified(WindowEvent e) {
    }

    public void windowDeiconified(WindowEvent e) {
    }

    public void windowActivated(WindowEvent e) {
    }

    public void windowDeactivated(WindowEvent e) {
    }
}