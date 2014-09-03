package com.androidgear.core.asset;

import java.applet.Applet;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;

import com.androidgear.core.graphics.Font;
import com.androidgear.core.graphics.Image;
import com.androidgear.core.graphics.JavaImage;
import com.androidgear.core.graphics.JavaSpriteSheet;
import com.androidgear.core.graphics.SpriteSheet;
import com.androidgear.core.sound.JavaSound;
import com.androidgear.core.sound.Sound;

public class JavaAssetLoader implements AssetLoader {

    @Override
    public Image loadImage(String src) throws AssetNotFoundException {
        URL resource = getClass().getResource(src);
        try {
            BufferedImage read = ImageIO.read(resource);
            return new JavaImage(read);
        } catch (IOException e) {
            throw new AssetNotFoundException();
        }
    }

    @Override
    public Sound loadSound(String src) throws AssetNotFoundException {
        URL url;
        url = getClass().getResource(src);

        if (url == null) {
            throw new AssetNotFoundException();
        }
        return new JavaSound(Applet.newAudioClip(url));
    }

    @Override
    public Font loadFont(String src, int style, int size)
            throws AssetNotFoundException {
        InputStream is = getClass().getResourceAsStream(src);
        try {
            java.awt.Font createFont = java.awt.Font.createFont(
                    java.awt.Font.TRUETYPE_FONT, is);
            return new Font(createFont, style, size);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public SpriteSheet loadSpriteSheet(String src, int c, int r, int fps)
            throws AssetNotFoundException {
    	InputStream is = null;
        try {
        	is = getClass().getResourceAsStream(src);
        	if(is == null) {
        		throw new AssetNotFoundException();
        	} else {
				BufferedImage image = ImageIO.read(is);
				JavaSpriteSheet spriteSheet = new JavaSpriteSheet(image, c, r);
				spriteSheet.setFramesPerSecond(fps);
				return spriteSheet;
        	}
		} catch (Exception e) {
			e.printStackTrace();
			throw new AssetNotFoundException();
		} finally {
			if(is != null) {
				try{
					is.close();
				} catch (IOException e) {
					throw new AssetNotFoundException();
				}
			}
		}
    }
}
