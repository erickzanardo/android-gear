package com.androidgear.core.graphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class JavaGraphics implements Graphics {

    private Graphics2D g2d;
    private int width;
    private int height;
    private java.awt.Font font;
    private Font gearFont;

    public Graphics2D getG2d() {
        return g2d;
    }

    public void setG2d(Graphics2D g2d) {
        this.g2d = g2d;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void setColor(int color) {
        Color c = new Color(color);
        g2d.setColor(c);
    }

    @Override
    public int getColor() {
        return g2d.getColor().getRGB();
    }

    @Override
    public void drawRect(float x, float y, int w, int h) {
        g2d.drawRect((int) x, (int) y, w, h);
    }

    @Override
    public void fillRect(float x, float y, int w, int h) {
        g2d.fillRect((int) x, (int) y, (int) w, (int) h);
    }

    @Override
    public void drawCircle(float x, float y, int radius) {
    	int size = radius * 2;
        Shape circle = new Ellipse2D.Float(x, y, size, size);
        g2d.draw(circle);
    }

    @Override
    public void fillCircle(float x, float y, int radius) {
    	int size = radius * 2;
        Shape circle = new Ellipse2D.Float(x, y, size, size);
        g2d.fill(circle);
    }

    @Override
    public void drawOval(float x, float y, int w, int h) {
        Shape circle = new Ellipse2D.Float(x, y, w, h);
        g2d.draw(circle);
    }
    
    @Override
    public void fillOval(float x, float y, int w, int h) {
        Shape circle = new Ellipse2D.Float(x, y, w, h);
        g2d.fill(circle);
    }

    @Override
    public void setTextStyle(Font style) {

        int fontstyle = 0;
        switch (style.getFontStyle()) {
        case Font.FONT_STYLE_PLAIN:
            fontstyle = java.awt.Font.PLAIN;
            break;
        case Font.FONT_STYLE_BOLD:
            fontstyle = java.awt.Font.BOLD;
            break;
        case Font.FONT_STYLE_BOLD_ITALIC:
            fontstyle = java.awt.Font.ITALIC | java.awt.Font.BOLD;
            break;
        case Font.FONT_STYLE_ITALIC:
            fontstyle = java.awt.Font.ITALIC;
            break;
        }

        if (style.getLoadedFont() != null) {
            font = ((java.awt.Font) style.getLoadedFont()).deriveFont(fontstyle, style.getFontSize());
        } else {
            font = new java.awt.Font(style.getFontFamily(),
                    fontstyle, style.getFontSize());
        }

        g2d.setFont(font);
        gearFont = style;
    }

    @Override
    public Font getTextStyle() {
        return gearFont;
    }

    @Override
    public void drawString(String str, float x, float y) {
        g2d.drawString(str, x, y);
    }

    @Override
    public int screenWidth() {
        return width;
    }

    @Override
    public int screenHeight() {
        return height;
    }

    @Override
    public void drawImage(Image image, float x, float y) {
        image.draw(this, x, y);
    }

    @Override
    public void drawImage(Image image, float x, float y, int w, int h) {
        image.draw(this, x, y, w, h);
    }

    @Override
    public void drawImage(Image image, float dx, float dy, float dw, float dh,
            float sx, float sy, float sw, float sh) {
        image.draw(this, dx, dy, dw, dh, sx, sy, sw, sh);
    }
}
