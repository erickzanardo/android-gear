package com.androidgear.samples.core;

import com.androidgear.core.Game;
import com.androidgear.core.GearCommands;
import com.androidgear.core.asset.AssetManager;
import com.androidgear.core.asset.AssetNotFoundException;
import com.androidgear.core.dialog.Dialog;
import com.androidgear.core.dialog.DialogButtonListener;
import com.androidgear.core.dialog.DialogFactory;
import com.androidgear.core.graphics.Font;
import com.androidgear.core.graphics.Graphics;
import com.androidgear.core.graphics.Image;
import com.androidgear.core.graphics.SpriteSheet;
import com.androidgear.core.sound.Sound;

public class SampleGame implements Game {

    private int paintRate;
    private int lastFps;
    private int counter;

    private Image androidBuddy;
    private SpriteSheet spriteSheet;

    private int color = 0xffffffff;

    private float pointerX = 0;
    private float pointerY = 0;

    private float x = 80;
    private float y = 50;
    private boolean onSquare;

    private Font boldfont;
    private Font italicboldfont;
    private Font italicfont;
    private Font plain;

    private Font loadedFontPlain;
    private Font loadedFontItalic;

    private Sound backgroundSound;
    private Sound touchSound;
    private Dialog dialog;
    private SpriteSheet spriteSheet2;

    @Override
    public void init(Graphics graphics) {
        plain = new Font("Arial", Font.FONT_STYLE_PLAIN, 14);
        boldfont = new Font("Arial", Font.FONT_STYLE_BOLD, 14);
        italicfont = new Font("Arial", Font.FONT_STYLE_ITALIC, 14);
        italicboldfont = new Font("Arial", Font.FONT_STYLE_BOLD_ITALIC, 14);

        paintRate = 0;
        counter = 0;

        dialog = DialogFactory.newDialog();
        dialog.setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogButtonListener() {
                    @Override
                    public void execute() {
                        GearCommands.instance().killGame();
                    }
                }).setNegativeButton("No", new DialogButtonListener() {
                    @Override
                    public void execute() {
                        dialog.cancel();
                    }
                });

        try {
            androidBuddy = AssetManager
                    .loadImage("/com/androidgear/core/samples/asset/androidbuddy.png");

            spriteSheet = AssetManager.loadSpriteSheet(
                    "/com/androidgear/core/samples/asset/walking.png", 2, 1, 4);

            spriteSheet2 = AssetManager.loadSpriteSheet(
                                        "/com/androidgear/core/samples/asset/bird.png", 5, 5, 25);
            spriteSheet2.setEndFrameIndex(21);

            loadedFontPlain = AssetManager.loadFont(
                    "/com/androidgear/core/samples/asset/RetroRescued.ttf",
                    Font.FONT_STYLE_PLAIN, 20);
            loadedFontItalic = AssetManager.loadFont(
                    "/com/androidgear/core/samples/asset/RetroRescued.ttf",
                    Font.FONT_STYLE_ITALIC, 20);

            touchSound = AssetManager
                    .loadSound("/com/androidgear/core/samples/asset/Circuit.wav");
            backgroundSound = AssetManager
                    .loadSound("/com/androidgear/core/samples/asset/background.wav");
            backgroundSound.loop();
        } catch (AssetNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {
    }

    @Override
    public void update(long delta) {
        counter += delta;
        spriteSheet.update(delta);
        spriteSheet2.update(delta);
        if (counter >= 1000) {
            counter -= 1000;
            lastFps = paintRate;
            paintRate = 0;
        }
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setTextStyle(boldfont);
        paintRate++;
        graphics.setColor(0xffa5a5a5);
        graphics.fillRect(0, 0, graphics.screenWidth(), graphics.screenHeight());

        graphics.setColor(color);
        graphics.fillRect(0, 0, 2, 2);

        spriteSheet.drawCurrentFrame(graphics, 20, 20);

        spriteSheet2.drawCurrentFrame(graphics, 20, 90);

        graphics.drawImage(androidBuddy, 200, 50);

        graphics.drawImage(androidBuddy, 350, 50, 100, 113);

        graphics.drawImage(androidBuddy, 450, 50, 50, 50, 20, 0, 100, 50);

        graphics.drawImage(androidBuddy, 450, 150, 50, 50, 20, 20, 100, 50);

        graphics.drawImage(androidBuddy, 450, 300, 50, 50, 20, 40, 100, 50);

        graphics.drawString(pointerX + " - " + pointerY, 150, 400);

        graphics.drawString(Integer.toString(lastFps),
                graphics.screenWidth() - 50, 50);

        graphics.setTextStyle(italicfont);
        graphics.drawString("Some italic text", 150, 420);

        graphics.setTextStyle(italicboldfont);
        graphics.drawString("Some italic bold text", 150, 440);

        graphics.setTextStyle(plain);
        graphics.drawString("Some plain text", 150, 460);

        graphics.setTextStyle(loadedFontPlain);
        graphics.drawString("Loaded Font", 20, 420);

        graphics.setTextStyle(loadedFontItalic);
        graphics.drawString("Loaded Font", 20, 440);

        graphics.fillRect(x, y, 50, 50);
    }

    @Override
    public void onStartPointerEvent(float x, float y) {
        pointerX = x;
        pointerY = y;
        onSquare = (pointerX >= this.x && pointerX <= this.x + 50)
                && (pointerY >= this.y && pointerY <= this.y + 50);
        if (onSquare) {
            GearCommands.instance().vibrate(500);
            touchSound.play();
        }
    }

    @Override
    public void onEndPointerEvent(float x, float y) {
        pointerX = x;
        pointerY = y;
        onSquare = false;
    }

    @Override
    public void onPointerDragEvent(float x, float y) {
        if (onSquare) {
            this.x = x - 25;
            this.y = y - 25;
        }
    }

    @Override
    public int getFrameRate() {
        return 40;
    }

    @Override
    public boolean screenChanged(int newWidht, int newHeight) {
        if (color == 0xffffffff) {
            color = 0xff1249a1;
        } else {
            color = 0xffffffff;
        }
        return true;
    }

    @Override
    public boolean onBackPress() {
        dialog.prepare().show();
        return false;
    }
}
