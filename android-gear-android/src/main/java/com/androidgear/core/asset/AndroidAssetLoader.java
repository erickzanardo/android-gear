package com.androidgear.core.asset;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.media.MediaPlayer;

import com.androidgear.core.graphics.AndroidImage;
import com.androidgear.core.graphics.AndroidSpriteSheet;
import com.androidgear.core.graphics.Font;
import com.androidgear.core.graphics.Image;
import com.androidgear.core.graphics.SpriteSheet;
import com.androidgear.core.sound.AndroidSound;
import com.androidgear.core.sound.Sound;

public class AndroidAssetLoader implements AssetLoader {

    @Override
    public Image loadImage(String src) throws AssetNotFoundException {
        InputStream is = null;
        try {
            is = getClass().getResourceAsStream(src);
            if (is == null) {
                throw new AssetNotFoundException();
            } else {
                Bitmap decodeStream = BitmapFactory.decodeStream(is);
                Image image = new AndroidImage(decodeStream);
                return image;
            }
        } catch (Exception e) {
            throw new AssetNotFoundException();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    throw new AssetNotFoundException();
                }
            }
        }
    }

    @Override
    public Sound loadSound(String src) throws AssetNotFoundException {
        MediaPlayer player = new MediaPlayer();
        try {
            File temp = inputStreamToTempFile(src, src.replace("/", "-"), ".dat");
            player.setDataSource(temp.getAbsolutePath());
            player.prepare();
            temp.delete();
        } catch (IOException e) {
            throw new AssetNotFoundException();
        }
        return new AndroidSound(player);
    }

    @Override
    public Font loadFont(String src, int style, int size)
            throws AssetNotFoundException {

        try {
            File temp = inputStreamToTempFile(src, "font", ".ttf");

            Typeface createFromFile = Typeface.createFromFile(temp);
            Typeface create = Typeface.create(createFromFile, style);

            temp.delete();

            return new Font(create, style, size);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AssetNotFoundException();
        }
    }

    private File inputStreamToTempFile(String src, String p, String s ) throws IOException {
        File temp = File.createTempFile(p, s);
        InputStream inputStream = getClass().getResourceAsStream(src);

        OutputStream out = new FileOutputStream(temp);

        int read = 0;
        byte[] bytes = new byte[1024];

        while ((read = inputStream.read(bytes)) != -1) {
            out.write(bytes, 0, read);
        }

        inputStream.close();
        out.flush();
        out.close();

        return temp;
    }

    @Override
    public SpriteSheet loadSpriteSheet(String src, int c, int r, int fps)
            throws AssetNotFoundException {

        InputStream is = null;
        try {
            is = getClass().getResourceAsStream(src);
            if (is == null) {
                throw new AssetNotFoundException();
            } else {
                Bitmap decodeStream = BitmapFactory.decodeStream(is);
                AndroidSpriteSheet androidSpriteSheet = new AndroidSpriteSheet(decodeStream, c, r);
                androidSpriteSheet.setFramesPerSecond(fps);
                return androidSpriteSheet;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new AssetNotFoundException();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    throw new AssetNotFoundException();
                }
            }
        }
    }
}
