package com.androidgear.core.graphics;

public interface Graphics {

    public abstract void setColor(int color);

    public abstract int getColor();

    public abstract void drawRect(float x, float y, int w, int h);

    public abstract void fillRect(float x, float y, int w, int h);

    public abstract void drawCircle(float x, float y, int radius);

    public abstract void fillCircle(float x, float y, int radius);
    
    public abstract void drawOval(float x, float y, int w, int h);
    
    public abstract void fillOval(float x, float y, int w, int h);
    
    public abstract void setTextStyle(Font style);

    public abstract Font getTextStyle();

    public abstract void drawString(String str, float x, float y);

    public abstract int screenWidth();

    public abstract int screenHeight();

    public abstract void drawImage(Image image, float x, float y);

    public abstract void drawImage(Image image, float x, float y, int w, int h);

    public abstract void drawImage(Image image, float dx, float dy, float dw,
            float dh, float sx, float sy, float sw, float sh);

}
