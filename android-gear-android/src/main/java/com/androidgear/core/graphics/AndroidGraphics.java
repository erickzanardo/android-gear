package com.androidgear.core.graphics;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;

public class AndroidGraphics implements Graphics {

    private Paint paint = new Paint();

    private Canvas canvas;
    private int color;
    Font font;

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    @Override
    public void setColor(int color) {
        this.color = color;
        paint.setColor(color);
    }

    @Override
    public int getColor() {
        return color;
    }

    @Override
    public void drawRect(float x, float y, int w, int h) {
        paint.setStyle(Paint.Style.STROKE);

        canvas.drawRect(x, y, x + w, y + h, paint);
    }

    @Override
    public void fillRect(float x, float y, int w, int h) {
        paint.setStyle(Paint.Style.FILL);

        canvas.drawRect(x, y, x + w, y + h, paint);
    }

    @Override
    public void drawCircle(float x, float y, int radius) {
        paint.setStyle(Paint.Style.STROKE);

        canvas.drawCircle(x, y, radius, paint);
    }

    @Override
    public void fillCircle(float x, float y, int radius) {
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(x, y, radius, paint);
    }

    @Override
    public void drawOval(float x, float y, int w, int h) {
        paint.setStyle(Paint.Style.STROKE);
        canvasDrawOval(x, y, w, h);

    }

    @Override
    public void fillOval(float x, float y, int w, int h) {
        paint.setStyle(Paint.Style.FILL);
        canvasDrawOval(x, y, w, h);
    }

    private void canvasDrawOval(float x, float y, int w, int h) {
        float left = x;
        float right = x + w;
        float top = y;
        float bottom = y - h;
        RectF oval = new RectF(left, top, right, bottom);
        canvas.drawOval(oval, paint);
    }

    @Override
    public void setTextStyle(Font style) {
        paint.setTextSize(style.getFontSize());
        if (style.getLoadedFont() != null) {
            paint.setTypeface((Typeface) style.getLoadedFont());
        } else {
            paint.setTypeface(Typeface.create(style.getFontFamily(),
                    style.getFontStyle()));
        }
        this.font = style;
    }

    @Override
    public Font getTextStyle() {
        return font;
    }

    @Override
    public void drawString(String str, float x, float y) {
        canvas.drawText(str, x, y, paint);
    }

    @Override
    public int screenWidth() {
        return canvas.getWidth();
    }

    @Override
    public int screenHeight() {
        return canvas.getHeight();
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
