package com.androidgear.core.graphics;

public interface SpriteSheet {

    public abstract void setFramesPerSecond(int fps);

    public abstract int getFramesPerSecond();

    public abstract void update(long delta);

    public abstract Image currentFrame();

    public abstract void drawCurrentFrame(Graphics graphics, float x, float y);

    public abstract void setStartFrameIndex(int index);

    public abstract void setEndFrameIndex(int index);
}
