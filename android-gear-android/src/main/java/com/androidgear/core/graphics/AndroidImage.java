package com.androidgear.core.graphics;

import android.graphics.Bitmap;
import android.graphics.Rect;

public class AndroidImage implements Image {

    private Bitmap bitmap;

    public AndroidImage(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    @Override
    public int width() {
        return bitmap.getWidth();
    }

    @Override
    public int height() {
        return bitmap.getHeight();
    }

    @Override
    public void draw(Graphics graphics, float x, float y) {
        AndroidGraphics androidGraphics = (AndroidGraphics) graphics;
        androidGraphics.getCanvas().drawBitmap(bitmap, x, y, null);
    }

    @Override
    public void draw(Graphics graphics, float x, float y, int w, int h) {
        AndroidGraphics androidGraphics = (AndroidGraphics) graphics;
        androidGraphics.getCanvas().drawBitmap(Bitmap.createScaledBitmap(bitmap, w, h, false), x, y, null);
    }

    @Override
    public void draw(Graphics graphics, float dx, float dy, float dw, float dh,
            float sx, float sy, float sw, float sh) {
        AndroidGraphics androidGraphics = (AndroidGraphics) graphics;

        Rect src = new Rect((int)sx, (int)sy, (int)(sx + sw), (int)(sy + sh));
        Rect dest= new Rect((int)dx, (int)dy, (int)(dx + dw), (int)(dy + dh));
        androidGraphics.getCanvas().drawBitmap(bitmap, src, dest, null);
    }
}
