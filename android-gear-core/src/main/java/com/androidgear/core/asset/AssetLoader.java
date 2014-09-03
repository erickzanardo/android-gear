package com.androidgear.core.asset;

import com.androidgear.core.graphics.Font;
import com.androidgear.core.graphics.Image;
import com.androidgear.core.graphics.SpriteSheet;
import com.androidgear.core.sound.Sound;

public interface AssetLoader {
    public abstract Image loadImage(String src) throws AssetNotFoundException;
    public abstract Sound loadSound(String src) throws AssetNotFoundException;
    public abstract Font loadFont(String src, int style, int size) throws AssetNotFoundException;
    public abstract SpriteSheet loadSpriteSheet(String src, int c, int r, int fps) throws AssetNotFoundException;
}
