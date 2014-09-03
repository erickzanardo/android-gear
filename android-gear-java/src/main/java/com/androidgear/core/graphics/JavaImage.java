package com.androidgear.core.graphics;

import java.awt.image.BufferedImage;

public class JavaImage implements Image {

    private BufferedImage image;

    public JavaImage(BufferedImage image) {
        this.image = image;
    }

    @Override
    public int width() {
        return image.getWidth();
    }

    @Override
    public int height() {
        return image.getHeight();
    }

    @Override
    public void draw(Graphics graphics, float x, float y) {
        JavaGraphics javaGraphics = (JavaGraphics) graphics;
        javaGraphics.getG2d().drawImage(image, (int) x, (int) y, null);
    }

    @Override
    public void draw(Graphics graphics, float x, float y, int w, int h) {
        JavaGraphics javaGraphics = (JavaGraphics) graphics;
        javaGraphics.getG2d().drawImage(image, (int) x, (int) y, (int) w,
                (int) h, null);
    }

    @Override
    public void draw(Graphics graphics, float dx, float dy, float dw, float dh,
            float sx, float sy, float sw, float sh) {
        JavaGraphics javaGraphics = (JavaGraphics) graphics;

        BufferedImage subimage = image.getSubimage((int) sx, (int) sy,
                (int) sw, (int) sh);

        javaGraphics.getG2d().drawImage(subimage, (int) dx, (int) dy, (int) dw,
                (int) dh, null);
    }
}
