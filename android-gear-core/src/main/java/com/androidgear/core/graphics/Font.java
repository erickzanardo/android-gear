package com.androidgear.core.graphics;

public class Font {
    public static final int FONT_STYLE_PLAIN = 0;
    public static final int FONT_STYLE_BOLD = 1;
    public static final int FONT_STYLE_ITALIC = 2;
    public static final int FONT_STYLE_BOLD_ITALIC = 3;

    private Object loadedFont;

    private String family;
    private int style;
    private int size;

    public Font(String family, int style, int size) {
        this.family = family;
        this.style = style;
        this.size = size;
    }

    public Font(Object loadedFont, int style, int size) {
        this.loadedFont = loadedFont;
        this.style = style;
        this.size = size;
    }

    public String getFontFamily() {
        return family;
    }

    public void setFontFamily(String family) {
        this.family = family;
    }

    public int getFontStyle() {
        return style;
    }

    public void setFontStyle(int fontStyle) {
        this.style = fontStyle;
    }

    public int getFontSize() {
        return size;
    }

    public void setFontSize(int fontSize) {
        this.size = fontSize;
    }

    public Object getLoadedFont() {
        return loadedFont;
    }

    public void setLoadedFont(Object loadedFont) {
        this.loadedFont = loadedFont;
    }
}
