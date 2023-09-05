package com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperview.Shapes;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;

import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperview.Resource.Resources;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperview.scripts.UimageSizeType;

public class Uimage extends Urect {

    protected Bitmap image;

    public Bitmap getImage() {
        return image;
    }


    public UimageSizeType sizeType = UimageSizeType.FitXY;


    public Uimage(double x, double y, double width, double height, Bitmap image) {
        super(x, y, width, height);
        paint.setColor(Color.TRANSPARENT);
        this.image = image;
    }


    @Override
    public void Draw(Canvas canvas) {
        //super.Draw(canvas);

        int i = canvas.save();
        canvas.rotate((int) getRotate(), (int) GetCenterX(), (int) getCenterY());
        paint.setAlpha((int) getAlpha());
        canvas.skew((int) skewX, (int) skewY);
        if (image.isRecycled()) {
            return;
        } else {
            switch (sizeType) {
                case FitXY:
                    canvas.drawBitmap(image, Resources.GetRectOfImage(image), GetRect(), paint);
                    break;
                case FitX:
                    Rect r = Resources.GetRectOfImage(image), r2, r3 = GetRect();
                    double h = r.height() * (r3.height() / r3.width());
                    r2 = new Rect(0, 0, r.width(), (int) h);
                    canvas.drawBitmap(image, r2, GetRect(), paint);
                    break;
                default:
                    break;
            }
        }
        canvas.restoreToCount(i);
        drawChildrens(canvas);
    }
}
