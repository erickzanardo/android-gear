package com.androidgear.core.graphics;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;

public class AndroidSpriteSheet implements SpriteSheet {

    private int collumns, rows, startIndex, endIndex, index;
    private List<Image> sprites = new ArrayList<Image>();
    private int fps, milisRate, deltaCounter;

    public AndroidSpriteSheet(Bitmap decodeStream, int c, int r) {
        this.collumns = c;
        this.rows = r;
        startIndex = 0;

        parseSprites(decodeStream);

        endIndex = sprites.size();
        deltaCounter = 0;
    }

    private void parseSprites(Bitmap decodeStream) {
        int spriteWidth = decodeStream.getWidth() / collumns;
        int spriteHeight = decodeStream.getHeight() / rows;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < collumns; c++) {
                Bitmap bitmap = Bitmap.createBitmap(decodeStream, c
                        * spriteWidth, r * spriteHeight, spriteWidth,
                        spriteHeight);
                AndroidImage image = new AndroidImage(bitmap);
                sprites.add(image);
            }
        }
    }

    @Override
    public void setFramesPerSecond(int fps) {
        this.fps = fps;
        milisRate = 1000 / fps;
    }

    @Override
    public int getFramesPerSecond() {
        return fps;
    }

    @Override
    public void update(long delta) {
        if (deltaCounter >= milisRate) {
            if (index + 1 < endIndex) {
                index++;
            } else {
                index = startIndex;
            }
            deltaCounter = 0;
        }
        deltaCounter += delta;
    }

    @Override
    public Image currentFrame() {
        return sprites.get(index);
    }

    @Override
    public void drawCurrentFrame(Graphics graphics, float x, float y) {
        graphics.drawImage(currentFrame(), x, y);
    }

    @Override
    public void setStartFrameIndex(int index) {
        startIndex = index;
        if (index < this.index) {
            this.index = index;
        }
    }

    @Override
    public void setEndFrameIndex(int index) {
        endIndex = index;
        if (index > this.index) {
            this.index = index;
        }
    }

}
