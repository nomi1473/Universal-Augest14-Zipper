package com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperview.Shapes;

import android.graphics.Canvas;
import android.graphics.Paint.Align;
import android.graphics.Typeface;

import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.media.Media;

public class ULabel extends Urect {

    String Text;

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public ULabel(double x, double y, double width, double height, String text) {
        super(x, y, width, height);
        Text = text;
        setFont(Media.font1);
        paint.setTextAlign(Align.CENTER);
    }

    public void setTextAlign(Align align) {
        paint.setTextAlign(align);
    }

    public void SetTextSize(double size) {
        paint.setTextSize((float) size);
    }

    public void setFont(Typeface font) {
        if (font != null)
            paint.setTypeface(font);
    }

    @Override
    public void Draw(Canvas canvas) {
        //super.Draw(canvas);
        int i = canvas.save();
        canvas.skew((int) skewX, (int) skewY);
        canvas.rotate((int) getRotate(), (int) GetCenterX(), (int) getCenterY());
        paint.setAlpha((int) getAlpha());
        canvas.drawText(getText(), (float) GetCenterX(), (float) (getCenterY() + Height() / 5), paint);


        canvas.restoreToCount(i);
        drawChildrens(canvas);

    }

}
