package com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperview.Resource;


import android.graphics.Bitmap;
import android.graphics.Rect;

public class Resources {
    public static Rect GetRectOfImage(Bitmap image) {
        return new Rect(0, 0, image.getWidth(), image.getHeight());
    }
}
