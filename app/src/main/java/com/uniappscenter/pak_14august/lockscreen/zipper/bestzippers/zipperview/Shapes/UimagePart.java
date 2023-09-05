package com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperview.Shapes;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class UimagePart extends Uimage {

    public Urect ImageRect;
    public UimagePart(double x, double y, double width, double height, Urect imageRect, Bitmap img) {
        super(x, y, width, height, img);
        ImageRect = imageRect;
    }

    public boolean LogedImageRecycled = false;

    @Override
    public void Draw(Canvas canvas) {
        // TODO Auto-generated method stub
        int i = canvas.save();
        canvas.rotate((int) getRotate(), (int) GetCenterX(), (int) getCenterY());
        paint.setAlpha((int) getAlpha());
        canvas.skew((int) skewX, (int) skewY);

        if (image.isRecycled() && !LogedImageRecycled) {
            LogedImageRecycled = true;

        } else {
            canvas.drawBitmap(image, ImageRect.GetRect(), GetRect(), paint);
        }

        canvas.restoreToCount(i);
        drawChildrens(canvas);
    }
}
