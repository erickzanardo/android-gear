package com.androidgear.core;

import com.androidgear.core.graphics.Graphics;

public interface Game {
    public abstract void init(Graphics graphics);

    public abstract void destroy();

    public abstract void update(long delta);

    public abstract void render(Graphics graphics);

    public abstract void onStartPointerEvent(float x, float y);

    public abstract void onEndPointerEvent(float x, float y);

    public abstract void onPointerDragEvent(float x, float y);

    public abstract int getFrameRate();

    public abstract boolean screenChanged(int newWidht, int newHeight);

    public abstract boolean onBackPress();
}
