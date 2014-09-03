package com.androidgear.core.graphics;

public interface Image {
    public abstract int width();

    public abstract int height();

    public abstract void draw(Graphics graphics, float x, float y);

    public abstract void draw(Graphics graphics, float x, float y, int w, int h);

    public abstract void draw(Graphics graphics, float dx, float dy, float dw,
            float dh, float sx, float sy, float sw, float sh);
}
