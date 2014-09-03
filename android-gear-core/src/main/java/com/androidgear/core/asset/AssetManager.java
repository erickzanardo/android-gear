package com.androidgear.core.asset;

import com.androidgear.core.graphics.Font;
import com.androidgear.core.graphics.Image;
import com.androidgear.core.graphics.SpriteSheet;
import com.androidgear.core.sound.Sound;

public abstract class AssetManager {
    private static AssetLoader loader;

    public static void setLoader(AssetLoader assetLoader) {
        if (loader == null) {
            loader = assetLoader;
        }
    }

    public static Image loadImage(String src) throws AssetNotFoundException {
        return loader.loadImage(src);
    }

    public static Sound loadSound(String src) throws AssetNotFoundException {
        return loader.loadSound(src);
    }

    public static Font loadFont(String src, int style, int size) throws AssetNotFoundException {
        return loader.loadFont(src, style, size);
    }

    public static SpriteSheet loadSpriteSheet(String src, int c, int r, int fps) throws AssetNotFoundException {
        return loader.loadSpriteSheet(src, c, r, fps);
    }

}
