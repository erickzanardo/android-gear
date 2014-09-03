package com.androidgear.core.graphics;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class JavaSpriteSheet implements SpriteSheet {

    private int spriteWidth, spriteHeight, fps, startingIndex, finalIndex,
            currentIndex;
    private int deltaCounter, milisRate;
    private List<Image> sprites = new ArrayList<Image>();

    public JavaSpriteSheet(BufferedImage image, int columns, int rows) {
        startingIndex = 0;
        this.spriteWidth = image.getWidth() / columns;
        this.spriteHeight = image.getHeight() / rows;
        deltaCounter = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                BufferedImage sprite = image.getSubimage(c * spriteWidth, r
                        * spriteHeight, spriteWidth, spriteHeight);
                JavaImage javaImage = new JavaImage(sprite);
                sprites.add(javaImage);
            }
        }
        finalIndex = sprites.size();

    }

    @Override
    public void setFramesPerSecond(int fps) {
        this.fps = fps;
        this.milisRate = 1000 / fps;
    }

    @Override
    public int getFramesPerSecond() {
        return fps;
    }

    @Override
    public void update(long delta) {
        if (deltaCounter >= milisRate) {
            if (currentIndex + 1 < finalIndex) {
                currentIndex++;
            } else {
                currentIndex = startingIndex;
            }
            deltaCounter = 0;
        }
        deltaCounter += delta;
    }

    @Override
    public Image currentFrame() {
        return sprites.get(currentIndex);
    }

    @Override
    public void drawCurrentFrame(Graphics graphics, float x, float y) {
        graphics.drawImage(sprites.get(currentIndex), x, y);
    }

    @Override
    public void setStartFrameIndex(int index) {
        this.startingIndex = index;
        if (index < currentIndex) {
            currentIndex = index;
        }
    }

    @Override
    public void setEndFrameIndex(int index) {
        this.finalIndex = index;
        if (index > finalIndex) {
            finalIndex = index;
        }
    }
}
